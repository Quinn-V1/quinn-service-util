package com.quinn.util.base.exception;

import com.quinn.util.constant.enums.CommonMessageEnum;

/**
 * 方法未找到异常
 *
 * @author Qunhua.Liao
 * @since 2020-04-04
 */
public class MethodNotFoundException extends BaseBusinessException {

    {
        buildParam(CommonMessageEnum.METHOD_NOT_FOUND.key(), 2, 0);
    }

    public MethodNotFoundException() {
    }

    public MethodNotFoundException(String message) {
        super(message);
    }

    public MethodNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
