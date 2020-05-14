package com.quinn.util.base;

import com.quinn.util.base.exception.ParameterShouldNotEmpty;
import com.quinn.util.base.exception.UnSupportedCharsetException;
import com.quinn.util.constant.MessageTempConstants;
import com.quinn.util.constant.CharConstant;
import com.quinn.util.constant.MimeMapper;
import com.quinn.util.constant.StringConstant;

import java.io.UnsupportedEncodingException;

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
                    .addParam(MessageTempConstants.ParamName.PARAM_CLASS_NAME, StringConstant.SYSTEM_DEFAULT_CHARSET)
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
                    .addParam(MessageTempConstants.ParamName.PARAM_CLASS_NAME, StringConstant.SYSTEM_DEFAULT_CHARSET)
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

}
