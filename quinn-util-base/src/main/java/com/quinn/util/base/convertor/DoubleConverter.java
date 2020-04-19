package com.quinn.util.base.convertor;

import com.quinn.util.base.util.NumberUtil;

/**
 * 双精度数据类型转换器
 *
 * @author Quinn.Liao
 * @since 2016-02-29 12:14
 */
public class DoubleConverter extends BaseConverter<Double> {

    @Override
    public Class[] getSupportedClasses() {
        return new Class[]{Double.TYPE};
    }

    @Override
    public Double convert(Object obj) {
        return NumberUtil.parseDouble(obj);
    }

    @Override
    public boolean isMyStyle(Object obj) {
        return NumberUtil.isDouble(obj);
    }

}
