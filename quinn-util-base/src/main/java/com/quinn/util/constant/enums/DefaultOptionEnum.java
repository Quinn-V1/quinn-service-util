package com.quinn.util.constant.enums;

/**
 * 默认选项枚举
 *
 * @author Qunhua.Liao
 * @since 2020-03-31
 */
public enum DefaultOptionEnum {

    // 全部
    ALL("全部"),

    // 无
    NONE("无"),

    // 无
    OTHER("其他"),

    ;

    /**
     * 描述
     */
    public final String desc;

    DefaultOptionEnum(String desc) {
        this.desc = desc;
    }

}
