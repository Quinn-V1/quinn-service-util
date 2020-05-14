package com.quinn.util.base;

import com.quinn.util.base.api.MethodInvokerOneParam;
import com.quinn.util.base.convertor.BaseConverter;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.UUID;

/**
 * 基础工具类
 *
 * @author Qunhua.Liao
 * @since 2020-03-29
 */
public final class BaseUtil {

    private BaseUtil() {
    }

    /**
     * null安全判断值是否相等
     *
     * @param obj1 对象1
     * @param obj2 对象2
     * @return
     */
    public static final boolean equals(Object obj1, Object obj2) {
        if (obj1 == null && obj2 == null) {
            return true;
        }

        if (obj1 == null) {
            return false;
        }

        return obj1.equals(obj2);
    }

    /**
     * 生成UUID去 -
     *
     * @return UUID去 -
     */
    public static String uuid() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-", "");
    }

    /**
     * 连续获取对象指定属性值
     *
     * @param obj       对象
     * @param fieldPath 属性路径
     * @return 属性值
     */
    public static Object getFieldInstance(Object obj, String fieldPath) {
        String[] fields = fieldPath.split("#");
        for (String field : fields) {
            obj = getField(obj, obj.getClass(), field);
            if (obj == null) {
                return null;
            }
        }
        return obj;
    }

    /**
     * 获取对象属性值
     *
     * @param obj       对象
     * @param clazz     类
     * @param fieldName 属性名
     * @return
     */
    public static Object getField(Object obj, Class<?> clazz, String fieldName) {
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                Field field;
                field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field.get(obj);
            } catch (Exception e) {
                // DO NOTHING
            }
        }
        return null;
    }

    /**
     * 取得最大的一个
     *
     * @param data       比较数据
     * @param comparator 比较器
     * @param <T>        数据泛型
     * @return 最大值
     */
    public static <T> T getMax(Collection<T> data, Comparator comparator) {
        T max = null;
        for (T t : data) {
            if (comparator.compare(max, t) < 0) {
                max = t;
            }
        }
        return max;
    }

    /**
     * 如果对象不为空：执行回调函数（可能是列表，迭代执行）
     *
     * @param object        对象
     * @param methodInvoker 回调函数
     * @return 是否不为空（执行）
     */
    public static boolean execIfNotEmpty(Object object, MethodInvokerOneParam methodInvoker) {
        if (object == null) {
            return false;
        }

        if (object instanceof Collection) {
            Collection collection = (Collection) object;
            if (collection.size() == 0) {
                return false;
            }

            if (methodInvoker != null) {
                for (Object o : collection) {
                    methodInvoker.invoke(o);
                }
            }
        } else if (object.getClass().isArray()) {
            Object[] array = (Object[]) object;
            if (array.length == 0) {
                return false;
            }

            if (methodInvoker != null) {
                for (Object o : array) {
                    methodInvoker.invoke(o);
                }
            }
        } else {
            if (BaseConverter.staticIsEmpty(object)) {
                return false;
            }
            if (methodInvoker != null) {
                methodInvoker.invoke(object);
            }
        }

        return true;
    }

}
