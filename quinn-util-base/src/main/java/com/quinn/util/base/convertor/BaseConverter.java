package com.quinn.util.base.convertor;

import com.quinn.util.base.api.DataConverter;
import com.quinn.util.base.util.CollectionUtil;
import com.quinn.util.base.util.StringUtil;
import com.quinn.util.constant.enums.DataTypeEnum;

import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * 所有类型转换器的父类
 *
 * @author Quinn.Liao
 * @since 2016-02-29 10:50
 */
public abstract class BaseConverter<T> implements DataConverter<T> {

    /**
     * 存储所有实际转换器单例的Map：启动时单线程加载，启动后不变，是线程安全的
     */
    protected static final Map<Class<?>, DataConverter> CONVERTER_MAP = new HashMap();

    /**
     * 根据转换器的类对象（或转换器生产结果类对象）获取转换器实例
     *
     * @param clazz 目标类
     * @return 目标类转换器
     */
    public static DataConverter getInstance(Class<?> clazz) {
        return CONVERTER_MAP.get(clazz);
    }

    /**
     * 添加一个转具体转换器
     *
     * @param converter 类型转换器
     */
    public static void addConverter(DataConverter converter) {
        CONVERTER_MAP.put(converter.getClass(), converter);
        ParameterizedType parameterizedType = (ParameterizedType) converter.getClass().getGenericSuperclass();
        CONVERTER_MAP.put((Class<?>) parameterizedType.getActualTypeArguments()[0], converter);

        Class[] supportedClasses = converter.getSupportedClasses();
        if (supportedClasses != null) {
            for (Class clazz : supportedClasses) {
                CONVERTER_MAP.put(clazz, converter);
            }
        }
    }

    static {
        ServiceLoader<DataConverter> dataConverters = ServiceLoader.load(DataConverter.class);
        Iterator<DataConverter> dataConverterIterator = dataConverters.iterator();
        while (dataConverterIterator.hasNext()) {
            addConverter(dataConverterIterator.next());
        }
    }

    /**
     * 将任意类型的值转换为目标类型的值
     *
     * @param value 值
     * @param clazz 目标类
     * @return 目标值
     */
    public static <T> T staticConvert(Object value, Class<T> clazz) {
        if (clazz.isInstance(value)) {
            return (T) value;
        }

        DataConverter converter = getInstance(clazz);
        if (converter == null) {
            return (T) value;
        }

        return (T) converter.convert(value);
    }

    /**
     * 将任意类型的值转换为目标类型的值
     *
     * @param value 值
     * @return 目标值
     */
    public static String staticToString(Object value) {
        if (value == null) {
            return null;
        }

        DataConverter converter = CONVERTER_MAP.get(value.getClass());
        if (converter != null) {
            return converter.toStr(value);
        }

        return value.toString();
    }

    /**
     * 通过数据类型获取Java类
     *
     * @param dataType 数据类型
     * @return  Java 类
     */
    public static Class classOf(String dataType) {
        return DataTypeEnum.classOf(dataType);
    }

    /**
     * 静态判断是否为空
     *
     * @param object    判断对象
     * @return  是否为空
     */
    public static boolean staticIsEmpty(Object object) {
        if (object == null) {
            return true;
        }

        Class<?> aClass = object.getClass();
        DataConverter dataConverter = CONVERTER_MAP.get(aClass);

        if (dataConverter != null) {
            return dataConverter.isEmpty(object);
        }

        if (aClass.isArray()) {
            return CollectionUtil.isEmpty((Object[]) object);
        } else if (Collection.class.isAssignableFrom(aClass)) {
            return  CollectionUtil.isEmpty((Collection) object);
        } else if (Map.class.isAssignableFrom(aClass)) {
            return  CollectionUtil.isEmpty((Map) object);
        }

        return false;
    }

    @Override
    public String toStr(T obj) {
        return StringUtil.parseString(obj);
    }

    /**
     * 是否是可以直接转化的基础类型
     *
     * @param clazz     Java类型
     * @return          是否是基础类
     */
    public static boolean isPrimitive(Class clazz) {
        return CONVERTER_MAP.containsKey(clazz);
    }
}
