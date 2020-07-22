package com.quinn.util.base.convertor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.quinn.util.base.StringUtil;
import com.quinn.util.base.convertor.BaseConverter;

import java.util.Collection;
import java.util.Map;

/**
 * Json对象转换器
 *
 * @author Qunhua.Liao
 * @since 2020-07-21
 */
public class JsonObjectConverter extends BaseConverter<JSONObject> {

    @Override
    public JSONObject convert(Object obj) {
        if (obj instanceof JSONObject) {
            return (JSONObject) obj;
        }

        if (obj instanceof Map) {
            return new JSONObject((Map) obj);
        }

        if (obj instanceof String) {
            return JSONObject.parseObject((String) obj);
        }

        if (obj == null || (obj instanceof Collection) || obj.getClass().isArray()) {
            return null;
        }

        return (JSONObject) JSON.toJSON(obj);
    }

    @Override
    public String toStr(JSONObject obj) {
        return obj.toJSONString();
    }

    @Override
    public boolean isMyStyle(Object obj) {
        if (obj instanceof Map) {
            return true;
        }

        if ((obj instanceof String)) {
            return StringUtil.isJson((String) obj);
        }

        if (obj instanceof Collection) {
            return false;
        }

        if (obj == null) {
            return false;
        }

        if (obj.getClass().isArray()) {
            return false;
        }

        return true;
    }
}
