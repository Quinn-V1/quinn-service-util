package com.quinn.util.base.exception;

import com.quinn.util.base.model.BaseResult;
import com.quinn.util.base.model.MessageProp;
import lombok.Getter;
import lombok.Setter;

/**
 * 工具类异常
 *
 * @author Qunhua.Liao
 * @since 2020-03-29
 */
@Getter
@Setter
public class BaseBusinessException extends RuntimeException {

    public BaseBusinessException() {
        super();
    }

    public BaseBusinessException(Throwable e) {
        super(e);
    }

    public BaseBusinessException(String message) {
        this(message, true);
    }

    public BaseBusinessException(String message, boolean i18n) {
        super(message);
        if (i18n) {
            messageProp = new BusinessExceptionMessageProp(this, message);
        }
    }

    public BaseBusinessException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * 消息属性
     */
    private BusinessExceptionMessageProp messageProp;

    @Override
    public String getMessage() {
        if (messageProp == null) {
            return super.getMessage();
        }

        // TODO 待推究合理性
        return null;
    }

    /**
     * 结果转变异常
     *
     * @param result 结果
     * @return 异常
     */
    public static BaseBusinessException fromResult(BaseResult result) {
        return new BaseBusinessException(result.getMessage()) {
        }.buildParam(result.getMessageProp()).exception();
    }

    /**
     * 构造参数
     *
     * @return 参数构造器
     */
    public <T extends BaseBusinessException> BusinessExceptionMessageProp<T> buildParam(
            String messageCode, int paramSize, int i18nParamSize) {
        if (messageProp == null) {
            messageProp = (BusinessExceptionMessageProp) new BusinessExceptionMessageProp(this, messageCode)
                    .paramSize(paramSize).i18nParamSize(i18nParamSize);
        }
        return messageProp;
    }

    /**
     * 从消息属性构建消息属性
     *
     * @param messageProp 消息属性
     * @return 消息属性
     */
    public BusinessExceptionMessageProp buildParam(MessageProp messageProp) {
        return new BusinessExceptionMessageProp(this, messageProp);
    }

    /**
     * 从消息属性构建消息属性
     *
     * @param paramName  参数名称
     * @param paramValue 参数值
     * @return 消息属性
     */
    public BusinessExceptionMessageProp addParam(String paramName, Object paramValue) {
        if (this.messageProp == null) {
            this.messageProp = new BusinessExceptionMessageProp(this, "");
        }
        return messageProp.addParam(paramName, paramValue);
    }

    /**
     * 从消息属性构建消息属性
     *
     * @param paramName  参数名称
     * @param paramValue 参数值
     * @return 消息属性
     */
    public BusinessExceptionMessageProp addParamI8n(String paramName, Object paramValue) {
        if (this.messageProp == null) {
            this.messageProp = new BusinessExceptionMessageProp(this, "");
        }
        return messageProp.addParamI8n(paramName, paramValue);
    }

    /**
     * 业务也常消息条目
     *
     * @author Qunhua.Liao
     * @since 2020-03-30
     */
    public class BusinessExceptionMessageProp<T extends BaseBusinessException> extends MessageProp<T> {

        /**
         * 构造器
         *
         * @param host        宿主
         * @param messageCode 消息编码
         */
        public BusinessExceptionMessageProp(T host, String messageCode) {
            super(host, messageCode);
        }

        /**
         * 带参构造器
         *
         * @param host        宿主
         * @param messageProp 消息属性
         */
        public BusinessExceptionMessageProp(T host, MessageProp messageProp) {
            super(host, null);
            if (messageProp != null) {
                setMessageCode(messageProp.getMessageCode());
                setParams(messageProp.getParams());
                setI18nParams(messageProp.getI18nParams());
                setPrevProp(messageProp.getPrevProp());
            }
        }

        @Override
        public BusinessExceptionMessageProp addParam(Object key, Object value) {
            return (BusinessExceptionMessageProp) super.addParam(key, value);
        }

        @Override
        public BusinessExceptionMessageProp addParamI8n(Object key, Object value) {
            return (BusinessExceptionMessageProp) super.addParamI8n(key, value);
        }

        @Override
        public BusinessExceptionMessageProp ofPrevProp(MessageProp prevProp) {
            return (BusinessExceptionMessageProp) super.ofPrevProp(prevProp);
        }

        /**
         * 获取异常
         *
         * @return 宿主异常
         */
        public BaseBusinessException exception() {
            return getHost();
        }

    }

}
