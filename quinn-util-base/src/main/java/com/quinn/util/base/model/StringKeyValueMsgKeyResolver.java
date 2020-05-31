package com.quinn.util.base.model;

import com.quinn.util.base.api.MethodInvokerOneParam;
import com.quinn.util.constant.StringConstant;

/**
 * 字符串键值对，消息键解析器
 *
 * @author Qunhua.Liao
 * @since 2020-05-31
 */
public class StringKeyValueMsgKeyResolver implements MethodInvokerOneParam<StringKeyValue, String> {

    public StringKeyValueMsgKeyResolver(String dataType) {
        this(dataType, StringConstant.NONE_OF_DATA, StringConstant.CHAR_COLON, StringConstant.CHAR_POUND_SIGN);
    }

    public StringKeyValueMsgKeyResolver(String dataType, String propName) {
        this(dataType, propName, StringConstant.CHAR_COLON, StringConstant.CHAR_POUND_SIGN);
    }

    public StringKeyValueMsgKeyResolver(String dataType, String propName, String cacheDelimiter) {
        this(dataType, propName, cacheDelimiter, StringConstant.CHAR_POUND_SIGN);
    }

    public StringKeyValueMsgKeyResolver(String dataType, String propName, String cacheDelimiter,
                                        String propDelimiter) {
        this.dataType = dataType;
        this.propName = propName;
        this.cacheDelimiter = cacheDelimiter;
        this.propDelimiter = propDelimiter;
    }

    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 属性名称
     */
    private String propName;

    /**
     * 分割符
     */
    private String cacheDelimiter;

    /**
     * 分割符
     */
    private String propDelimiter;

    @Override
    public String invoke(StringKeyValue stringKeyValue) {
        return dataType + cacheDelimiter + stringKeyValue.getDataKey() + propDelimiter + propName;
    }

    public StringKeyValueMsgKeyResolver ofDataType(String dataType) {
        this.dataType = dataType;
        return this;
    }

    public StringKeyValueMsgKeyResolver ofPropName(String propName) {
        this.propName = propName;
        return this;
    }

    public StringKeyValueMsgKeyResolver ofCacheDelimiter(String cacheDelimiter) {
        this.cacheDelimiter = cacheDelimiter;
        return this;
    }

    public StringKeyValueMsgKeyResolver ofPropDelimiter(String propDelimiter) {
        this.propDelimiter = propDelimiter;
        return this;
    }
}
