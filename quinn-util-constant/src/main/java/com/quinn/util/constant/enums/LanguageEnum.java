package com.quinn.util.constant.enums;

import java.util.Locale;

/**
 * 语言枚举类
 *
 * @author Qunhua.Liao
 * @since 2020-05-24
 */
public enum LanguageEnum {

    // 中文-简
    zh_CN(Locale.SIMPLIFIED_CHINESE),

    // 中文-默认
    zh(Locale.SIMPLIFIED_CHINESE),

    // 英文-英
    en(Locale.ENGLISH),

    // 随用户
    by_user(null),

    ;

    public Locale locale;

    LanguageEnum(Locale locale) {
        this.locale = locale;
    }

    /**
     * 如果没有语言找最近的
     *
     * @param locale 原语言
     * @return 最近语言
     */
    public static Locale closestLocale(Locale locale) {
        if (locale == null) {
            return zh_CN.locale;
        }

        for (LanguageEnum languageEnum : LanguageEnum.values()) {
            if (locale.equals(languageEnum.locale) || locale.toString().equals(languageEnum.name())) {
                return locale;
            }
        }

        Locale result = Locale.forLanguageTag(locale.getLanguage());
        if (result != null) {
            return result;
        }

        return zh_CN.locale;
    }

    public static void main(String[] args) {
        System.out.println(Locale.US.getLanguage());
    }
}
