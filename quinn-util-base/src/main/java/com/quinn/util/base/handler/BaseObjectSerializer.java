package com.quinn.util.base.handler;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.quinn.util.base.convertor.BaseConverter;

import java.lang.reflect.Type;

/**
 * 序列化
 *
 * @author Qunhua.Liao
 * @since 2020-05-13
 */
public class BaseObjectSerializer implements ObjectSerializer {

    public static final BaseObjectSerializer INSTANCE = new BaseObjectSerializer();

    @Override
    public void write(JSONSerializer serializer, Object object, Object o1, Type type, int i) {
        if (object == null) {
            serializer.out.writeNull();
        } else {
            String text = BaseConverter.staticToString(object);
            serializer.write(text);
        }
    }

}
