package com.quinn.util.base;

import com.alibaba.fastjson.JSONArray;
import com.quinn.util.base.api.DegreeEntity;
import com.quinn.util.base.api.MethodInvokerNoParam;
import com.quinn.util.base.api.MethodInvokerOneParam;
import com.quinn.util.base.convertor.BaseConverter;
import com.quinn.util.constant.NumberConstant;
import com.quinn.util.constant.StringConstant;

import java.util.*;

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

    /**
     * 默认分割符拼接列表
     *
     * @param data 数据
     * @return 字符串
     */
    public static <T> String join(Collection<T> data, String split, MethodInvokerOneParam<T, String> invoker) {
        if (isEmpty(data)) {
            return null;
        }

        StringBuilder query = new StringBuilder();
        for (T o : data) {
            query.append(invoker.invoke(o)).append(split);
        }

        query.delete(query.length() - split.length(), query.length());
        return query.toString();
    }

    /**
     * 默认分割符拼接列表
     *
     * @param data 数据
     * @return 字符串
     */
    public static String join(Collection data) {
        return join(data, StringConstant.CHAR_COMMA);
    }

    /**
     * 默认分割符拼接数组
     *
     * @param data 数据
     * @return 字符串
     */
    public static String join(Object[] data) {
        return join(data, StringConstant.CHAR_COMMA);
    }

    /**
     * 自定分隔符拼接列表
     *
     * @param data  数据
     * @param split 分割符
     * @return
     */
    public static String join(Collection data, String split) {
        return join(data, split, (MethodInvokerOneParam<Object, String>) o -> BaseConverter.staticToString(o));
    }

    /**
     * 自定分隔符拼接数组
     *
     * @param data  数据
     * @param split 分割符
     * @return
     */
    public static String join(Object[] data, String split) {
        if (isEmpty(data)) {
            return null;
        }

        StringBuilder query = new StringBuilder();
        for (Object o : data) {
            query.append(BaseConverter.staticToString(o)).append(split);
        }
        query.delete(query.length() - split.length(), query.length());
        return query.toString();
    }

    /**
     * Map空值安全添加元素
     *
     * @param map   容器
     * @param key   键
     * @param value 空值生成值的回调函数
     * @param <T>   值泛型
     * @return 值（原值或者新添加的）
     */
    public static <T> T nullSafeGut(Map<String, T> map, String key, MethodInvokerNoParam<T> value) {
        T t = map.get(key);
        if (t == null) {
            t = value.invoke();
            map.put(key, t);
        }
        return t;
    }

    /**
     * 根据下标取值
     *
     * @param object 容器
     * @param index  下标
     * @return 值
     */
    public static Object get(Object object, int index) {
        if (object == null || index < 0) {
            return null;
        }

        if (object instanceof JSONArray) {
            JSONArray array = ((JSONArray) object);
            if (index >= array.size()) {
                return null;
            }
            return array.get(index);
        }

        if (object.getClass().isArray()) {
            Object[] array = (Object[]) object;
            if (index >= array.length) {
                return null;
            }
            return array[index];
        }

        if (List.class.isAssignableFrom(object.getClass())) {
            List list = (List) object;
            if (index >= list.size()) {
                return null;
            }
            return list.get(index);
        }

        return null;
    }

    /**
     * 平铺添加
     *
     * @param collection 已有集合
     * @param object     新增数据
     */
    public static void plainAdd(Collection collection, Object object) {
        if (object.getClass().isArray()) {
            Object[] array = (Object[]) object;
            for (Object o : array) {
                collection.add(o);
            }
        } else if (Collection.class.isAssignableFrom(object.getClass())) {
            collection.addAll((Collection) object);
        } else {
            collection.add(object);
        }
    }

    /**
     * 合并两个列表
     *
     * @param data1 数据1
     * @param data2 数据2
     * @return 合并后的数据（不要改变之前的数据）
     */
    public static Set mergeSet(Set data1, Set data2) {
        if (data1 == null) {
            return data2;
        }

        if (data2 == null) {
            return data1;
        }

        Set result = new HashSet();
        result.addAll(data1);
        result.addAll(data2);

        return result;
    }

    /**
     * 将列表转为Map
     *
     * @param data    数据
     * @param invoker 获取Key的回调函数
     * @param <K>     Key 泛型
     * @param <V>     值泛型
     * @return Map
     */
    public static <K, V> Map<K, List<V>> collectionToListMap(
            Collection<V> data, MethodInvokerOneParam<V, K> invoker) {
        if (isEmpty(data)) {
            return null;
        }

        Map<K, List<V>> result = new HashMap<>();
        for (V v : data) {
            K k = invoker.invoke(v);
            List<V> vs = result.get(k);
            if (vs == null) {
                vs = new ArrayList<>();
                result.put(k, vs);
            }
            vs.add(v);
        }

        return result;
    }

    /**
     * 将列表转为Map
     *
     * @param data    数据
     * @param invoker 获取Key的回调函数
     * @param <K>     Key 泛型
     * @param <V>     值泛型
     * @return Map
     */
    public static <K, V> Map<K, List<V>> collectionToListMap(
            Map<K, List<V>> result, Collection<V> data, MethodInvokerOneParam<V, K> invoker) {
        if (isEmpty(data)) {
            return null;
        }

        for (V v : data) {
            K k = invoker.invoke(v);
            List<V> vs = result.get(k);
            if (vs == null) {
                vs = new ArrayList<>();
                result.put(k, vs);
            }
            vs.add(v);
        }

        return result;
    }

    /**
     * 将列表转为Map
     *
     * @param data         数据
     * @param keyInvoker   获取Key的回调函数
     * @param valueInvoker 获取Value的回调函数
     * @param <K>          Key 泛型
     * @param <V>          值泛型
     * @return Map
     */
    public static <K, V, R> Map<K, List<R>> collectionToListMap(
            Collection<V> data, MethodInvokerOneParam<V, K> keyInvoker, MethodInvokerOneParam<V, R> valueInvoker) {
        if (isEmpty(data)) {
            return null;
        }

        Map<K, List<R>> result = new HashMap<>(NumberConstant.INT_EIGHT);
        for (V v : data) {
            K k = keyInvoker.invoke(v);

            List<R> vs = result.get(k);
            if (vs == null) {
                vs = new ArrayList<>();
                result.put(k, vs);
            }
            vs.add(valueInvoker.invoke(v));
        }

        return result;
    }

    /**
     * 将列表转为Map
     *
     * @param data         数据
     * @param keyInvoker   获取Key的回调函数
     * @param valueInvoker 获取Value的回调函数
     * @param <K>          Key 泛型
     * @param <V>          值泛型
     * @return Map
     */
    public static <K, V, R> Map<K, Set<R>> collectionToSetMap(
            Collection<V> data, MethodInvokerOneParam<V, K> keyInvoker, MethodInvokerOneParam<V, R> valueInvoker) {
        if (isEmpty(data)) {
            return null;
        }

        Map<K, Set<R>> result = new HashMap<>(NumberConstant.INT_EIGHT);
        for (V v : data) {
            K k = keyInvoker.invoke(v);

            Set<R> vs = result.get(k);
            if (vs == null) {
                vs = new HashSet<>();
                result.put(k, vs);
            }
            vs.add(valueInvoker.invoke(v));
        }

        return result;
    }

    /**
     * 将列表转为Map
     *
     * @param data    数据
     * @param invoker 获取Key的回调函数
     * @param <K>     Key 泛型
     * @param <V>     值泛型
     * @return Map
     */
    public static <K, V> Map<K, V> collectionToMap(Collection<V> data, MethodInvokerOneParam<V, K> invoker) {
        Map<K, V> result = new HashMap<>(data.size());
        for (V v : data) {
            K k = invoker.invoke(v);
            result.put(k, v);
        }
        return result;
    }

    /**
     * 将列表转为Map
     *
     * @param data    数据
     * @param invoker 获取Key的回调函数
     * @param <K>     Key 泛型
     * @param <V>     值泛型
     * @return Map
     */
    public static <K, V> Map<K, V> arrayToMap(V[] data, MethodInvokerOneParam<V, K> invoker) {
        Map<K, V> result = new HashMap<>(data.length);
        for (V v : data) {
            K k = invoker.invoke(v);
            result.put(k, v);
        }
        return result;
    }

    /**
     * 将列表转为Map
     *
     * @param data         数据
     * @param keyInvoker   获取Key的回调函数
     * @param valueInvoker 获取Value的回调函数
     * @param <K>          Key 泛型
     * @param <V>          值泛型
     * @return Map
     */
    public static <K, V, R> Map<K, R> collectionToMap(
            Collection<V> data, MethodInvokerOneParam<V, K> keyInvoker, MethodInvokerOneParam<V, R> valueInvoker) {
        Map<K, R> result = new HashMap<>(data.size());
        for (V v : data) {
            result.put(keyInvoker.invoke(v), valueInvoker.invoke(v));
        }
        return result;
    }

    /**
     * 空安全添加列表Map
     *
     * @param map  列表Map
     * @param data 数据
     * @param key  Key
     */
    public static <T> void nullSafePut(Map<String, T> map, T data, String key) {
        if (data == null || key == null) {
            return;
        }
        map.put(key, data);
    }

    /**
     * 空安全添加列表Map
     *
     * @param listMap 列表Map
     * @param data    数据
     * @param key     Key
     */
    public static <T> void nullSafePutList(Map<String, List<T>> listMap, T data, String key) {
        List list = listMap.get(key);
        if (list == null) {
            list = new ArrayList();
            listMap.put(key, list);
        }
        list.add(data);
    }

    /**
     * 将层级列表转成树结构
     *
     * @param degreeList 层级数据列表
     * @param <T>        数据泛型
     * @param <K>        关联字段泛型
     * @return 树结构
     */
    public static <T extends DegreeEntity<K, T>, K> List<T> collectionToTree(List<T> degreeList) {
        if (isEmpty(degreeList)) {
            return null;
        }

        LinkedHashMap<K, T> allMap = new LinkedHashMap<>();
        List<T> result = new ArrayList<>();

        for (T data : degreeList) {
            allMap.put(data.getDataKey(), data);
        }

        for (T data : degreeList) {
            K parentKey = data.getParentKey();
            T parent = allMap.get(parentKey);

            if (parent == null) {
                result.add(data);
            } else {
                List<T> children = parent.getChildren();
                if (children == null) {
                    children = new ArrayList<>();
                    parent.setChildren(children);
                }
                children.add(data);
            }
        }

        return result;
    }


    public static <V, V1 extends V, V2 extends V> Map<String, V> toMap(
            String name1, V1 value1, String name2, V2 value2
    ) {
        return populateMap(new HashMap<>(16), name1, value1, name2, value2);
    }

    public static <V> Map<String, V> populateMap(Map<String, V> map, Object... data) {
        if (isEmpty(data)) {
            return map;
        }
        if (map == null) {
            map = new HashMap<>();
        }

        int size = data.length;
        if (size % NumberConstant.INT_TWO != NumberConstant.INT_ZERO) {
            size--;
        }

        for (int i = 0; i < size; ) {
            map.put((String) data[i++], (V) data[i++]);
        }
        return map;
    }

    /**
     * 转换为数组
     *
     * @param o     对象
     * @param clazz 类型
     * @param <T>   结果泛型
     * @return 泛型数组
     */
    public static <T> T[] toArray(Object o, Class<T> clazz) {
        if (o == null) {
            return null;
        }

        if (o instanceof Collection) {
            Collection collection = (Collection) o;
            Object[] ts = new Object[collection.size()];
            int i = 0;
            for (Object o1 : collection) {
                ts[i++] = BaseConverter.staticConvert(o1, clazz);
            }
            return (T[]) ts;
        } else if (o.getClass().isArray()) {
            Object[] os = (Object[]) o;
            Object[] ts = new Object[os.length];
            for (int i = 0; i < os.length; i++) {
                ts[i] = BaseConverter.staticConvert(os[i], clazz);
            }
        } else if (o instanceof String && clazz == String.class) {
            String[] os = ((String) o).split(StringConstant.CHAR_COMMA);
            return (T[]) os;
        }

        Object[] objects = new Object[1];
        objects[0] = BaseConverter.staticConvert(o, clazz);
        return (T[]) objects;
    }
}
