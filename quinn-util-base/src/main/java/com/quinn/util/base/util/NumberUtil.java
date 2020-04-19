package com.quinn.util.base.util;

import com.quinn.util.base.exception.DataStyleNotMatchException;
import com.quinn.util.constant.enums.ExceptionEnum;

import java.math.BigDecimal;
import java.nio.ByteBuffer;

/**
 * 数字工具类
 *
 * @author Qunhua.Liao
 * @since 2020-03-29
 */
public final class NumberUtil {

    private NumberUtil() {
    }

    /**
     * 判断一个字符串是否为数字
     *
     * @param str 字符串
     * @return 是否为数字样式
     */
    public static boolean isNumber(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        char[] array = str.toCharArray();
        boolean firstChar = (array[0] <= '9' && array[0] >= '0') || array[0] == '+' || array[0] == '-' || array[0] == '.';
        if (!firstChar) {
            return false;
        }

        int i = 1;
        int dotCount = 0;
        int eCount = 0;

        for (; i < array.length; i++) {
            char c = array[i];
            if (c > '9' || c < '0') {
                if (c == '.') {
                    if (dotCount > 0 || eCount > 0) {
                        return false;
                    } else {
                        dotCount++;
                    }
                } else if (c == 'E' || c == 'e') {
                    if (eCount > 0 || i == array.length - 1) {
                        return false;
                    } else {
                        eCount++;
                    }
                }
                return false;
            }
        }

        return true;
    }

