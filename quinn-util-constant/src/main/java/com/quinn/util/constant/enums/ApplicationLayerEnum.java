package com.quinn.util.constant.enums;

/**
 * 服务分层枚举类
 *
 * @author Qunhua.Liao
 * @since 2020-03-31
 */
public enum  ApplicationLayerEnum {

    // 设备层
    DEVICE(100),

    // 网络层
    NETWORK(200),

    // 数据层
    DATA(300),

    // 中台
    CENTER(400),

    // 平台
    PLATFORM(500),

    // 业务
    BUSINESS(600),

    // 网关
    GATEWAY(700),

    // 展示
    SHOW(800),

    ;

    private final int level;

    ApplicationLayerEnum(int level) {
        this.level = level;
    }
}
