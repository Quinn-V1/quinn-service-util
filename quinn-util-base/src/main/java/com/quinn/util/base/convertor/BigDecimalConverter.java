package com.quinn.util.base.convertor;

import com.quinn.util.base.NumberUtil;

import java.math.BigDecimal;

/**
 * 精确数值类型转换类
 *
 * @author Quinn.Liao
 * @since 2016-02-29 12:09
 */
public class BigDecimalConverter extends BaseConverter<BigDecimal> {

    @Override
    public BigDecimal convert(Object obj) {
        return NumberUtil.paresBigDecimal(obj);
    }

    @Override
    public boolean isMyStyle(Object obj) {
        return NumberUtil.isBigDecimal(obj);
    }

}
