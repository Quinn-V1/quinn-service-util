package com.quinn.util.base.util;

import com.quinn.util.constant.StringConstant;

import java.util.Properties;

/**
 * 国际化处理
 *
 * @author QUnhua.Liao
 * @since 2020-03-31
 */
public class I18nUtil {

    /**
     * 判断一个键就是否要国际化
     *
     * @param key   键
     * @return      是否就国际化
     */
    public static boolean isI18nKey(String key) {
        return key.startsWith(StringConstant.SYSTEM_I18N_KEY_PREFIX);
    }

    /**
     * 将一个字符串转为国际化键
     *
     * @param key   键
     * @return      国际化键
     */
    private static String i18nKey(String key) {
        if (isI18nKey(key)) {
            return key;
        }
        return StringConstant.SYSTEM_I18N_KEY_PREFIX + key;
    }

    /**
     * 清除国际化标识
     *
     * @param key   键
     * @return      清除后人值
     */
    public static String clearI18nKey(String key) {
        if (!isI18nKey(key)) {
            return key;
        }
        return key.substring(StringConstant.SYSTEM_I18N_KEY_PREFIX.length());
    }

    /**
     * 尝试获取国际化描述
     *
     * @param key           键
     * @param properties    属性池
     */
    public static String tryGetI18n(String key, Properties properties) {
        if (key == null || isI18nKey(key) || properties == null) {
            return key;
        }

        String value = properties.getProperty(key.substring(StringConstant.SYSTEM_I18N_KEY_PREFIX.length()));
        return (value == null || "".equals(value)) ? key : value;
    }

}
