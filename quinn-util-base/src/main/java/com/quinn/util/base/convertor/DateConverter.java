package com.quinn.util.base.convertor;

import com.quinn.util.base.util.DateUtil;
import com.quinn.util.constant.DateFormatConstant;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换工具类
 *
 * @author Qunhua.Liao
 * @since 2020-03-29
 */
public class DateConverter extends BaseConverter<Date> {

    @Override
    public Date convert(Object obj) {
        return DateUtil.parseDate(obj);
    }

    @Override
    public boolean isMyStyle(Object obj) {
        return DateUtil.isDate(obj);
    }

    @Override
    public String toStr(Date obj) {
        if (obj == null) {
            return null;
        }
        return new SimpleDateFormat(DateFormatConstant.DATE_PATTEN_YYYY_MM_DD_SEPARATOR_1).format(obj);
    }
}
