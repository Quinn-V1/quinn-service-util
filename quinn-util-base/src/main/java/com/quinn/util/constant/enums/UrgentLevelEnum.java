package com.quinn.util.constant.enums;

/**
 * 紧急程度枚举类
 *
 * @author Qunhua.Liao
 * @since 2020-05-11
 */
public enum UrgentLevelEnum {

    // 一般
    NORMAL(30),

    // 比较紧急
    HIGH_V1(50),

    // 非常紧急
    HIGH_V2(100),

    // 不紧急
    LOW(10),
    ;

    /**
     * 紧急程度代码（越高越紧急）
     */
    public final int code;

    UrgentLevelEnum(int code) {
        this.code = code;
    }

    /**
     * 通过字符串带出数字等级
     *
     * @param urgentLevel 字符串等级
     * @return 数字等级
     */
    public static int codeByName(String urgentLevel) {
        if (urgentLevel == null || urgentLevel.isEmpty()) {
            return NORMAL.code;
        }

        for (UrgentLevelEnum level : values()) {
            if (level.name().equals(urgentLevel)) {
                return level.code;
            }
        }

        return NORMAL.code;
    }
}
