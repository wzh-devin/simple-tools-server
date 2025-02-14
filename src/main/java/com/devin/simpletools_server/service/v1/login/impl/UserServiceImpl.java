package com.devin.simpletools_server.service.v1.login.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.devin.simpletools_server.domain.eneity.login.WxUser;
import com.devin.simpletools_server.mapper.v1.login.UsersMapper;
import com.devin.simpletools_server.mapper.v1.login.WxUserMapper;
import com.devin.simpletools_server.service.v1.login.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 2025/2/14 15:39
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final WxUserMapper wxUserMapper;

    @Override
    public WxUser getWxUser(String openId) {
        return wxUserMapper.selectOne(new LambdaQueryWrapper<WxUser>().eq(WxUser::getOpenid, openId));
    }
}
