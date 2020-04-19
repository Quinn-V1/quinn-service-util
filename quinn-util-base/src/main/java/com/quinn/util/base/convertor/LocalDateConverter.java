package com.quinn.util.base.convertor;

import com.quinn.util.base.util.DateUtil;
import com.quinn.util.constant.DateFormatConstant;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 日期转换工具类
 *
 * @author Qunhua.Liao
 * @since 2020-03-29
 */
public class LocalDateConverter extends BaseConverter<LocalDate> {

    @Override
    public LocalDate convert(Object obj) {
        return DateUtil.parseLocalDate(obj);
    }

    @Override
    public boolean isMyStyle(Object obj) {
        return DateUtil.isDate(obj);
    }

    @Override
    public String toStr(LocalDate obj) {
        if (obj == null) {
            return null;
        }
        return DateTimeFormatter.ofPattern(DateFormatConstant.DATE_PATTEN_YYYY_MM_DD_SEPARATOR_1).format(obj);
    }
}
