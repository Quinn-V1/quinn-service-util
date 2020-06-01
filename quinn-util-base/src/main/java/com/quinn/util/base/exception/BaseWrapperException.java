package com.quinn.util.base.exception;

import com.quinn.util.base.enums.CommonMessageEnum;

/**
 * 关键信息缺失异常
 *
 * @author Qunhua.Liao
 * @since 2020-04-04
 */
public class BaseWrapperException extends BaseBusinessException {

    {
        buildParam(CommonMessageEnum.KEY_INFO_MISS.key(), 0, 2);
    }

    public BaseWrapperException() {
    }

    public BaseWrapperException(Exception e) {
        super(e);
    }

    public BaseWrapperException(String message) {
        super(message);
    }

    public BaseWrapperException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
