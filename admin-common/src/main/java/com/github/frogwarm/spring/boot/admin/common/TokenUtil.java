package com.github.frogwarm.spring.boot.admin.common;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 验证和生成
 *
 * @author tuzy
 */
public class TokenUtil {

    /**
     * 生成token
     *
     * @param secret    秘钥
     * @param timestamp 时间戳
     * @param nonceStr  随机字符串
     * @return token
     */
    public static String get(String secret, String timestamp, String nonceStr) {
        return sign(secret + timestamp + nonceStr);
    }

    /**
     * 验证token
     *
     * @param token     待验证token
     * @param secret    秘钥
     * @param timestamp 时间戳
     * @param nonceStr  随机字符串
     * @return 验证结果
     */
    public static boolean verification(String token, String secret, String timestamp, String nonceStr) {
        return token.equals(get(secret, timestamp, nonceStr));
    }


    private static String sign(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            return byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("生成token失败");
        }
    }


    /**
     * 将byte转为16进制
     *
     * @param bytes 字节
     * @return 16进制字符串
     */
    private static String byte2Hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        String temp;
        for (byte b : bytes) {
            temp = Integer.toHexString(b & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                sb.append("0");
            }
            sb.append(temp);
        }
        return sb.toString();
    }
}