    /**
     * 判断一个字符串是否是整数
     *
     * @param str 字符串
     * @return 是否是整数样式
     */
    public static boolean isDigit(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        char[] array = str.toCharArray();
        boolean firstChar = (array[0] <= '9' && array[0] >= '0') || array[0] == '+' || array[0] == '-';
        if (!firstChar) {
            return false;
        }

        int i = 1;
        for (; i < array.length; i++) {
            char c = array[i];
            if (c > '9' || c < '0') {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断一个对象是否是数字
     *
     * @param obj 对象
     * @return 是否是数字
     */

    public static boolean isBigDecimal(Object obj) {
        if (obj instanceof Number) {
            return true;
        }

        if (obj instanceof String) {
            return isNumber((String) obj);
        }

        return false;
    }

    /**
     * 判断一个对象是否可以转为s双精度数字
     *
     * @param o 对象
     * @return 在双精度范围内则为true否则为false
     */
    public static boolean isDouble(Object o) {
        if (o instanceof Double || o instanceof Long || o instanceof Integer || o instanceof Short || o instanceof Byte) {
            return true;
        }

        if (o instanceof String) {
            String str = (String) o;
            if (!isNumber(str)) {
                return false;
            }

            BigDecimal bigDecimal = new BigDecimal(str);
            return bigDecimal.compareTo(BigDecimal.valueOf(Double.MIN_VALUE)) >= 0
                    && bigDecimal.compareTo(BigDecimal.valueOf(Double.MAX_VALUE)) <= 0;
        }

        return false;
    }

    /**
     * 判断一个对象是否可以转为长整型
     *
     * @param o 对象
     * @return 在长整型范围内则为true否则为false
     */
    public static boolean isLong(Object o) {
        if (o instanceof Long || o instanceof Integer || o instanceof Short || o instanceof Byte) {
            return true;
        }

        if (o instanceof String) {
            String str = (String) o;
            if (!isDigit(str)) {
                return false;
            }

            BigDecimal bigDecimal = new BigDecimal(str);
            return bigDecimal.compareTo(BigDecimal.valueOf(Long.MIN_VALUE)) >= 0
                    && bigDecimal.compareTo(BigDecimal.valueOf(Long.MAX_VALUE)) <= 0;
        }

        return false;
    }

    /**
     * 判断一个对象是否可以转为整型
     *
     * @param o 对象
     * @return 在整型范围内则为true否则为false
     */
    public static boolean isInteger(Object o) {
        if (o instanceof Integer || o instanceof Short || o instanceof Byte) {
            return true;
        }

        if (o instanceof Long) {
            Long l = (Long) o;
            return (Integer.MAX_VALUE >= l) && (Integer.MIN_VALUE <= l);
        }

        if (o instanceof String) {
            String str = (String) o;
            if (!isDigit(str)) {
                return false;
            }

            BigDecimal bigDecimal = new BigDecimal(str);
            return bigDecimal.compareTo(BigDecimal.valueOf(Integer.MIN_VALUE)) >= 0
                    && bigDecimal.compareTo(BigDecimal.valueOf(Integer.MAX_VALUE)) <= 0;
        }

        return false;
    }

    /**
     * 判断一个对象是否可以转为短整型
     *
     * @param o 对象
     * @return 在短整型范围内则为true否则为false
     */
    public static boolean isShort(Object o) {
        if (o instanceof Short || o instanceof Byte) {
            return true;
        }

        if (o instanceof Integer) {
            Integer l = (Integer) o;
            return (Short.MAX_VALUE >= l) && (Short.MIN_VALUE <= l);
        }

        if (o instanceof Long) {
            Long l = (Long) o;
            return (Short.MAX_VALUE >= l) && (Short.MIN_VALUE <= l);
        }

        if (o instanceof String) {
            String str = (String) o;
            if (!isDigit(str)) {
                return false;
            }

            BigDecimal bigDecimal = new BigDecimal(str);
            return bigDecimal.compareTo(BigDecimal.valueOf(Short.MIN_VALUE)) >= 0
                    && bigDecimal.compareTo(BigDecimal.valueOf(Short.MAX_VALUE)) <= 0;
        }

        return false;
    }

    /**
     * 将一个对象转化为双精度
     *
     * @param o 对象
     * @return 双精度
     */
    public static Double parseDouble(Object o) {
        if (o == null) {
            return null;
        }

        if (!isDouble(o)) {
            throw new DataStyleNotMatchException()
                    .getMessageProp()
                    .addParamI8n(ExceptionEnum.DATA_STYLE_NOT_MATCHED.paramNames[0], Double.class.getName())
                    .addParam(ExceptionEnum.DATA_STYLE_NOT_MATCHED.paramNames[1], o)
                    .exception();
        }

        if (o instanceof Double) {
            return (Double) o;
        }

        return Double.valueOf(String.valueOf(o));
    }

    /**
     * 将一个对象转化为数字
     *
     * @param o 对象
     * @return 数字
     */
    public static BigDecimal paresBigDecimal(Object o) {
        if (o == null) {
            return null;
        }

        if (!isBigDecimal(o)) {
            throw new DataStyleNotMatchException()
                    .getMessageProp()
                    .addParamI8n(ExceptionEnum.DATA_STYLE_NOT_MATCHED.paramNames[0], BigDecimal.class.getName())
                    .addParam(ExceptionEnum.DATA_STYLE_NOT_MATCHED.paramNames[1], o)
                    .exception();
        }

        if (o instanceof BigDecimal) {
            return (BigDecimal) o;
        }

        return new BigDecimal(String.valueOf(o));
    }

    /**
     * 将一个对象转化为长整型
     *
     * @param o 对象
     * @return 长整型
     */
    public static Long parseLong(Object o) {
        if (o == null) {
            return null;
        }

        if (!isLong(o)) {
            throw new DataStyleNotMatchException()
                    .getMessageProp()
                    .addParamI8n(ExceptionEnum.DATA_STYLE_NOT_MATCHED.paramNames[0], Long.class.getName())
                    .addParam(ExceptionEnum.DATA_STYLE_NOT_MATCHED.paramNames[1], o)
                    .exception();
        }

        if (o instanceof Long) {
            return (Long) o;
        }

        return Long.valueOf(String.valueOf(o));
    }


    /**
     * 将一个对象转化为整型
     *
     * @param o 对象
     * @return 整型
     */
    public static Integer parseInteger(Object o) {
        if (o == null) {
            return null;
        }

        if (!isInteger(o)) {
            throw new DataStyleNotMatchException()
                    .getMessageProp()
                    .addParamI8n(ExceptionEnum.DATA_STYLE_NOT_MATCHED.paramNames[0], Integer.class.getName())
                    .addParam(ExceptionEnum.DATA_STYLE_NOT_MATCHED.paramNames[1], o)
                    .exception();
        }

        if (o instanceof Integer) {
            return (Integer) o;
        }

        return Integer.valueOf(String.valueOf(o));
    }


    /**
     * 将一个对象转化为短整型
     *
     * @param o 对象
     * @return 短整型
     */
    public static Short parseShort(Object o) {
        if (o == null) {
            return null;
        }

        if (!isShort(o)) {
            throw new DataStyleNotMatchException()
                    .getMessageProp()
                    .addParamI8n(ExceptionEnum.DATA_STYLE_NOT_MATCHED.paramNames[0], Short.class.getName())
                    .addParam(ExceptionEnum.DATA_STYLE_NOT_MATCHED.paramNames[1], o)
                    .exception();
        }

        if (o instanceof Short) {
            return (Short) o;
        }

        return Short.valueOf(String.valueOf(o));
    }

    /**
     * 将long转为byte[]
     *
     * @param x long
     * @return  byte[]
     */
    public static byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(0, x);
        return buffer.array();
    }

    /**
     * 将byte[]转为long
     *
     * @param bytes 字节数组
     * @return  long
     */
    public static long bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.put(bytes, 0, bytes.length);
        buffer.flip();
        return buffer.getLong();
    }

}
