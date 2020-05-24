package com.quinn.util.base.handler;

import com.quinn.util.base.StringUtil;
import com.quinn.util.base.api.MessageResolver;
import com.quinn.util.base.model.BaseResult;
import com.quinn.util.base.model.MessageProp;
import com.quinn.util.constant.CharConstant;

import java.util.*;

/**
 * 多元国际化消息解析器
 *
 * @author Qunhua.Liao
 * @since 2020-04-20
 */
public final class MultiMessageResolver {

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
            String messageCode = messageProp.getMessageCode();
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

        for (MessageResolver resolver : resolverList) {
            BaseResult<String> res = resolver.resolveMessage(locale, messageCode, params, i18nParams, messageCode);
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
