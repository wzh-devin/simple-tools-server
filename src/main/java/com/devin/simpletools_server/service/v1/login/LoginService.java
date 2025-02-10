package com.devin.simpletools_server.service.v1.login;

import com.devin.simpletools_server.domain.vo.req.LoginReq;

/**
 * 2025/2/10 22:39
 * <p>
 *     登录服务
 * </p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
public interface LoginService {
    /**
     * 账号登录
     * @param loginReq
     * @return
     */
    String accountLogin(LoginReq loginReq);
}
