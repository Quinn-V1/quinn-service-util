package com.quinn.util.base.convertor;

import com.quinn.util.base.util.NumberUtil;

/**
 * 长整型数据转换器
 *
 * @author Quinn.Liao
 * @since 2016-02-29 11:23
 */
public class LongConverter extends BaseConverter<Long> {

    @Override
    public Class[] getSupportedClasses() {
        return new Class[]{Long.TYPE};
    }

    @Override
    public Long convert(Object obj) {
        return NumberUtil.parseLong(obj);
    }

    @Override
    public boolean isMyStyle(Object obj) {
        return NumberUtil.isLong(obj);
    }

}
