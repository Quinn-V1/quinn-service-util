package com.quinn.util.constant.enums;

/**
 * 默认选项枚举
 *
 * @author Qunhua.Liao
 * @since 2020-03-31
 */
public enum DataStatusEnum {

    // 归档
    ARCHIVE("归档", 0),

    // 无
    DISABLED("禁用", 5),

    // 全部
    ENABLED("启用", 10),

    ;

    /**
     * 描述
     */
    public final String desc;

    /**
     * 编码
     */
    public final int code;

    DataStatusEnum(String desc, int code) {
        this.desc = desc;
        this.code = code;
    }

}
