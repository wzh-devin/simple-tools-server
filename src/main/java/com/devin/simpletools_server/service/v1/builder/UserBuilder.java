package com.devin.simpletools_server.service.v1.builder;

import com.devin.simpletools_server.common.enums.ActiveStatusEnum;
import com.devin.simpletools_server.common.utils.SnowFlake;
import com.devin.simpletools_server.domain.eneity.login.Users;
import com.devin.simpletools_server.domain.eneity.login.WxUser;

import java.time.LocalDateTime;

/**
 * 2025/2/11 21:27
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
public class UserBuilder {

    public static Users buildBaseUsers() {
        Users users = Users.builder()
            .id(SnowFlake.nextId())
            .isActive(ActiveStatusEnum.NORMAL.getStatus())
            .lastLogin(LocalDateTime.now())
            .build();
        users.fillTime();
        return users;
    }

    /**
     * 构建微信子表
     * @param openId
     * @return
     */
    public static WxUser buildWxUser(String openId, Users user) {
        WxUser wxUser = WxUser.builder()
            .id(SnowFlake.nextId())
            .userId(user.getId())
            .openid(openId)
            .build();
        wxUser.fillTime();
        return wxUser;
    }
}
