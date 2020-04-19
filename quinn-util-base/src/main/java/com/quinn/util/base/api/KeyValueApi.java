package com.quinn.util.base.api;

/**
 * 键值对接口
 *
 * @author Qunhua.Liao
 * @since 2020-03-30
 */
public interface KeyValueApi<K, V> {

    /**
     * 获取键
     *
     * @return  键
     */
    K getKey();

    /**
     * 获取值
     *
     * @return  值
     */
    V getValue();

    /**
     * 获取父键
     *
     * @return  父键
     */
    K getParentKey();

}
