package com.jzj.demo.entity.factory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    private static final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 按照UTF-8编码对字符串进行加密，如果字符串不是UTF-8编码会抛出异常
     *
     * @param s
     * @return
     */
    public static String getMD5String(String s) {
        return getMD5String(s.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 按照UTF-8编码对字符串进行加密，如果字符串不是UTF-8编码会抛出异常
     *
     * @param bytes
     * @return
     */
    public static String getMD5String(byte[] bytes) {
        try {
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            messagedigest.update(bytes);
            return bufferToHex(messagedigest.digest());
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(MD5Util.class.getName() + "初始化失败，MessageDigest不支持MD5Util。" + ex.getLocalizedMessage());
        }
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }
}
