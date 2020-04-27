package com.quinn.util.base.convertor;

import com.quinn.util.constant.StringConstant;

/**
 * 字符串数据转换器
 *
 * @author Qunhua.Liao
 * @since 2016-02-29 11:26
 */
public class StringConverter extends BaseConverter<String> {

    @Override
    public String convert(Object obj) {
        if (obj == null) {
            return null;
        }
        return String.valueOf(obj);
    }

    @Override
    public boolean isMyStyle(Object obj) {
        return true;
    }

    @Override
    public boolean isEmpty(String obj) {
        return obj == null || StringConstant.STRING_EMPTY.equals(obj);
    }
}
