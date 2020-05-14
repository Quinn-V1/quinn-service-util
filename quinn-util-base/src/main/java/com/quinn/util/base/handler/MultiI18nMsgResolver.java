package com.quinn.util.base.handler;

import com.quinn.util.base.api.I18nMsgResolver;
import com.quinn.util.base.model.BaseResult;
import com.quinn.util.base.model.MessageProp;
import com.quinn.util.base.StringUtil;
import com.quinn.util.constant.CharConstant;
import com.quinn.util.constant.StringConstant;
import com.quinn.util.constant.enums.ExceptionEnum;

import java.util.*;

/**
 * 多元国际化消息解析器
 *
 * @author Qunhua.Liao
 * @since 2020-04-20
 */
public final class MultiI18nMsgResolver {

    private MultiI18nMsgResolver() {
    }

    private static Set<I18nMsgResolver> resolverList = new LinkedHashSet<>();

    public static void setResolverList(List<I18nMsgResolver> resolverList) {
        if (resolverList != null) {
            Collections.sort(resolverList, Comparator.comparingInt(I18nMsgResolver::priority));
            MultiI18nMsgResolver.resolverList.addAll(resolverList);
        }
    }

    /**
     * 解析消息属性
     *
     * @param messageProp 消息属性
     * @return 国际化消息
     */
    public static final String resolveMessageProp(MessageProp messageProp) {
        if (messageProp == null) {
            return null;
        }

        MessageProp prev = messageProp;
        StringBuilder query = new StringBuilder();

        do {
            String messageCode = messageProp.getMessageCode();
            if (StringUtil.isEmpty(messageCode)) {
                prev = prev.getPrevProp();
                continue;
            }

            String msg = resolve(messageCode, prev.getParams(), prev.getI18nParams());
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
     * @param messageCode 消息编码
     * @param params      直接参数
     * @param i18nParams  国际化参数
     * @return 国际化消息
     */
    public static final String resolve(String messageCode, Map<String, Object> params, Map<String, Object> i18nParams) {
        if (StringUtil.isEmpty(messageCode)) {
            return null;
        }

        for (I18nMsgResolver i18nMsgResolver : resolverList) {
            BaseResult<String> result = i18nMsgResolver.resolveMap(messageCode, params, i18nParams);
            if (result.isSuccess() || !result.wantContinue()) {
                return result.getData();
            }
        }

        Map<String, Object> allParam = new HashMap<>();
        if (params != null) {
            allParam.putAll(params);
        }

        if (i18nParams != null) {
            i18nParams.keySet().removeAll(allParam.keySet());
            allParam.putAll(i18nParams);
        }

        try {
            ExceptionEnum exceptionEnum = ExceptionEnum.valueOf(messageCode);
            return DefaultPlaceholderHandler.defaultInstance().parseStringWithMap(exceptionEnum.defaultDesc, allParam);
        } catch (Exception e) {
            StringBuilder query = new StringBuilder(messageCode);
            if (allParam.size() > 0) {
                query.append(StringConstant.CHAR_OPEN_BRACE);
                for (Map.Entry<String, Object> entry : allParam.entrySet()) {
                    query.append(entry.getKey()).append(StringConstant.CHAR_COLON).append(entry.getValue())
                            .append(StringConstant.CHAR_SEMICOLON);
                }
                query.append(StringConstant.CHAR_CLOSE_BRACE);
            }
            return query.toString();
        }

    }

    /**
     * 解析消息（数组形式的参数）
     *
     * @param messageCode 消息编码
     * @param params      参数
     * @return 国际化消息
     */
    public static final String resolve(String messageCode, Object... params) {
        for (I18nMsgResolver i18nMsgResolver : resolverList) {
            BaseResult<String> result = i18nMsgResolver.resolveArray(messageCode, params);
            if (result.isSuccess() || !result.wantContinue()) {
                return result.getData();
            }
        }
        return null;
    }

}
