package com.quinn.util.base;

import com.quinn.util.base.enums.CommonMessageEnum;
import com.quinn.util.base.exception.ParameterShouldNotEmpty;
import com.quinn.util.base.exception.UnSupportedCharsetException;
import com.quinn.util.constant.CharConstant;
import com.quinn.util.constant.CommonParamName;
import com.quinn.util.constant.MimeMapper;
import com.quinn.util.constant.StringConstant;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.UUID;

/**
 * 字符串工具类
 *
 * @author Qunhua.Liao
 * @since 2020-03-29
 */
public final class StringUtil {

    private StringUtil() {
    }

    /**
     * 判断字符串是否为空
     *
     * @param str 字符串
     * @return 空为true
     */
    public final static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 判断字符串是否为空
     *
     * @param str 字符串
     * @return 空为true
     */
    public final static boolean isNotEmpty(String str) {
        return str != null && str.length() > 0;
    }

    /**
     * 转换成String
     *
     * @param obj 对象
     * @return 字符串
     */
    public static String parseString(Object obj) {
        if (obj == null) {
            return null;
        }
        return String.valueOf(obj);
    }

    /**
     * 字符串分解
     *
     * @param str       字符串
     * @param separator 分割符
     * @return 数组
     */
    public static String[] split(String str, String separator) {
        if (str == null) {
            return null;
        }
        return str.split(separator);
    }

    /**
     * 获取字符串的字节数组（默认编码）
     *
     * @param str 字符串
     * @return 数组
     */
    public static byte[] getBytes(String str) {
        if (isEmpty(str)) {
            throw new ParameterShouldNotEmpty();
        }

        try {
            return str.getBytes(StringConstant.SYSTEM_DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new UnSupportedCharsetException().getMessageProp()
                    .addParam(CommonParamName.PARAM_CLASS_NAME, StringConstant.SYSTEM_DEFAULT_CHARSET)
                    .exception();
        }
    }

    /**
     * 从字节数组变成字符串
     *
     * @param bytes 字节数组
     * @return 字符串
     */
    public static String forBytes(byte[] bytes) {
        if (bytes == null) {
            return null;
        }

        try {
            return new String(bytes, StringConstant.SYSTEM_DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new UnSupportedCharsetException().getMessageProp()
                    .addParam(CommonParamName.PARAM_CLASS_NAME, StringConstant.SYSTEM_DEFAULT_CHARSET)
                    .exception();
        }
    }

    /**
     * 通过文件名获取文件类型
     *
     * @param fileName
     * @return
     */
    public static String getFileTypeByName(String fileName) {
        if (isEmpty(fileName)) {
            return MimeMapper.DEFAULT_FILE_TYPE;
        }

        int lastDotIndex = fileName.lastIndexOf(CharConstant.DOT);
        if (lastDotIndex > 0) {
            String typeName = fileName.substring(lastDotIndex + 1);
            if (MimeMapper.MIME_MAP.containsKey(typeName)) {
                return typeName;
            }
        }

        return MimeMapper.DEFAULT_FILE_TYPE;
    }

    /**
     * 在框架内为空(一些特殊字符串)
     *
     * @return 框架内表示空的
     */
    public static boolean isEmptyInFrame(String str) {
        boolean result = isEmpty(str);
        if (result) {
            return true;
        }
        return StringConstant.TOP_OF_DATA.equals(str) || StringConstant.NULL_OF_STRING.equals(str)
                || StringConstant.NONE_OF_DATA.equals(str);
    }

    /**
     * 判断一个字符串是否为Json格式
     *
     * @param string
     * @return Json: true
     */
    public static boolean isJson(String string) {
        if (string == null) {
            return false;
        }
        return string.startsWith(StringConstant.CHAR_OPEN_BRACE) && string.endsWith(StringConstant.CHAR_CLOSE_BRACE);
    }

    /**
     * 判断一个字符串是否为JsonArray格式
     *
     * @param string
     * @return JsonArray : true
     */
    public static boolean isJsonArray(String string) {
        if (string == null) {
            return false;
        }
        return string.startsWith(StringConstant.CHAR_OPEN_BRACKET) && string.endsWith(StringConstant.CHAR_CLOSE_BRACKET);
    }

    /**
     * 字节数组转字符串
     *
     * @param bytes 字节数组
     * @return 字符串
     */
    public static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

    /**
     * 获取异常信息
     *
     * @param e 异常对象
     * @return 异常信息
     */
    public static String getStringFromException(Throwable e) {
        String result;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            PrintStream ps = new PrintStream(bos, false, StringConstant.SYSTEM_DEFAULT_CHARSET);
            e.printStackTrace(ps);
            result = bos.toString(StringConstant.SYSTEM_DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException ee) {
            throw new UnSupportedCharsetException()
                    .addParam(CommonMessageEnum.CHARSET_NOT_SUPPORTED.paramNames[0], StringConstant.SYSTEM_DEFAULT_CHARSET)
                    .exception()
                    ;
        }
        return result;
    }

    /**
     * 生成UUID去 -
     *
     * @return UUID去 -
     */
    public static String uuid() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-", "");
    }

    /**
     * 拼接字符串
     *
     * @param strings 零碎字符串
     * @return 拼接字符串
     */
    public static String concat(String... strings) {
        StringBuilder query = new StringBuilder();
        for (String str : strings) {
            if (isEmpty(str)) {
                continue;
            }
            query.append(str);
        }
        return query.toString();
    }

    /**
     * 拼接字符串
     *
     * @param strings 零碎字符串
     * @return 拼接字符串
     */
    public static String join(Collection<String> strings, String delimiter) {
        StringBuilder query = new StringBuilder();
        for (String str : strings) {
            query.append(str).append(delimiter);
        }

        if (query.length() > 0) {
            query.delete(query.length() - delimiter.length(), query.length());
        }
        return query.toString();
    }

    /**
     * 根据长度省略显示
     *
     * @param content   内容
     * @param maxLength 最大长度
     * @return 省略后的值
     */
    public static String omitByLength(String content, int maxLength) {
        if (content.length() <= maxLength) {
            return content;
        }

        return content.substring(0, maxLength - StringConstant.OMIT_STRING.length()) + StringConstant.OMIT_STRING;
    }

    /**
     * 首字母小写
     *
     * @return 首字母小写
     */
    public static String firstCharLowercase(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    /**
     * 首字母大写
     *
     * @return 首字母大写
     */
    public static String firstCharUppercase(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    /**
     * 源串是否以withinStr为起始
     *
     * @return 起始为true
     */
    public static boolean startWith(String srcStr, String withinStr) {
        if (srcStr == null || withinStr == null) {
            return false;
        }
        return srcStr.startsWith(withinStr);
    }

}
