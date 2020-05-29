package com.quinn.util.base;

import com.quinn.util.constant.StringConstant;
import lombok.SneakyThrows;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

/**
 * Md5 加密工具类
 *
 * @author Qunhua.Liao
 * @since 2020-05-29
 */
public final class Md5EncryptUtil {

    private Md5EncryptUtil() {
    }

    /**
     * Md5 加密
     *
     * @param data 源字符串
     * @return 加密字符串
     */
    @SneakyThrows
    public static String encryptMD5(String data) {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] bytes = md.digest(data.getBytes(StringConstant.SYSTEM_DEFAULT_CHARSET));
        return StringUtil.byte2hex(bytes);
    }

    /**
     * SHA 加密
     *
     * @param data 源字符串
     * @return 加密字符串
     */
    @SneakyThrows
    public static String encryptSHA(String data) {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] bytes = md.digest(data.getBytes(StringConstant.SYSTEM_DEFAULT_CHARSET));
        return StringUtil.byte2hex(bytes);
    }

    /**
     * HmacMD5 加密
     *
     * @param data   源数据
     * @param secret 盐
     * @return 教秘字符串
     */
    @SneakyThrows
    public static String encryptHMAC(String data, String secret) {
        SecretKey secretKey = new SecretKeySpec(secret.getBytes(StringConstant.SYSTEM_DEFAULT_CHARSET), "HmacMD5");
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        byte[] bytes = mac.doFinal(data.getBytes(StringConstant.SYSTEM_DEFAULT_CHARSET));
        return StringUtil.byte2hex(bytes);
    }

    public static void main(String[] args) {
        System.out.println(encryptHMAC("a1234567", "lqh"));
    }

}
