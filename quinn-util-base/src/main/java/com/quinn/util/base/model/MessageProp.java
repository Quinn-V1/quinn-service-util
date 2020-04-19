package com.quinn.util.base.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息条目
 *
 * @author Qunhua.Liao
 * @since 2020-03-29
 */
@Setter
@Getter
public class MessageProp<T> {

    /**
     * 带参构造器
     *
     * @param host 宿主
     */
    public MessageProp(T host, String messageCode) {
        this.host = host;
        this.messageCode = messageCode;
    }

    /**
     * 宿主
     */
    private T host;

    /**
     * 消息编码
     */
    private String messageCode;

    /**
     * 前置消息属性
     */
    private MessageProp prevProp;

    /**
     * 消息参数
     */
    protected Map<Object, Object> params;

    /**
     * 国际化消息参数
     */
    protected Map<Object, Object> i18nParams;

    /**
     * 构造
     *
     * @param host          宿主
     * @param messageCode   消息编码
     * @param paramSize     参数大小
     * @param i18nParamSize 参数方案
     * @param <T>           宿主泛型
     * @return 消息条目
     */
    public static <T> MessageProp<T> build(T host, String messageCode, int paramSize, int i18nParamSize) {
        MessageProp messageProp = new MessageProp(host, messageCode);
        if (paramSize > 0 && messageProp.params == null) {
            messageProp.params = new HashMap(paramSize);
        }

        if (i18nParamSize > 0 && messageProp.i18nParams == null) {
            messageProp.i18nParams = new HashMap(i18nParamSize);
        }

        return messageProp;
    }

    /**
     * 设置普通参数大小
     *
     * @param paramSize 参数大小
     * @return 本身
     */
    public MessageProp paramSize(int paramSize) {
        if (params == null) {
            params = new HashMap<>(paramSize);
        }
        return this;
    }

    /**
     * 设置普通参数大小
     *
     * @param paramSize 参数大小
     * @return 本身
     */
    public MessageProp i18nParamSize(int paramSize) {
        if (i18nParams == null) {
            i18nParams = new HashMap<>(paramSize);
        }
        return this;
    }

    /**
     * 链式设置业务数据
     *
     * @param prevProp 业务数据
     * @return 本身
     */
    public MessageProp ofPrevProp(MessageProp prevProp) {
        this.prevProp = prevProp;
        return this;
    }

    /**
     * 增加普通参数
     *
     * @param key   参数键
     * @param value 参数值
     * @return 本身
     */
    public MessageProp addParam(Object key, Object value) {
        params.put(key, value);
        return this;
    }

    /**
     * 增加国际化参数
     *
     * @param key   参数键
     * @param value 参数值
     * @return 本身
     */
    public MessageProp addParamI8n(Object key, Object value) {
        i18nParams.put(key, value);
        return this;
    }

    /**
     * 返回所在结果
     *
     * @return
     */
    public T host(Class<T> clazz) {
        return host;
    }

}
