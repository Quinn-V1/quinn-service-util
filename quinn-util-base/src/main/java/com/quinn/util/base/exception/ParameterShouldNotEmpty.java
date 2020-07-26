package com.quinn.util.base.exception;

import com.quinn.util.constant.enums.CommonMessageEnum;

/**
 * 参数不可为空异常
 *
 * @author Qunhua.Liao
 * @since 2020-04-02
 */
public class ParameterShouldNotEmpty extends BaseBusinessException {

    {
        buildParam(CommonMessageEnum.PARAM_SHOULD_NOT_NULL.name(), 1, 0);
    }

    public ParameterShouldNotEmpty() {
    }

    public ParameterShouldNotEmpty(String message) {
        super(message);
    }

    public ParameterShouldNotEmpty(String message, Throwable throwable) {
        super(message, throwable);
    }
}
