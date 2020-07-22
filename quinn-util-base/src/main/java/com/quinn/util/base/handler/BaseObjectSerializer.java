package com.quinn.util.base.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ListSerializer;
import com.alibaba.fastjson.serializer.MapSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.quinn.util.base.convertor.BaseConverter;
import lombok.SneakyThrows;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * 序列化
 *
 * @author Qunhua.Liao
 * @since 2020-05-13
 */
public class BaseObjectSerializer implements ObjectSerializer {

    public static final BaseObjectSerializer INSTANCE = new BaseObjectSerializer();

    @Override
    @SneakyThrows
    public void write(JSONSerializer serializer, Object object, Object o1, Type type, int i) {
        if (object == null) {
            serializer.out.writeNull();
        } else if (object instanceof Map) {
            MapSerializer.instance.write(serializer, object, o1, type, i);
        } else if (object instanceof JSONArray) {
            ListSerializer.instance.write(serializer, object, o1, type, i);
        } else {
            String text = BaseConverter.staticToString(object);
            serializer.write(text);
        }
    }

}
