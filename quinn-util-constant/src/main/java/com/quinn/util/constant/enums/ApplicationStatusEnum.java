package com.quinn.util.constant.enums;

/**
 * 应用状态枚举
 *
 * @author Qunhua.Liao
 * @since 2020-03-31
 */
public enum  ApplicationStatusEnum {

    // 停止
    STOPED(0),

    // 启动中
    STARTING(100),

    // 启动了
    STARTED(300),

    // 失联
    WAITING(500),

    // 停止中
    STOPING(700),

    ;

    /**
     * 状态值
     */
    private final int status;

    ApplicationStatusEnum(int status) {
        this.status = status;
    }
}
