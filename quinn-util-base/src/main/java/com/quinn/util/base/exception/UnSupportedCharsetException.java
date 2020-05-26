package com.quinn.util.base.exception;

import com.quinn.util.base.enums.CommMessageEnum;

/**
 * 不支持的方法
 *
 * @author Qunhua.Liao
 * @since 2020-03-28
 */
public class UnSupportedCharsetException extends BaseBusinessException {

    {
        buildParam(CommMessageEnum.CHARSET_NOT_SUPPORTED.name(), 1, 0);
    }

    public UnSupportedCharsetException() {
        super();
    }

    public UnSupportedCharsetException(String message) {
        super(message);
    }

    public UnSupportedCharsetException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
