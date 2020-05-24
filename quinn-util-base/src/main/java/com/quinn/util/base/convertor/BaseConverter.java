package com.quinn.util.base.convertor;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.quinn.util.base.api.DataConverter;
import com.quinn.util.base.exception.DataStyleNotMatchException;
import com.quinn.util.base.handler.BaseObjectSerializer;
import com.quinn.util.base.StringUtil;
import com.quinn.util.base.enums.DataTypeEnum;
import com.quinn.util.base.enums.ExceptionEnum;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

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

        Class clazz = (Class<?>) parameterizedType.getActualTypeArguments()[0];
        CONVERTER_MAP.put(clazz, converter);
        SerializeConfig.globalInstance.put(clazz, BaseObjectSerializer.INSTANCE);

        Class[] supportedClasses = converter.getSupportedClasses();
        if (supportedClasses != null) {
            for (Class type : supportedClasses) {
                CONVERTER_MAP.put(type, converter);
                SerializeConfig.globalInstance.put(type, BaseObjectSerializer.INSTANCE);
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

        if (value == null) {
            return null;
        }

        DataConverter converter = getInstance(clazz);
        if (converter != null) {
            return (T) converter.convert(value);
        }

        if (value instanceof JSONObject) {
            return ((JSONObject) value).toJavaObject(clazz);
        } else if (value instanceof Map) {
            return new JSONObject((Map) value).toJavaObject(clazz);
        }

        throw new DataStyleNotMatchException().getMessageProp()
                .addParam(ExceptionEnum.DATA_STYLE_NOT_MATCHED.paramNames[0], clazz.getName())
                .addParam(ExceptionEnum.DATA_STYLE_NOT_MATCHED.paramNames[1], value)
                .exception();
    }


    /**
     * 将任意类型的值转换为目标类型的值
     *
     * @param value  值
     * @param clazz  目标类
     * @param defVal 默认值
     * @return 目标值
     */
    public static <T> T staticConvert(Object value, Class<T> clazz, T defVal) {
        T result = staticConvert(value, clazz);
        return result == null ? defVal : result;
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
     * @return Java 类
     */
    public static Class classOf(String dataType) {
        return DataTypeEnum.classOf(dataType);
    }

    /**
     * 静态判断是否为空
     *
     * @param object 判断对象
     * @return 是否为空
     */
    public static boolean staticIsEmpty(Object object) {
        if (object == null) {
            return true;
        }

        Class<?> clazz = object.getClass();
        DataConverter dataConverter = CONVERTER_MAP.get(clazz);

        if (dataConverter != null) {
            return dataConverter.isEmpty(object);
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
     * @param clazz Java类型
     * @return 是否是基础类
     */
    public static boolean isPrimitive(Class clazz) {
        return CONVERTER_MAP.containsKey(clazz);
    }
}
