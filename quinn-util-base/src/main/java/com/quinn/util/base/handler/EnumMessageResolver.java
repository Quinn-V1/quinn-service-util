package com.quinn.util.base.handler;

import com.quinn.util.constant.MessageEnumFlag;
import com.quinn.util.constant.OrderedConstant;

import java.util.Locale;
import java.util.Properties;

/**
 * 枚举类解析消息器
 *
 * @author Qunhua.Liao
 * @since 2020-05-24
 */
public class EnumMessageResolver extends AbstractMessageResolver {

    @Override
    public Properties getProperties(Locale locale) {
        Properties properties = localeMessagesMap.get(locale);
        if (properties == null) {
            return defaultProperties;
        }
        return null;
    }

    @Override
    public int priority() {
        return OrderedConstant.HIGHER_V2 - OrderedConstant.HIGHER_V1;
    }

    /**
     * 添加内容
     *
     * @param locale       语言
     * @param messageEnums 枚举
     */
    public static void addContent(Locale locale, MessageEnumFlag[] messageEnums) {
        Properties prop = localeMessagesMap.get(locale);
        if (prop == null) {
            prop = new Properties();
            localeMessagesMap.put(locale, prop);
        }

        if (Locale.getDefault().equals(locale)) {
            defaultProperties = prop;
        } else if (defaultProperties == null) {
            defaultProperties = prop;
        }

        for (MessageEnumFlag messageEnum : messageEnums) {
            prop.put(messageEnum.name(), messageEnum.defaultDesc());
        }
    }
}
