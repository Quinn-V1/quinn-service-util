package com.quinn.util.base.api;

/**
 * 无参数操作接口
 *
 * @author Qunhua.Liao
 * @since 2020-02-10
 */
public interface MethodInvokerNoParam<V> {

    /**
     * 取值
     *
     * @return 值
     */
    V invoke();

}
