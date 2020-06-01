package com.quinn.util.base.exception;

import com.quinn.util.base.enums.CommonMessageEnum;

/**
 * 主数据丢失异常
 *
 * @author Qunhua.Liao
 * @since 2020-05-20
 */
public class MasterDataLostException extends BaseBusinessException {

    {
        buildParam(CommonMessageEnum.MASTER_DATA_LOST.key(), 1, 1);
    }

    public MasterDataLostException() {
    }

    public MasterDataLostException(String message) {
        super(message);
    }

    public MasterDataLostException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
