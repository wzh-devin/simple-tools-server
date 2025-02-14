package com.devin.simpletools_server.service.v1.login;

import com.devin.simpletools_server.domain.eneity.login.WxUser;

/**
 * 2025/2/14 15:39
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
public interface UserService {
    /**
     * 获取微信用户信息
     * @param openId
     * @return
     */
    WxUser getWxUser(String openId);
}
