package com.okex.okex.utils;

import java.security.MessageDigest;

public class MD5UTIL {
    private static final String PASE_FAIL="-1";

    /**

     * 加密Integer(返回字符串，加密后持有字符，无法返回int)

     * @param key

     * @return

     */

    public static String toMD5Str(Integer key) {
        if(key==null){
            return PASE_FAIL;

        }

        return toMD5Str(key.toString());

    }

    /**

     * 加密Long

     * @param key

     * @return

     */

    public static String toMD5Str(Long key) {
        if(key==null){
            return PASE_FAIL;

        }

        return toMD5Str(key.toString());

    }

    /**

     * 加密str

     * @param key

     * @return

     */

    public static String toMD5Str(String key) {
        if(key==null||"".equals(key)){
            return PASE_FAIL;

        }

        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'

        };

        try {
            byte[] btInput = key.getBytes();

// 获得MD5摘要算法的 MessageDigest 对象

            MessageDigest mdInst = MessageDigest.getInstance("MD5");

// 使用指定的字节更新摘要

            mdInst.update(btInput);

// 获得密文

            byte[] md = mdInst.digest();

// 把密文转换成十六进制的字符串形式

            int j = md.length;

            char str[] = new char[j * 2];

            int k = 0;

            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];

                str[k++] = hexDigits[byte0 >>> 4 & 0xf];

                str[k++] = hexDigits[byte0 & 0xf];

            }

            return new String(str);

        } catch (Exception e) {
            return PASE_FAIL;

        }

    }

}
