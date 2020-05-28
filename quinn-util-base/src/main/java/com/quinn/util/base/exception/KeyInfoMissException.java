package com.quinn.util.base.exception;

import com.quinn.util.base.enums.CommonMessageEnum;

/**
 * 关键信息缺失异常
 *
 * @author Qunhua.Liao
 * @since 2020-04-04
 */
public class KeyInfoMissException extends BaseBusinessException {

    {
        buildParam(CommonMessageEnum.KEY_INFO_MISS.name(), 0, 2);
    }

    public KeyInfoMissException() {
    }

    public KeyInfoMissException(String message) {
        super(message);
    }

    public KeyInfoMissException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
