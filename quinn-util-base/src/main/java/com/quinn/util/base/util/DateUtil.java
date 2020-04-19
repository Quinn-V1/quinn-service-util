package com.quinn.util.base.util;

import com.quinn.util.base.exception.DataStyleNotMatchException;
import com.quinn.util.constant.DateFormatConstant;
import com.quinn.util.constant.RegexConstant;
import com.quinn.util.constant.enums.ExceptionEnum;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 日期工具类
 * TODO: 使用 DateTimeFormatter 传入带时间样式的字符串，LocalDate会报错
 *
 * @author Qunhua.Liao
 * @since 2020-03-29
 */
public final class DateUtil {

    /**
     * 1分钟对应的微秒数
     */
    public static final long ONE_SECOND = 1000;

    /**
     * 1分钟对应的微秒数
     */
    public static final long ONE_MINUTE = 60000;

    private DateUtil() {
    }

    private static final Map<Pattern, String> SUPPORT_PATTEN = new HashMap<>();

    static {
        SUPPORT_PATTEN.put(RegexConstant.DATE_PATTEN_YYYY_MM_DD_SEPARATOR_1,
                DateFormatConstant.DATE_PATTEN_YYYY_MM_DD_SEPARATOR_1);
        SUPPORT_PATTEN.put(RegexConstant.DATE_PATTEN_YYYY_MM_DD_SEPARATOR_2,
                DateFormatConstant.DATE_PATTEN_YYYY_MM_DD_SEPARATOR_2);
        SUPPORT_PATTEN.put(RegexConstant.DATE_PATTEN_YYYY_MM_DD_NO_SEPARATOR,
                DateFormatConstant.DATE_PATTEN_YYYY_MM_DD_NO_SEPARATOR);
        SUPPORT_PATTEN.put(RegexConstant.DATE_PATTEN_YYYY_MM_DD_HH_MM_SS_SEPARATOR_1,
                DateFormatConstant.DATE_PATTEN_YYYY_MM_DD_HH_MM_SS_SEPARATOR_1);
        SUPPORT_PATTEN.put(RegexConstant.DATE_PATTEN_YYYY_MM_DD_HH_MM_SS_SEPARATOR_2,
                DateFormatConstant.DATE_PATTEN_YYYY_MM_DD_HH_MM_SS_SEPARATOR_2);
        SUPPORT_PATTEN.put(RegexConstant.DATE_PATTEN_YYYY_MM_DD_HH_MM_SS_NO_SEPARATOR,
                DateFormatConstant.DATE_PATTEN_YYYY_MM_DD_HH_MM_SS_NO_SEPARATOR);
        SUPPORT_PATTEN.put(RegexConstant.DATE_PATTEN_YYYY_MM_DD_HH_MM_SS_SSS_SEPARATOR_1,
                DateFormatConstant.DATE_PATTEN_YYYY_MM_DD_HH_MM_SS_SSS_SEPARATOR_1);
        SUPPORT_PATTEN.put(RegexConstant.DATE_PATTEN_YYYY_MM_DD_HH_MM_SS_SSS_SEPARATOR_2,
                DateFormatConstant.DATE_PATTEN_YYYY_MM_DD_HH_MM_SS_SSS_SEPARATOR_2);
        SUPPORT_PATTEN.put(RegexConstant.DATE_PATTEN_YYYY_MM_DD_HH_MM_SS_SSS_NO_SEPARATOR,
                DateFormatConstant.DATE_PATTEN_YYYY_MM_DD_HH_MM_SS_SSS_NO_SEPARATOR);
    }

    /**
     * 判断一个对象是否可以转为日期
     *
     * @param obj 对象
     * @return 为日期格式则为true否则为false
     */
    public static boolean isDate(Object obj) {
        if (obj instanceof TemporalAccessor || obj instanceof Date) {
            return true;
        }

        if (obj instanceof String) {
            String str = (String) obj;
            for (Map.Entry<Pattern, String> entry : SUPPORT_PATTEN.entrySet()) {
                if (entry.getKey().matcher(str).matches()) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 将一个对象转换为LocalDate
     *
     * @param obj 对象
     * @return 新日期
     */
    @SneakyThrows
    public static LocalDate parseLocalDate(Object obj) {
        if (obj == null) {
            return null;
        }

        if (obj instanceof LocalDate) {
            return (LocalDate) obj;
        }

        if (obj instanceof TemporalAccessor) {
            return LocalDate.from((TemporalAccessor) obj);
        }

        if (obj instanceof Date) {
            Date date = (Date) obj;
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }

        if (obj instanceof String) {
            String str = (String) obj;
            for (Map.Entry<Pattern, String> entry : SUPPORT_PATTEN.entrySet()) {
                if (entry.getKey().matcher(str).find()) {
                    Date date = new SimpleDateFormat(entry.getValue()).parse(str);
                    return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                }
            }
        }

        throw new DataStyleNotMatchException()
                .getMessageProp()
                .addParamI8n(ExceptionEnum.DATA_STYLE_NOT_MATCHED.paramNames[0], LocalDate.class.getName())
                .addParam(ExceptionEnum.DATA_STYLE_NOT_MATCHED.paramNames[1], obj)
                .exception()
                ;
    }

    /**
     * 将一个对象转换为LocalDate
     *
     * @param obj 对象
     * @return 新日期时间
     */
    @SneakyThrows
    public static LocalDateTime parseLocalDateTime(Object obj) {
        if (obj == null) {
            return null;
        }

        if (obj instanceof LocalDateTime) {
            return (LocalDateTime) obj;
        }

        if (obj instanceof TemporalAccessor) {
            return LocalDateTime.from((TemporalAccessor) obj);
        }

        if (obj instanceof Date) {
            Date date = (Date) obj;
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        }

        if (obj instanceof String) {
            String str = (String) obj;
            for (Map.Entry<Pattern, String> entry : SUPPORT_PATTEN.entrySet()) {
                if (entry.getKey().matcher(str).find()) {
                    Date date = new SimpleDateFormat(entry.getValue()).parse(str);
                    return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                }
            }
        }

        throw new DataStyleNotMatchException()
                .getMessageProp()
                .addParamI8n(ExceptionEnum.DATA_STYLE_NOT_MATCHED.paramNames[0], LocalDateTime.class.getName())
                .addParam(ExceptionEnum.DATA_STYLE_NOT_MATCHED.paramNames[1], obj)
                .exception();
    }

    /**
     * 将一个对象转换为LocalDate
     *
     * @param obj 对象
     * @return 日旧期
     */
    @SneakyThrows
    public static Date parseDate(Object obj) {
        if (obj == null) {
            return null;
        }

        if (obj instanceof Date) {
            return (Date) obj;
        }

        if (obj instanceof TemporalAccessor) {
            return Date.from(LocalDateTime.from((TemporalAccessor) obj).atZone(ZoneId.systemDefault()).toInstant());
        }

        if (obj instanceof String) {
            String str = (String) obj;
            for (Map.Entry<Pattern, String> entry : SUPPORT_PATTEN.entrySet()) {
                if (entry.getKey().matcher(str).find()) {
                    return new SimpleDateFormat(entry.getValue()).parse(str);
                }
            }
        }

        throw new DataStyleNotMatchException()
                .getMessageProp()
                .addParamI8n(ExceptionEnum.DATA_STYLE_NOT_MATCHED.paramNames[0], LocalDateTime.class.getName())
                .addParam(ExceptionEnum.DATA_STYLE_NOT_MATCHED.paramNames[1], obj)
                .exception();
    }

}
