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

    private Long id;

    private Long parentId;

    private K dataKey;

    private K dataCode;

    private V dataValue;

}
