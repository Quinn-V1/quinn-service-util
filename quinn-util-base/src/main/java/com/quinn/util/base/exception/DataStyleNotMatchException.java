package com.quinn.util.base.exception;

import com.quinn.util.constant.enums.ExceptionEnum;

/**
 * 数据样式不匹配异常
 *
 * @author Qunhua.Liao
 * @since 2020-03-29
 */
public class DataStyleNotMatchException extends BaseBusinessException {

    {
        buildParam(ExceptionEnum.DATA_STYLE_NOT_MATCHED.name(), 1, 1);
    }

    public DataStyleNotMatchException() {
        super();
    }

    public DataStyleNotMatchException(String message) {super(message);}

    public DataStyleNotMatchException(String message, Throwable throwable) {super(message, throwable);}

}
