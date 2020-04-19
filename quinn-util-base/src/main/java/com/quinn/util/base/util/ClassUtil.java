package com.quinn.util.base.util;

import com.quinn.util.base.api.ClassDivAble;
import com.quinn.util.base.model.ClassComparator;
import com.quinn.util.constant.enums.OrderByTypeEnum;

import java.util.*;

/**
 * 类相关操作工具
 *
 * @author Qunhua.liao
 * @since 2020-03-30
 */
public final class ClassUtil {

    private ClassUtil(){}

    /**
     * 指定类是否包含在目标类中
     * isAssignableFrom: 表示是否可从***转办、应用：父类项子类
     *
     * @param src       指定类
     * @param target    目标类
     * @return
     */
    public static final boolean classIn(Class src, Class[] target) {
        if (src == null || target == null) {
            return false;
        }

        for (Class clazz : target) {
            if (clazz == null) {
                continue;
            }
            if (clazz.isAssignableFrom(src)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(Object.class.isAssignableFrom(ClassUtil.class));
        System.out.println(ClassUtil.class.isAssignableFrom(Object.class));
    }

    /**
     * 按类排序：越具体越在前面
     *
     * @param values    列表
     * @param order     顺序
     * @return          排序好的列表
     */
    public static <T extends ClassDivAble> List<T> sort(Collection<T> values, OrderByTypeEnum order) {
        if (values == null || values.size() == 0) {
            return null;
        }

        List<T> result = new ArrayList<>(values);
        Collections.sort(result, new ClassComparator(order));

        return result;
    }

}
