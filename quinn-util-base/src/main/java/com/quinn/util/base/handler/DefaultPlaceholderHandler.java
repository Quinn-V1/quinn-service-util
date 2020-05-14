package com.quinn.util.base.handler;

import com.quinn.util.base.api.PlaceholderHandler;
import com.quinn.util.base.convertor.BaseConverter;
import com.quinn.util.base.CollectionUtil;
import com.quinn.util.base.StringUtil;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 占位符替换处理类
 *
 * @author Qunhua.Liao
 * @since 2020-02-30
 */
public class DefaultPlaceholderHandler implements PlaceholderHandler {

    /**
     * 如果替换不掉，将其暂时替换为别的占位符（起始），以保证不影响迭代
     */
    private static final String PLACEHOLDER_PREFIX_BAK = "@-_";

    /**
     * 如果替换不掉，将其暂时替换为别的占位符（结束），以保证不影响迭代
     */
    private static final String PLACEHOLDER_SUFFIX_BAK = "_-@";

    /**
     * 如果替换不掉，将其暂时替换为别的占位符（起始），以保证不影响迭代
     */
    private static final String DEFAULT_PREFIX = "${";

    /**
     * 如果替换不掉，将其暂时替换为别的占位符（结束），以保证不影响迭代
     */
    private static final String DEFAULT_SUFFIX = "}";

    public DefaultPlaceholderHandler(String placeholderPrefix, String placeholderSuffix) {
        this.placeholderPrefix = placeholderPrefix;
        this.placeholderSuffix = placeholderSuffix;
    }

    public DefaultPlaceholderHandler() {
        this.placeholderPrefix = DEFAULT_PREFIX;
        this.placeholderSuffix = DEFAULT_SUFFIX;
    }

    /**
     * 占位符前缀
     */
    private final String placeholderPrefix;

    /**
     * 占位符后缀
     */
    private final String placeholderSuffix;

    /**
     * 默认实例
     *
     * @return 默认占位符解析器
     */
    public static DefaultPlaceholderHandler defaultInstance() {
        return new DefaultPlaceholderHandler(DEFAULT_PREFIX, DEFAULT_SUFFIX);
    }

    @Override
    public String parseStringWithMap(String format, Map<String, Object> properties) {
        StringBuffer srcStr = new StringBuffer(format);
        ParseResult result = new ParseResult();
        result.setSuccess(true);

        parseStringValue(srcStr, result, properties);
        return srcStr.toString().replace(PLACEHOLDER_PREFIX_BAK, placeholderPrefix)
                .replace(PLACEHOLDER_SUFFIX_BAK, placeholderSuffix);
    }

    @Override
    public String parseStringWithArray(String format, Object... args) {
        if (StringUtil.isEmpty(format) || CollectionUtil.isEmpty(args)
                || !format.contains(placeholderPrefix)) {
            return format;
        }

        char[] array = format.toCharArray();
        Stack<Character> stack = new Stack();
        char start = placeholderPrefix.charAt(0);
        char end = placeholderSuffix.charAt(placeholderSuffix.length() - 1);
        int index = 0;
        int arsSize = args.length;
        int startNum = 0;

        for (int i = 0; i < array.length; i++) {
            stack.push(array[i]);
            if (end == array[i]) {
                if (index >= arsSize || startNum < 1) {
                    continue;
                }
                startNum--;

                String key = popToStart(stack, start);
                String str = BaseConverter.staticToString(args[index++]);
                str = str == null ? placeholderPrefix + key + placeholderSuffix : str;

                for (char c : str.toCharArray()) {
                    stack.push(c);
                }

            } else if (start == array[i]) {
                startNum++;
            }
        }

        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }
        builder.reverse();

        return builder.toString();
    }

    @Override
    public String parseSqlParamMapToList(String formatSql, List<Object> params, Map<String, Object> cond) {
        char[] sqlArray = formatSql.toCharArray();
        Stack<Character> stack = new Stack();
        char start = placeholderPrefix.charAt(0);
        char end = placeholderSuffix.charAt(placeholderSuffix.length() - 1);

        for (int i = 0; i < sqlArray.length; i++) {
            stack.push(sqlArray[i]);
            if (end == sqlArray[i]) {
                String key = popToStart(stack, start);
                if (!StringUtil.isEmpty(key)) {
                    Object o = cond.get(key);
                    if (o == null) {
                        params.add("");
                        stack.push('?');
                    } else if (o.getClass().isArray()) {
                        Object os[] = (Object[]) o;
                        for (Object oo : os) {
                            params.add(oo);
                            stack.push('?');
                        }
                    } else if (o instanceof Collection) {
                        Collection os = (Collection) o;
                        for (Object oo : os) {
                            params.add(oo);
                            stack.push('?');
                        }
                    } else {
                        params.add(o);
                        stack.push('?');
                    }
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }
        builder.reverse();

        return builder.toString();
    }

    @Override
    public String parseStringByOne(String format, String key, String value) {
        return format.replace(wrapperKey(key), value);
    }

    @Override
    public boolean isComplete(String value) {
        return !value.contains(placeholderPrefix) && !value.contains(placeholderSuffix);
    }

    /**
     * 包装key
     *
     * @param key 键
     * @return 使用前后缀包装好的占位符
     */
    private String wrapperKey(String key) {
        StringBuilder query = new StringBuilder();
        query.append(placeholderPrefix).append(key).append(placeholderSuffix);
        return query.toString();
    }

    /**
     * 解析字符串
     *
     * @param srcStr     需要替换的源字符串
     * @param properties 待替换的属性值Map
     */
    private void parseStringValue(StringBuffer srcStr, ParseResult result, Map<String, Object> properties) {

        if (!result.isSuccess()) {
            return;
        }

        int endNew = srcStr.indexOf(placeholderSuffix);
        if (endNew < 0) {
            result.setSuccess(false);
            return;
        }

        int startNew = srcStr.substring(0, endNew).lastIndexOf(placeholderPrefix);
        if (startNew < 0) {
            result.setSuccess(false);
            return;
        }

        String placeholder = srcStr.substring(startNew + placeholderPrefix.length(), endNew);
        String val = String.valueOf(properties.get(placeholder));
        if (StringUtil.isNotEmpty(val)) {
            srcStr.replace(startNew, endNew + placeholderSuffix.length(), val);
        } else {
            srcStr.replace(startNew, endNew + placeholderSuffix.length(), PLACEHOLDER_PREFIX_BAK + placeholder + PLACEHOLDER_SUFFIX_BAK);
        }

        result.setSuccess(true);
        parseStringValue(srcStr, result, properties);

    }

    /**
     * 出栈到头
     *
     * @param stack 栈
     * @param start 头字符
     * @return 字符串
     */
    private String popToStart(Stack<Character> stack, char start) {
        char c;
        StringBuilder builder = new StringBuilder();
        while ((c = stack.pop()) != start) {
            builder.append(c);
        }

        builder.append(start);
        builder.reverse();
        return builder.substring(placeholderPrefix.length(), builder.length() - placeholderSuffix.length());
    }

    /**
     * 解析结果暂存器
     */
    private static class ParseResult {

        /**
         * 解析是否有替换
         */
        private boolean result;

        public boolean isSuccess() {
            return result;
        }

        public void setSuccess(boolean result) {
            this.result = result;
        }

    }
}
