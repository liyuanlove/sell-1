package com.imooc.utils;

import java.util.Random;

/**
 * Created by songyouyu on 2018/5/23
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式： 时间 + 唯一数
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
