package com.devin.simpletools_server.common.constant;

/**
 * 2025/2/15 21:00
 * <p>
 *     redis常量类
 * </p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
public class RedisKey {
    private static final String BASE_KEY = "simple-tools:";

    public static final String USER_ONLINE_KEY = "user_online:%s";

    public static String generateKey(String key, Object ...args) {
        return String.format(BASE_KEY + key, args);
    }
}
