package com.quinn.util.base.exception;

import com.quinn.util.base.enums.CommonMessageEnum;

/**
 * 开发许可未授权
 *
 * @author Qunhua.Liao
 * @since 2020-04-04
 */
public class LicenceException extends BaseBusinessException {

    {
        buildParam(CommonMessageEnum.LICENCE_EXCEPTION.name(), 0, 1);
    }

    public LicenceException() {
    }

    public LicenceException(String message) {
        super(message);
    }

    public LicenceException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
