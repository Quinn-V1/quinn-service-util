package com.quinn.util.base.convertor;

import com.quinn.util.base.NumberUtil;

/**
 * 整型数据转换器
 *
 * @author Quinn.Liao
 * @since 2016-02-29 11:23
 */
public class ShortConverter extends BaseConverter<Short> {

    @Override
    public Class[] getSupportedClasses() {
        return new Class[]{Short.TYPE};
    }

    @Override
    public Short convert(Object obj) {
        return NumberUtil.parseShort(obj);
    }

    @Override
    public boolean isMyStyle(Object obj) {
        return NumberUtil.isShort(obj);
    }

}
