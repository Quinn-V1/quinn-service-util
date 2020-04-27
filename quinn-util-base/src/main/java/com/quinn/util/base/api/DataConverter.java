package com.quinn.util.base.api;

/**
 * 数据转换器
 *
 * @author Qunhua.Liao
 * @since 2020-03-29
 */
public interface DataConverter<T> {

    /**
     * 将一个数据转换为当前转换器的类型
     *
     * @param obj: 原对象
     * @return 目标类对象
     */
    T convert(Object obj);

    /**
     * 判断一个对象是否符合当前转换器类型的样式
     *
     * @param obj： 转换对象
     * @return 是否符合目标对象的样式
     */
    boolean isMyStyle(Object obj);

    /**
     * 判断一个对象是是不是空
     *
     * @param obj： 转换对象
     * @return 是否符合目标对象的样式
     */
    default boolean isEmpty(T obj) {return obj == null;}

    /**
     * 将一个对象转为字符串
     *
     * @param obj 目标对象
     * @return 字符串
     */
    String toStr(T obj);

    /**
     * 获取支持的类型
     *
     * @return  支持类型
     */
    default Class[] getSupportedClasses() {return null;}

}
