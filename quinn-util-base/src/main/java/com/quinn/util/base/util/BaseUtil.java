package com.quinn.util.base.util;

import java.lang.reflect.Field;
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
     * @param obj           对象
     * @param fieldPath     属性路径
     * @return              属性值
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

}
