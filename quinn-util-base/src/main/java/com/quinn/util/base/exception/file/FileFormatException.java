package com.quinn.util.base.exception.file;

import com.quinn.util.constant.enums.CommonMessageEnum;
import com.quinn.util.base.exception.BaseBusinessException;

/**
 * 文件格式不正确
 *
 * @author Simon.z
 * @since 2020/06/08
 */
public class FileFormatException extends BaseBusinessException {

    public FileFormatException() {
        this.buildParam(CommonMessageEnum.FILE_FORMAT_NOT_CORRECT.key(), 1, 0);
    }

    public FileFormatException(String message) {
        super(message);
        this.buildParam(CommonMessageEnum.FILE_FORMAT_NOT_CORRECT.key(), 1, 0);
    }

    public FileFormatException(String message, Throwable throwable) {
        super(message, throwable);
        this.buildParam(CommonMessageEnum.FILE_FORMAT_NOT_CORRECT.key(), 1, 0);
    }
}
