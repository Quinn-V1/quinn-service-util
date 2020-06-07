package com.quinn.util.base.enums;

/**
 * 消息状态枚举
 *
 * @author Qunhua.Liao
 * @since 2020-02-08
 */
public enum MessageStateEnum {

    // 新建
    CREATE(0),

    // 发送失败
    SEND_FAIL(5),

    // 发送成功
    SEND_SUCCESS(10),

    // 标记未读
    UNREAD(19),

    // 已读
    READ(20),

    // 撤回
    REVOKE(90);

    /**
     * 状态编码，具有顺序含义
     */
    public final int code;

    MessageStateEnum(int code) {
        this.code = code;
    }

}
