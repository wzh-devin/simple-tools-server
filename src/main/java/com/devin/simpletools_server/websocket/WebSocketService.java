package com.devin.simpletools_server.websocket;

/**
 * 2025/2/13 23:57
 * <p>
 *     websocket服务接口
 * </p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
public interface WebSocketService {
    /**
     * 微信扫码登录成功
     * @param code
     * @param openId
     */
    void loginScanSuccess(Integer code, String openId);
}
