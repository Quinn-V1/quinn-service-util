package com.quinn.util.base.exception;

import com.quinn.util.constant.enums.ExceptionEnums;

/**
 * 开发许可未授权
 *
 * @author Qunhua.Liao
 * @since 2020-04-04
 */
public class LicenceUnauthorizedException extends BaseBusinessException {

    {
        buildParam(ExceptionEnums.LICENCE_EXCEPTION.name(), 0, 1);
    }

    public LicenceUnauthorizedException() {
    }

    public LicenceUnauthorizedException(String message) {
        super(message);
    }

    public LicenceUnauthorizedException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
