package com.quinn.util.base.model;

/**
 * 异步成功（异步成功就一种特殊场景，提示后续具有依赖关系的逻辑谨慎操作）
 *
 * @author Qunhua.Liao
 * @since 2020-05-09
 */
public class AsyncSuccess {

    /**
     * 异步调用成功结果
     */
    public static final AsyncSuccess INSTANCE = new AsyncSuccess();
    
}
