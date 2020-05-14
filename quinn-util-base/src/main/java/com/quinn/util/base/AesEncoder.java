package com.quinn.util.base;

import com.quinn.util.base.exception.BaseBusinessException;
import com.quinn.util.constant.NumberConstant;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * 加密解密（AES）
 *
 * @author Qunhua.Liao
 * @since 2020-05-10
 */
public class AesEncoder {

    /**
     * 编码格式
     */
    private static final String ENCODING = "UTF-8";

    /**
     * 加密算法
     */
    public static final String KEY_ALGORITHM = "AES";

    /**
     * 签名算法
     */
    public static final String SIGN_ALGORITHMS = "SHA1PRNG";

    /**
     * 加密
     *
     * @param content  内容
     * @param password 盐
     * @return 加密串
     */
    public static String encode(String content, String password) {
        try {
            EncoderHolder encoderHolder = generateEncoderHolder(password);
            byte[] byteContent = content.getBytes(ENCODING);
            encoderHolder.cipher.init(Cipher.ENCRYPT_MODE, encoderHolder.secretKeySpec);
            byte[] byteResult = encoderHolder.cipher.doFinal(byteContent);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteResult.length; i++) {
                String hex = Integer.toHexString(byteResult[i] & 0xFF);
                if (hex.length() == 1) {
                    hex = '0' + hex;
                }
                sb.append(hex.toUpperCase());
            }

            return sb.toString();
        } catch (Exception e) {
            throw new BaseBusinessException();
        }
    }

    /**
     * 解密
     *
     * @param content  内容
     * @param password 盐
     * @return 解密串
     */
    public static String decode(String content, String password) {
        byte[] byteResult = new byte[content.length() / NumberConstant.INT_TWO];
        for (int i = 0; i < content.length() / NumberConstant.INT_TWO; i++) {
            int high = Integer.parseInt(content.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(content.substring(i * 2 + 1, i * 2 + 2), 16);
            byteResult[i] = (byte) (high * 16 + low);
        }

        try {
            EncoderHolder encoderHolder = generateEncoderHolder(password);
            encoderHolder.cipher.init(Cipher.DECRYPT_MODE, encoderHolder.secretKeySpec);
            byte[] result = encoderHolder.cipher.doFinal(byteResult);
            return new String(result, ENCODING);
        } catch (Exception e) {
            throw new BaseBusinessException();
        }
    }

    /**
     * 生成加密工具持有对象
     *
     * @param password 密码
     * @return 加密工具持有对象
     */
    private static EncoderHolder generateEncoderHolder(String password) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(KEY_ALGORITHM);
        SecureRandom random = SecureRandom.getInstance(SIGN_ALGORITHMS);
        random.setSeed(password.getBytes(ENCODING));
        keyGen.init(128, random);
        SecretKey secretKey = keyGen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, KEY_ALGORITHM);
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        return new EncoderHolder(cipher, secretKeySpec);
    }

    /**
     * 机密工具持有对象
     *
     * @author Qunhua.Liao
     * @since 2020-05-10
     */
    private static class EncoderHolder {

        private EncoderHolder(Cipher cipher, SecretKeySpec secretKeySpec) {
            this.cipher = cipher;
            this.secretKeySpec = secretKeySpec;
        }

        Cipher cipher;

        SecretKeySpec secretKeySpec;

    }

    public static void main(String[] args) {
        System.out.println(encode("ming-bpm", "quinn-service"));
        System.out.println(encode("MingBpm!", "quinn-service"));
    }

}