package com.quinn.util.base.api;

/**
 * 三参数操作接口
 *
 * @author Qunhua.Liao
 * @since 2020-02-10
 */
public interface MethodInvokerThreeParam<P1, P2, P3, R> {

    /**
     * 取值
     *
     * @param p1 第一个参数
     * @param p2 第二个参数
     * @param p3 第三个参数
     * @return 值
     */
    R invoke(P1 p1, P2 p2, P3 p3);

}
