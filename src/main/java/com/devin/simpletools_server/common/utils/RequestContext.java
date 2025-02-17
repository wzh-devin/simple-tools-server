package com.devin.simpletools_server.common.utils;

/**
 * 2025/2/13 19:13
 * <p>
 *     请求会话上下文
 * </p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
public class RequestContext {
    private static final ThreadLocal<Long> LOCAL = new ThreadLocal<>();

    public static void setContext(Long context) {
        LOCAL.set(context);
    }

    public static Long getContext() {
        return LOCAL.get();
    }

    public static void removeContext() {
        LOCAL.remove();
    }
}
