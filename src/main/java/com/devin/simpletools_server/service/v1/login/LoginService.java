package com.devin.simpletools_server.service.v1.login;

import com.devin.simpletools_server.common.utils.ApiResult;
import com.devin.simpletools_server.domain.vo.req.LoginReq;
import com.devin.simpletools_server.domain.vo.resp.WxLoginURL;

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

//    /**
//     * 获取登录二维码
//     * @return
//     */
//    ApiResult<?> wxLoginQR();

    /**
     * 注册微信用户
     */
    void registerWx(String openId);
}
