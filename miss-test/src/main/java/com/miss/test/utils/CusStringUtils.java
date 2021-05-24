package com.miss.test.utils;

import java.util.UUID;

/**
 * @project: miss-mvc-web
 * @package: com.miss.test.utils
 * @author: miss
 * @date: 2021/5/24 12:10
 * @since:
 * @history: 1.2021/5/24 created by miss
 */
public class CusStringUtils {

    public static String generateId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
