package com.quinn.util.constant.enums;

/**
 * 消息等级
 *
 * @author Qunhua.Liao
 * @since 2020-03-29
 */
public enum MessageLevelEnum {

    // 致命
    FATAL(900),

    // 错误
    ERROR(800),

    // 警告
    WARN(700),

    // 通知
    INFO(500),

    // 调试
    DEBUG(300),

    // 追踪
    TRACE(100),

    // 无
    NONE(0)
    ;

    public final int status;

    MessageLevelEnum(int status) {
        this.status = status;
    }

}
