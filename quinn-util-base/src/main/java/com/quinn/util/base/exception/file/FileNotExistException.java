package com.quinn.util.base.exception.file;

import com.quinn.util.base.exception.BaseBusinessException;
import com.quinn.util.constant.enums.CommonMessageEnum;

/**
 * 文件不存在异常
 *
 * @author Qunhua.Liao
 * @since 2020-04-04
 */
public class FileNotExistException extends BaseBusinessException {

    {
        buildParam(CommonMessageEnum.FILE_NOT_EXIST.key(), 1, 0);
    }

    public FileNotExistException() {
    }

    public FileNotExistException(String message) {
        super(message);
    }

    public FileNotExistException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
