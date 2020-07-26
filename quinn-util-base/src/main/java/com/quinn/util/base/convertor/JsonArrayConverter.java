package com.quinn.util.base.convertor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.quinn.util.base.StringUtil;
import com.quinn.util.base.convertor.BaseConverter;
import com.quinn.util.constant.StringConstant;

import java.util.Collection;

/**
 * Json对象转换器
 *
 * @author Qunhua.Liao
 * @since 2020-07-21
 */
public class JsonArrayConverter extends BaseConverter<JSONArray> {

    @Override
    public JSONArray convert(Object obj) {
        if (obj == null) {
            return null;
        }

        if (obj instanceof Collection || obj.getClass().isArray()) {
            return (JSONArray) JSON.toJSON(obj);
        }

        if (obj instanceof String) {
            String str = (String) obj;
            if (StringUtil.isJsonArray(str)) {
                return JSONArray.parseArray(str);
            }

            String[] split = StringUtil.split(str, StringConstant.CHAR_COMMA);
            return (JSONArray) JSON.toJSON(split);
        }

        return null;
    }

    @Override
    public String toStr(JSONArray obj) {
        return obj.toJSONString();
    }

    @Override
    public boolean isMyStyle(Object obj) {
        if (obj instanceof JSONArray) {
            return true;
        }

        if (obj instanceof Collection) {
            return true;
        }

        if (obj instanceof String) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (obj.getClass().isArray()) {
            return true;
        }

        return false;
    }

}
