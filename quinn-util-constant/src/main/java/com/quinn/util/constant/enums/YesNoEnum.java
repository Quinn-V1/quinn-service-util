package com.quinn.util.constant.enums;

/**
 * 是否枚举类
 *
 * @author Qunhua.Liao
 * @since 2020-05-09
 */
public enum  YesNoEnum {

    // 是
    YES(true, "Y"),

    // 否
    NO(false, "N"),

    ;

    /**
     * 是否布尔值
     */
    public final boolean value;

    /**
     * 是否简码
     */
    public final String code;

    YesNoEnum(boolean value, String code) {
        this.value = value;
        this.code = code;
    }

}
