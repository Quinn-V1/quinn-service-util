package com.quinn.util.base.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quinn.util.base.StringUtil;
import com.quinn.util.constant.CharConstant;
import com.quinn.util.constant.enums.MessageLevelEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 基础结果信息
 *
 * @author Qunhua.Liao
 * @since 2020-03-27
 */
@Setter
@Getter
@ApiModel("单体操作结果")
public class BaseResult<T> {

    /**
     * 通用成功接口（成功的方式大同小异，使用一个就可以；失败的方式千差万别，不做常量）
     */
    public static final BaseResult SUCCESS = new BaseResult();

    public BaseResult() {
    }

    /**
     * 是否成功
     */
    @ApiModelProperty("是否成功")
    private boolean success = true;

    /**
     * 消息等级
     */
    @ApiModelProperty("消息等级：900致命、800错误、700警告、500通知、300调试、100追踪、0无")
    private int level = MessageLevelEnum.TRACE.status;

    /**
     * 消息
     */
    @ApiModelProperty("消息内容")
    private String message;

    /**
     * 业务数据
     */
    @ApiModelProperty("业务数据")
    private T data;

    /**
     * 消息条目：用于国际化
     */
    @JsonIgnore
    private BaseResultMessageProp messageProp;

    /**
     * 从前一个结果衍生新的结果
     *
     * @param prev 前置结果
     * @return 结果
     */
    public static BaseResult fromPrev(BaseResult prev) {
        BaseResult result = new BaseResult();
        result.setSuccess(prev.isSuccess());
        result.setMessage(prev.getMessage());
        result.setData(prev.getData());
        result.setMessageProp(prev.getMessageProp());
        result.setLevel(prev.getLevel());
        return result;
    }

    /**
     * 从前一个结果衍生新的结果
     *
     * @param prev 前置结果
     * @return 结果
     */
    public static BaseResult fromPrev(BaseResult prev, String messageOrCode, int paramSize, int i18nParamSize) {
        BaseResult result = new BaseResult();
        result.setSuccess(prev.isSuccess());

        if (StringUtil.isEmpty(messageOrCode)) {
            result.setMessage(prev.getMessage());
            result.setData(prev.getData());
            result.setMessageProp(prev.getMessageProp());
            result.setLevel(prev.getLevel());
        } else {
            if (prev.getMessageProp() != null) {
                result.buildMessage(messageOrCode, paramSize, i18nParamSize).ofPrevProp(prev.getMessageProp());
            } else {
                String message = prev.getMessage();
                if (message != null && message.length() > 0) {
                    result.setMessage(message + CharConstant.LINE_BREAK + messageOrCode);
                }
            }
        }
        return result;
    }

    /**
     * 产生失败结果
     *
     * @return 失败结果
     */
    public static BaseResult fail() {
        BaseResult result = new BaseResult();
        result.setSuccess(false);
        return result;
    }

    /**
     * 产生失败结果
     *
     * @param message 消息
     * @return 失败结果
     */
    public static BaseResult fail(String message) {
        BaseResult result = new BaseResult();
        result.setSuccess(false);
        result.setMessage(message);
        return result;
    }

    /**
     * 构建结果
     *
     * @param success 是否成功
     * @return 结果
     */
    public static BaseResult build(boolean success) {
        BaseResult result = new BaseResult();
        result.setSuccess(success);
        return result;
    }

    /**
     * 成功方法
     *
     * @param data 数据
     * @param <T>  数据泛型
     * @return 结果
     */
    public static <T> BaseResult<T> success(T data) {
        BaseResult result = new BaseResult();
        result.setData(data);
        return result;
    }

    /**
     * 设置消息级别
     *
     * @param success 结果
     * @return 结果本身
     */
    public BaseResult ofSuccess(boolean success) {
        this.success = success;
        return this;
    }

    /**
     * 设置消息级别
     *
     * @param level 消息级别
     * @return 结果本身
     */
    public BaseResult ofLevel(MessageLevelEnum level) {
        this.level = level.status;
        return this;
    }

    /**
     * 链式设置业务数据
     *
     * @param message 业务数据
     * @return 本身
     */
    public BaseResult ofMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * 链式设置业务数据
     *
     * @param data 业务数据
     * @return 本身
     */
    public BaseResult ofData(T data) {
        this.data = data;
        return this;
    }

    /**
     * 构建消息属性
     * BaseResult.build(true).buildMessage("ERROR001").addParam("workNo", "WO0001")
     * .addI18nParam("WORK_NO_NAME", "WORK_NO_NAME").result();
     *
     * @param messageCode 消息编码
     * @return 消息属性
     */
    public BaseResultMessageProp buildMessage(String messageCode, int paramSize, int i18nParamSize) {
        if (this.messageProp == null) {
            this.messageProp = (BaseResultMessageProp) new BaseResultMessageProp(this, messageCode)
                    .paramSize(paramSize).i18nParamSize(i18nParamSize);
        }
        return messageProp;
    }

    /**
     * 链式处理中，后续逻辑是否继续的判断依据
     * 中断后续逻辑，不仅仅在前置逻辑出错的情况下会出现，还有可以，前面已经找到结果，就没必要再往后找
     *
     * @return 是否要继续
     */
    public boolean wantContinue() {
        return !this.success || this.level < MessageLevelEnum.WARN.status;
    }

    /**
     * 拼接消息
     *
     * @param message 新消息
     */
    public void appendMessage(String message) {
        if (StringUtil.isEmpty(message)) {
            return;
        }

        if (StringUtil.isNotEmpty(message)) {
            this.message = message;
        } else {
            this.message = this.message + CharConstant.LINE_BREAK + message;
        }
    }

    /**
     * 凭借前一个结果（一般用于错误消息传递）
     *
     * @param prev
     */
    public void appendPrev(BaseResult prev) {
        if (!prev.isSuccess()) {
            setSuccess(false);
        }

        appendMessage(prev.getMessage());
        if (messageProp != null) {
            messageProp.ofPrevProp(prev.getMessageProp());
        } else {
            messageProp = prev.getMessageProp();
        }
    }

    /**
     * 业务也常消息条目
     *
     * @author Qunhua.Liao
     * @since 2020-03-30
     */
    public class BaseResultMessageProp extends MessageProp<BaseResult> {

        /**
         * 构造器
         *
         * @param host        宿主
         * @param messageCode 消息编码
         */
        public BaseResultMessageProp(BaseResult host, String messageCode) {
            super(host, messageCode);
        }

        /**
         * 带参构造器
         *
         * @param host        宿主
         * @param messageProp 消息属性
         */
        public BaseResultMessageProp(BaseResult host, MessageProp messageProp) {
            super(host, null);
            if (messageProp != null) {
                setMessageCode(messageProp.getMessageCode());
                setParams(messageProp.getParams());
                setI18nParams(messageProp.getI18nParams());
                setPrevProp(messageProp.getPrevProp());
            }
        }

        @Override
        public BaseResultMessageProp addParam(Object key, Object value) {
            return (BaseResultMessageProp) super.addParam(key, value);
        }

        @Override
        public BaseResultMessageProp addParamI8n(Object key, Object value) {
            return (BaseResultMessageProp) super.addParamI8n(key, value);
        }

        @Override
        public BaseResultMessageProp ofPrevProp(MessageProp prevProp) {
            return (BaseResultMessageProp) super.ofPrevProp(prevProp);
        }

        /**
         * 获取异常
         *
         * @return
         */
        public <T> BaseResult<T> result() {
            return getHost();
        }

    }

}
