package com.quinn.util.base.api;

import java.util.List;

/**
 * 具有层级关系的实体
 * <p>
 * T 上下机关联属性泛型
 * V 子类属性
 *
 * @author Qunhua.Liao
 * @since 2020-06-04
 */
public interface DegreeEntity<K, V extends DegreeEntity> {

    /**
     * 自身关联编码
     *
     * @return
     */
    K getDataKey();

    /**
     * 获取父类关联编码
     *
     * @return 父类关联编码
     */
    K getParentKey();

    /**
     * 获取子类列表
     *
     * @return 子类列表
     */
    List<V> getChildren();

    /**
     * 获取子类列表
     *
     * @param children 子类列表
     */
    void setChildren(List<V> children);

}
