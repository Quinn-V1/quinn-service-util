package com.quinn.util.base.convertor;

import com.quinn.util.base.DateUtil;
import com.quinn.util.constant.DateFormatConstant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 本地日期时间转换器
 *
 * @author Qunhua.Liao
 * @since 2020-03-29
 */
public class LocalDateTimeConverter extends BaseConverter<LocalDateTime> {

    @Override
    public LocalDateTime convert(Object obj) {
        return DateUtil.parseLocalDateTime(obj);
    }

    @Override
    public boolean isMyStyle(Object obj) {
        return DateUtil.isDate(obj);
    }

    @Override
    public String toStr(LocalDateTime obj) {
        if (obj == null) {
            return null;
        }
        return DateTimeFormatter.ofPattern(DateFormatConstant.DATE_PATTEN_YYYY_MM_DD_HH_MM_SS_SEPARATOR_1).format(obj);
    }

}
