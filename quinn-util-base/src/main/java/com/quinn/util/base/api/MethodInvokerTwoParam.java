package com.quinn.util.base.api;

/**
 * 双参数操作接口
 *
 * @author Qunhua.Liao
 * @since 2020-02-10
 */
public interface MethodInvokerTwoParam<T, K, V> {

    /**
     * 取值
     *
     * @param t 取值对象
     * @param k 取值对象
     * @return 值
     */
    V invoke(T t, K k);

}
