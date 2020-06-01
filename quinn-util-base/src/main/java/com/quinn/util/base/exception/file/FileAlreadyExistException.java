package com.quinn.util.base.exception.file;

import com.quinn.util.base.exception.BaseBusinessException;
import com.quinn.util.base.enums.CommonMessageEnum;

/**
 * 文件不存在异常
 *
 * @author Qunhua.Liao
 * @since 2020-04-04
 */
public class FileAlreadyExistException extends BaseBusinessException {

    {
        buildParam(CommonMessageEnum.FILE_DIRECTORY_OCCUPIED_BY_FILE.key(), 1, 0);
    }

    public FileAlreadyExistException() {
    }

    public FileAlreadyExistException(String message) {
        super(message);
    }

    public FileAlreadyExistException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
