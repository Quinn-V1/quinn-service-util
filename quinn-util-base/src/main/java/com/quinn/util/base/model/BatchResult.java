package com.quinn.util.base.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quinn.util.base.CollectionUtil;
import com.quinn.util.constant.enums.MessageLevelEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 批处理结果信息
 *
 * @author Qunhua.Liao
 * @since 2020-03-27
 */
@Setter
@Getter
@ApiModel("批处理结果")
public class BatchResult<T> implements Serializable {

    /**
     * 通用成功接口（成功的方式大同小异，使用一个就可以；失败的方式千差万别，不做常量）
     */
    public static final BatchResult SUCCESS = new BatchResult(0);

    /**
     * 有参构造器
     *
     * @param size 批处理任务条目数
     */
    public BatchResult(int size) {
        this.data = new BatchItems<T>(size);
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
     * 消息内容
     */
    @ApiModelProperty("消息内容")
    private String message;

    /**
     * 批处理整体结果
     */
    @JsonIgnore
    private BatchItems<T> data;

    /**
     * 最近一次操作的数据条目
     */
    @JsonIgnore
    private BatchItem<T> recentItem;

    /**
     * 从前一个结果衍生新的结果
     *
     * @param prev 前置结果
     * @return 结果
     */
    public static BatchResult fromPrev(BaseResult prev) {
        BatchResult result = new BatchResult(1);
        return result.addItem(prev);
    }

    /**
     * 构建消息记录
     *
     * @param t    数据
     * @param size 成功
     * @return 消息记录
     */
    public static <T> BatchResult<T> build(T t, boolean success, int size) {
        BatchResult<T> result = new BatchResult<>(size);
        result.data = result.new BatchItems<T>(size);
        result.addItem(t, success);
        return result;
    }

    /**
     * 添加成功消息
     *
     * @param t 业务数据
     */
    public BatchItem<T> successData(T t) {
        BatchItem<T> item = new BatchItem<>(t);
        this.data.getSuccessItems().add(item);
        this.recentItem = item;
        return item;
    }

    /**
     * 添加失败消息
     *
     * @param t 业务数据
     */
    public BatchItem<T> failData(T t) {
        BatchItem<T> item = new BatchItem<>(t);
        this.data.getFailItems().add(item);
        this.recentItem = item;
        this.success = false;
        return item;
    }

    /**
     * 通过单个结果增加记录
     *
     * @param t       数据
     * @param success 结果
     */
    public BatchItem<T> addItem(T t, boolean success) {
        if (success) {
            return successData(t);
        }
        return failData(t);
    }

    /**
     * 通过单个结果增加记录
     *
     * @param res 结果
     */
    public BatchResult<T> addItem(BaseResult<T> res) {
        BatchItem<T> batchItem = addItem(res.getData(), res.isSuccess())
                .buildMessage(res.getMessageProp())
                .ofLevel(res.getLevel());
        batchItem.setMessage(res.getMessage());
        return this;
    }

    /**
     * 批处理单条结果条目
     */
    @Setter
    @Getter
    public class BatchItem<T> {

        public BatchItem(T item) {
            this.item = item;
        }

        /**
         * 消息
         */
        private String message;

        /**
         * 消息等级
         */
        private int level = MessageLevelEnum.TRACE.status;

        /**
         * 业务数据
         */
        private T item;

        @JsonIgnore
        private BatchResultMessageProp messageProp;

        /**
         * 生成最终结果
         *
         * @return
         */
        public BatchResult result() {
            return BatchResult.this;
        }

        /**
         * 构建消息属性
         *
         * @param messageProp 消息编码
         * @return 消息属性
         */
        public BatchItem<T> buildMessage(MessageProp messageProp) {
            this.messageProp = new BatchResultMessageProp(BatchResult.this, messageProp);
            return this;
        }

        /**
         * 构建消息属性
         *
         * @param messageCode 消息编码
         * @return 消息属性
         */
        public BatchItem<T> buildMessage(String messageCode, int paramSize, int i18nParamSize) {
            this.messageProp = (BatchResultMessageProp) new BatchResultMessageProp(BatchResult.this, messageCode)
                    .paramSize(paramSize).i18nParamSize(i18nParamSize);
            return this;
        }

        public BatchItem<T> ofLevel(int level) {
            this.level = level;
            return this;
        }

        /**
         * 增加普通参数
         *
         * @param key   参数键
         * @param value 参数值
         * @return 本身
         */
        public BatchItem<T> addParam(Object key, Object value) {
            this.messageProp.addParam(key, value);
            return this;
        }

        /**
         * 增加国际化参数
         *
         * @param key   参数键
         * @param value 参数值
         * @return 本身
         */
        public BatchItem<T> addParamI8n(Object key, Object value) {
            this.messageProp.addParamI8n(key, value);
            return this;
        }

        /**
         * 创建下一条记录
         *
         * @param success 是否成功
         * @param t       业务数据
         * @return 数据条目
         */
        public BatchItem<T> nextItem(boolean success, T t) {
            BatchItem item = new BatchItem<>(t);
            if (success) {
                BatchResult.this.getData().getSuccessItems().add(item);
            } else {
                BatchResult.this.getData().getFailItems().add(item);
            }
            return item;
        }
    }

    /**
     * 批处理整体结果
     */
    @Setter
    @Getter
    public class BatchItems<T> {

        /**
         * 成功条目
         */
        private List<BatchItem<T>> successItems;

        /**
         * 失败条目
         */
        private List<BatchItem<T>> failItems;

        /**
         * 有参构造器
         *
         * @param size 批处理任务数
         */
        public BatchItems(int size) {
            this.successItems = new ArrayList<>(size);
            this.failItems = new ArrayList<>(0);
        }

        public boolean allSuccess() {
            return CollectionUtil.isEmpty(failItems);
        }
    }

    /**
     * 业务也常消息条目
     *
     * @author Qunhua.Liao
     * @since 2020-03-30
     */
    public static class BatchResultMessageProp extends MessageProp<BatchResult> {

        /**
         * 构造器
         *
         * @param host        宿主
         * @param messageCode 消息编码
         */
        public BatchResultMessageProp(BatchResult host, String messageCode) {
            super(host, messageCode);
        }

        /**
         * 带参构造器
         *
         * @param host        宿主
         * @param messageProp 消息属性
         */
        public BatchResultMessageProp(BatchResult host, MessageProp messageProp) {
            super(host, null);
            if (messageProp != null) {
                setMessageCode(messageProp.getMessageCode());
                setParams(messageProp.getParams());
                setI18nParams(messageProp.getI18nParams());
                setPrevProp(messageProp.getPrevProp());
            }
        }

        @Override
        public BatchResult.BatchResultMessageProp addParam(Object key, Object value) {
            return (BatchResult.BatchResultMessageProp) super.addParam(key, value);
        }

        @Override
        public BatchResult.BatchResultMessageProp addParamI8n(Object key, Object value) {
            return (BatchResult.BatchResultMessageProp) super.addParamI8n(key, value);
        }

        @Override
        public BatchResult.BatchResultMessageProp ofPrevProp(MessageProp prevProp) {
            return (BatchResult.BatchResultMessageProp) super.ofPrevProp(prevProp);
        }

        /**
         * 获取异常
         *
         * @return
         */
        public BatchResult result() {
            return (BatchResult) getHost();
        }

    }

}
