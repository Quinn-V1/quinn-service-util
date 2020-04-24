package com.quinn.util.base.api;

/**
 * 键值对形式的数据
 *
 * @author Qunhua.Liao
 * @since 2020-04-18
 */
public interface KeyValue<K, V> {

    /**
     * 获取系统主键
     *
     * @return  系统主键
     */
    Long getId();

    /**
     * 获取业务主键
     *
     * @return  业务主键
     */
    K getDataKey();

    /**
     * 获取业务描述
     *
     * @return
     */
    V getDataValue();

    /**
     * 获取编码（编码是业务主键的一种简要表达）
     *
     * @return  编码
     */
    default K getDataCode() {return getDataKey();}

    /**
     * 获取上级ID（支持上下级）
     *
     * @return  默认为空
     */
    default Long getParentId() {return null;}

}
