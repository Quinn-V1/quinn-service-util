package com.quinn.util.constant.enums;

/**
 * 数据同步状态
 *
 * @author Qunhua.Liao
 * @since 2020-05-01
 */
public enum SyncStatusEnum {

    // 未同步
    NOT_SYNCHRONIZED("未同步", 1),

    // 已修改
    ALREADY_EDITED("已修改", 9),

    // 已同步
    SYNCED("已同步", 10),

    ;

    /**
     * 描述
     */
    public final String desc;

    /**
     * 编码
     */
    public final int code;

    SyncStatusEnum(String desc, int code) {
        this.desc = desc;
        this.code = code;
    }

}
