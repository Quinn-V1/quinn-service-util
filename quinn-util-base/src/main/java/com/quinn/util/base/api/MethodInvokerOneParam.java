package com.quinn.util.base.api;

import java.io.Serializable;

/**
 * 单参数操作接口
 *
 * @author Qunhua.Liao
 * @since 2020-02-10
 */
public interface MethodInvokerOneParam<T, V> extends Serializable {

    /**
     * 取值
     *
     * @param t 取值对象
     * @return 值
     */
    V invoke(T t);

}
