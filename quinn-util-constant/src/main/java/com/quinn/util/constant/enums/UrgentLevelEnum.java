package com.quinn.util.constant.enums;

/**
 * 紧急程度枚举类
 *
 * @author Qunhua.Liao
 * @since 2020-05-11
 */
public enum  UrgentLevelEnum {

    // 非常紧急
    HIGH_V2(100),

    // 比较紧急
    HIGH_V1(50),

    // 一般
    NORMAL(30),

    // 不紧急
    LOW(0),
    ;

    /**
     * 紧急程度代码（越高越紧急）
     */
    public final int code;

    UrgentLevelEnum(int code) {
        this.code = code;
    }

}
