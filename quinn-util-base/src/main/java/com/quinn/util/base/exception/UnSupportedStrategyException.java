package com.quinn.util.base.exception;

import com.quinn.util.base.enums.CommonMessageEnum;

/**
 * 不支持的方法
 *
 * @author Qunhua.Liao
 * @since 2020-03-28
 */
public class UnSupportedStrategyException extends BaseBusinessException {

    {
        buildParam(CommonMessageEnum.STRATEGY_NOT_SUPPORTED.key(), 1, 0);
    }

    public UnSupportedStrategyException() {
        super();
    }

    public UnSupportedStrategyException(String message) {
        super(message);
    }

    public UnSupportedStrategyException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
