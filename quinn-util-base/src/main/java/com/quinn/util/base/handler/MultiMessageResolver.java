package com.quinn.util.base.handler;

import com.quinn.util.base.CollectionUtil;
import com.quinn.util.base.StringUtil;
import com.quinn.util.base.api.MessageResolver;
import com.quinn.util.base.convertor.BaseConverter;
import com.quinn.util.base.exception.BaseBusinessException;
import com.quinn.util.base.model.BaseResult;
import com.quinn.util.base.model.MessageProp;
import com.quinn.util.constant.CharConstant;
import com.quinn.util.constant.StringConstant;

import java.util.*;

/**
 * 多元国际化消息解析器
 *
 * @author Qunhua.Liao
 * @since 2020-04-20
 */
public final class MultiMessageResolver {

    /**
     * 消息解析器列表：具有优先级
     */
    private static Set<MessageResolver> resolverList = new LinkedHashSet<>();

    private MultiMessageResolver() {
    }

    /**
     * 解析消息属性
     *
     * @param locale      语言
     * @param messageProp 消息属性
     * @return 国际化消息
     */
    public static final String resolveMessageProp(Locale locale, MessageProp messageProp) {
        if (messageProp == null) {
            return null;
        }

        MessageProp prev = messageProp;
        StringBuilder query = new StringBuilder();

        do {
            String messageCode = prev.getMessageCode();
            if (StringUtil.isEmpty(messageCode)) {
                prev = prev.getPrevProp();
                continue;
            }

            String msg = resolve(locale, messageCode, prev.getParams(), prev.getI18nParams());
            if (StringUtil.isNotEmpty(msg)) {
                query.append(CharConstant.LINE_BREAK).append(msg);
            }

            if (query.length() > 0) {
                query.deleteCharAt(0);
            }
            prev = prev.getPrevProp();
        } while (prev != null);

        return query.toString();
    }

    /**
     * 解析消息
     *
     * @param locale      语言
     * @param messageCode 消息编码
     * @param params      直接参数
     * @param i18nParams  国际化参数
     * @return 国际化消息
     */
    public static final String resolve(
            Locale locale, String messageCode, Map<String, Object> params, Map<String, Object> i18nParams) {
        if (StringUtil.isEmpty(messageCode)) {
            return null;
        }

        if (!CollectionUtil.isEmpty(i18nParams)) {
            if (params == null) {
                params = new HashMap<>(i18nParams.size());
            }

            LOOP:
            for (Map.Entry<String, Object> entry : i18nParams.entrySet()) {
                for (MessageResolver resolver : resolverList) {
                    BaseResult<String> res = resolver.resolveString(locale, BaseConverter.staticToString(entry.getValue()));
                    if (res.isSuccess()) {
                        params.put(entry.getKey(), res.getData());
                        continue LOOP;
                    }
                }
                params.put(entry.getKey(), entry.getValue());
            }
        }

        for (MessageResolver resolver : resolverList) {
            BaseResult<String> res = resolver.resolveMessage(locale, messageCode, params, null, messageCode);
            if (res.isSuccess()) {
                return res.getData();
            }
        }

        return messageCode;
    }

    /**
     * 解析消息（数组形式的参数）
     *
     * @param locale      语言
     * @param messageCode 消息编码
     * @param params      参数
     * @return 国际化消息
     */
    public static final String resolve(Locale locale, String messageCode, Object... params) {
        for (MessageResolver messageResolver : resolverList) {
            BaseResult<String> res =
                    messageResolver.resolveMessageWithArrayParams(locale, messageCode, messageCode, params);
            if (res.isSuccess()) {
                return res.getData();
            }
        }
        return messageCode;
    }

    /**
     * 解析结果
     */
    public static String resolveResult(BaseResult result) {
        MessageProp messageProp = result.getMessageProp();
        if (messageProp == null) {
            return result.getMessage();
        }
        return resolveMessageProp(Locale.getDefault(), messageProp);
    }

    /**
     * 解析结果
     */
    public static String resolveException(Exception exception) {
        if (exception instanceof BaseBusinessException) {
            MessageProp messageProp = ((BaseBusinessException) exception).getMessageProp();
            if (messageProp != null) {
                return resolveMessageProp(Locale.getDefault(), messageProp);
            }
        }
        return exception.getClass().getSimpleName() + StringConstant.CHAR_COLON + exception.getMessage();
    }

    /**
     * 解析字符传
     *
     * @param locale 语言
     * @param code   编码
     * @return 国际化显示
     */
    public static String resolveString(Locale locale, String code) {
        for (MessageResolver resolver : resolverList) {
            BaseResult<String> descRes = resolver.resolveString(locale, code);
            if (descRes.isSuccess()) {
                return descRes.getData();
            }
        }
        return code;
    }

    /**
     * 设置解析器
     *
     * @param resolverList 解析器
     */
    public static void setResolverList(List<MessageResolver> resolverList) {
        if (resolverList != null) {
            Collections.sort(resolverList, Comparator.comparingInt(MessageResolver::priority));
            MultiMessageResolver.resolverList.addAll(resolverList);
        }
    }

}
