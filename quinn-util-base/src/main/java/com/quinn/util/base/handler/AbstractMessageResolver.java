package com.quinn.util.base.handler;

import com.quinn.util.base.CollectionUtil;
import com.quinn.util.base.I18nUtil;
import com.quinn.util.base.StringUtil;
import com.quinn.util.base.api.MessageResolver;
import com.quinn.util.base.convertor.BaseConverter;
import com.quinn.util.base.exception.BaseBusinessException;
import com.quinn.util.base.model.BaseResult;
import com.quinn.util.base.model.MessageProp;

import java.util.*;

/**
 * @author HUAWEI
 */
public abstract class AbstractMessageResolver implements MessageResolver {

    /**
     * 按语言区分的国际化配置（其他语言懒加载）
     */
    protected final static Map<Locale, Properties> localeMessagesMap = new HashMap<>();

    /**
     * 默认的属性
     */
    protected static Properties defaultProperties;

    /**
     * 占位符号解析器
     */
    protected PlaceholderHandler placeholderHandler;

    @Override
    public BaseResult<String> resolveMessage(
            Locale locale, String messageCode, Map params, Map i18nParams, String defaultMessage) {

        Properties properties = getProperties(locale);
        if (properties == null) {
            return BaseResult.fail(defaultMessage);
        }

        String result = properties.getProperty(messageCode);
        if (StringUtil.isEmpty(result)) {
            result = defaultMessage;
        }

        if (CollectionUtil.isEmpty(params) && CollectionUtil.isEmpty(i18nParams)) {
            return BaseResult.success(result);
        }

        Map<String, Object> directParam = new HashMap<>();
        if (params != null) {
            directParam.putAll(params);
        }

        if (!CollectionUtil.isEmpty(i18nParams)) {
            Set<Map.Entry<String, String>> set = i18nParams.entrySet();
            for (Map.Entry<String, String> entry : set) {
                String value = properties.getProperty(entry.getValue());
                value = StringUtil.isEmpty(value) ? entry.getValue() : value;
                directParam.put(entry.getKey(), value);
            }
        }

        if (!directParam.isEmpty()) {
           result = placeholderHandler.parseStringWithMap(result, directParam);
        }

        return BaseResult.success(result);
    }

    @Override
    public BaseResult<String> resolveMessageProp(Locale locale, MessageProp messageProp, String defaultMessage) {
        return resolveMessage(locale, messageProp.getMessageCode(), messageProp.getParams(),
                messageProp.getI18nParams(), defaultMessage);
    }

    @Override
    public BaseResult<String> resolveResult(Locale locale, BaseResult result) {
        return resolveMessageProp(locale, result.getMessageProp(), result.getMessage());
    }

    @Override
    public BaseResult<String> resolveException(Locale locale, BaseBusinessException exception) {
        return resolveMessageProp(locale, exception.getMessageProp(), exception.getMessage());
    }

    @Override
    public BaseResult<String> resolveMessageWithArrayParams(
            Locale locale, String messageCode, String defaultMessage, Object... args) {
        Properties properties = getProperties(locale);
        if (properties == null) {
            return BaseResult.fail(defaultMessage);
        }

        String result = properties.getProperty(defaultMessage);
        if (CollectionUtil.isEmpty(args)) {
            return BaseResult.fail(result);
        }

        for (int i = 0; i < args.length; i++) {
            args[i] = I18nUtil.tryGetI18n(BaseConverter.staticToString(args[i]), properties);
        }

        result = placeholderHandler.parseStringWithArray(result, args);
        if (placeholderHandler.isComplete(result)) {
            return BaseResult.success(result);
        } else {
            return BaseResult.fail(result);
        }
    }

    /**
     * 获取国际化配置
     *
     * @param locale 语言
     * @return 国际化配置
     */
    public abstract Properties getProperties(Locale locale);

}
