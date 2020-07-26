package com.quinn.util.constant.enums;

/**
 * 数据库操作类型枚举
 *
 * @author Qunhua.Liao
 * @since 2020-04-20
 */
public enum DbOperateTypeEnum {

    // 增
    INSERT,

    // 删-硬
    DELETE_HARD,

    // 删-软
    DELETE_SOFT,

    // 改-全
    UPDATE_ALL,

    // 改-非空
    UPDATE_NON_EMPTY,

    // 恢复-硬
    RECOVERY_HARD,

    // 恢复-软
    RECOVERY_SOFT,

    ;

}
