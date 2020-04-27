package com.quinn.util.base.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 集合工具类
 *
 * @author Qunhua.Liao
 * @since 2020-03-30
 */
public final class CollectionUtil {

    private CollectionUtil() {
    }

    /**
     * 判断元素是否在数组内
     *
     * @param element 元素
     * @param array   数组
     * @return 在里面则为true, 否则为false
     */
    public static final boolean elementInArray(Object element, Object[] array) {
        if (element == null || array == null) {
            return false;
        }
        for (Object obj : array) {
            if (element.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断数组是否为空
     *
     * @param args 判断数组
     * @return 是否为空
     */
    public static final boolean isEmpty(Object[] args) {
        return args == null || args.length == 0;
    }

    /**
     * 判断列表是否为空
     *
     * @param collection 判断列表
     * @return 是否为空
     */
    public static final boolean isEmpty(Collection collection) {
        return collection == null || collection.size() == 0;
    }

    /**
     * 判断Map是否为空
     *
     * @param params Map
     * @return 是否为空
     */
    public static boolean isEmpty(Map params) {
        return params == null || params.size() == 0;
    }

    /**
     * 将数组转化为Set
     *
     * @param ts  数组
     * @param <T> 数组泛型
     * @return Set
     */
    public static final <T> HashSet<T> asHashSet(T[] ts) {
        if (isEmpty(ts)) {
            return null;
        }

        HashSet hashSet = new HashSet();
        for (T t : ts) {
            hashSet.add(t);
        }
        return hashSet;
    }

    /**
     * 整合两个Map
     *
     * @param oldMap 旧的Map
     * @param addMap 新增Map
     */
    public static <K, V> void mergeMap(Map<K, V> oldMap, Map<K, V> addMap) {
        if (addMap == null) {
            return;
        }

        for (Map.Entry<K, V> entry : addMap.entrySet()) {
            if (!oldMap.containsKey(entry.getKey())) {
                oldMap.put(entry.getKey(), entry.getValue());
            } else {
                Object oldVal = oldMap.get(entry.getKey());
                if (oldVal instanceof Map && entry.getValue() instanceof Map) {
                    mergeMap((Map) oldVal, (Map) entry.getValue());
                } else {
                    oldMap.put(entry.getKey(), entry.getValue());
                }
            }
        }
    }

}
