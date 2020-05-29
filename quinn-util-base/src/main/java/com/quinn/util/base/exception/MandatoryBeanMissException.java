package com.quinn.util.base.exception;

import com.quinn.util.base.enums.CommonMessageEnum;

/**
 * 关键信息缺失异常
 *
 * @author Qunhua.Liao
 * @since 2020-04-04
 */
public class MandatoryBeanMissException extends BaseBusinessException {

    {
        buildParam(CommonMessageEnum.MANDATORY_BEAN_MISS.name(), 1, 0);
    }

    public MandatoryBeanMissException() {
    }

    public MandatoryBeanMissException(String message) {
        super(message);
    }

    public MandatoryBeanMissException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
