package com.quinn.util.base.convertor;

import com.quinn.util.base.BooleanUtil;

/**
 * 布尔型数据转换器
 *
 * @author Quinn.Liao
 * @since 2016-02-29 10:47
 */
public class BooleanConverter extends BaseConverter<Boolean> {

    @Override
    public Class[] getSupportedClasses() {
        return new Class[]{Boolean.TYPE};
    }

    @Override
    public Boolean convert(Object obj) {
        return BooleanUtil.parseBoolean(obj);
    }

    @Override
    public boolean isMyStyle(Object obj) {
        return BooleanUtil.isBoolean(obj);
    }

}
