package com.cw;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * @author david.cai
 * @date 2018/8/3
 **/
public class EncodeDemo {
    /**
     * 加密
     * @param secret 密钥
     * @param value 待加密的手机号
     * @return 加密后的字符串
     */
    public static String encrypt(String secret, String value) {
        SecretKeySpec keySpec = getKey(secret);
        IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
            byte[] encrypted = cipher.doFinal(value.getBytes("UTF-8"));
            return parseByte2HexStr(encrypted);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成加密的密钥，保证长度为16位
     * @param secret 用户的密钥
     * @return 生成的密钥
     */
    private static SecretKeySpec getKey(String secret) {
        byte[] bytes;
        try {
            bytes = secret.getBytes("UTF-8");
            return new SecretKeySpec(Arrays.copyOf(bytes, 16), "AES");
        } catch (UnsupportedEncodingException e) {
            System.out.println(String.format("生成加密钥异常,原因：%s", e.getMessage()));
        }
        return null;
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    private static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
}
