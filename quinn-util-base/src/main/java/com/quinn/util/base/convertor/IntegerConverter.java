package com.quinn.util.base.convertor;

import com.quinn.util.base.NumberUtil;

/**
 * 整型数据转换器
 *
 * @author Quinn.Liao
 * @since 2016-02-29 11:23
 */
public class IntegerConverter extends BaseConverter<Integer> {

    @Override
    public Class[] getSupportedClasses() {
        return new Class[]{Integer.TYPE};
    }

    @Override
    public Integer convert(Object obj) {
        return NumberUtil.parseInteger(obj);
    }

    @Override
    public boolean isMyStyle(Object obj) {
        return NumberUtil.isInteger(obj);
    }

}
