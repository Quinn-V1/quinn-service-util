package com.quinn.util.constant.enums;

/**
 * 许可证异常类型
 *
 * @author Qunhua.Liao
 * @since 2020-04-19
 */
public enum LicenceExceptionType {

    // 未授权
    UNAUTHORIZED("未授权", -90),

    // 文件损坏
    FILE_DESTROYED("文件损坏", -80),

    // 授权过期
    AUTHORIZE_EXPIRE("授权过期", -70),

    ;

    /**
     * 描述
     */
    public final String desc;

    /**
     * 编码
     */
    public final int code;

    LicenceExceptionType(String desc, int code) {
        this.desc = desc;
        this.code = code;
    }

}
