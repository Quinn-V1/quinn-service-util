package com.quinn.util.constant.enums;

/**
 * 系统异常退出类型
 *
 * @author Qunhua.Liao
 * @since 2020-04-18
 */
public enum  SystemExitTypeEnum {

    // 网络故障
    NETWORK_ERROR(-100),

    // 许可证故障
    LICENCE_ERROR(-200),
    ;

    public final int code;

    SystemExitTypeEnum(int code) {
        this.code = code;
    }

}
