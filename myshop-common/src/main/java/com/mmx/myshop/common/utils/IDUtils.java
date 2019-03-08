package com.mmx.myshop.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * ID 工具类
 * <p>Title: IDUtils</p>
 * <p>Description: </p>
 *
 * @author Lusifer
 * @version 1.0.0
 * @date 2018/9/19 9:15
 */
public class IDUtils {
    public static long count = 0;
    public static int idLength = 3;

    /**
     * 生成 ID
     * @return
     */
    public static long genId() {
        long currentTimeMillis = System.currentTimeMillis();
        long random = (long) (100 + Math.random() * 899);
        count++;

        if (String.valueOf(count).length() > idLength) {
            count = 0;
        }

        String strId = String.format("%s%s%s", currentTimeMillis, random, StringUtils.leftPad(String.valueOf(count), idLength, "0"));
        return Long.parseLong(strId);
    }
}
