package com.quinn.util.base.exception.file;

import com.quinn.util.base.exception.BaseBusinessException;
import com.quinn.util.constant.enums.CommonMessageEnum;

/**
 * 文件不存在异常
 *
 * @author Qunhua.Liao
 * @since 2020-04-04
 */
public class FileOperationException extends BaseBusinessException {

    {
        buildParam(CommonMessageEnum.FILE_STREAM_OPERATION_FAIL.key(), 1, 1);
    }

    public FileOperationException() {
    }

    public FileOperationException(String message) {
        super(message);
    }

    public FileOperationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
