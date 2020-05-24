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

    // 英文-美
    en_US(Locale.US),
    ;

    public Locale locale;

    LanguageEnum(Locale locale) {
        this.locale = locale;
    }

}
