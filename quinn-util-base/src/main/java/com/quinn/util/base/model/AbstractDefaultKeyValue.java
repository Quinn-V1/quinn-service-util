package com.quinn.util.base.model;

import com.quinn.util.base.api.KeyValue;
import lombok.Getter;
import lombok.Setter;

/**
 * 简单的字符串键值对
 *
 * @author Qunhua.Liao
 * @since 2020-04-18
 */
@Setter
@Getter
public abstract class AbstractDefaultKeyValue<K, V> implements KeyValue<K, V> {

    /**
     * 系统主键
     */
    private Long id;

    /**
     * 父类系统主键
     */
    private Long parentId;

    /**
     * 数据编码
     */
    private K dataKey;

    /**
     * 短编码
     */
    private K dataCode;

    /**
     * 数据值-描述
     */
    private V dataValue;

    /**
     * 父类编码
     */
    private K parentKey;

}
