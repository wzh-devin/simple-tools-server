package com.devin.simpletools_server.service.v1.login.impl;

import cn.hutool.core.util.RandomUtil;
import com.devin.simpletools_server.common.constant.RedisKey;
import com.devin.simpletools_server.common.event.UserOfflineEvent;
import com.devin.simpletools_server.common.utils.*;
import com.devin.simpletools_server.domain.eneity.login.AccountUser;
import com.devin.simpletools_server.domain.eneity.login.Users;
import com.devin.simpletools_server.domain.eneity.login.WxUser;
import com.devin.simpletools_server.domain.vo.req.LoginReq;
import com.devin.simpletools_server.mapper.v1.login.AccountUserMapper;
import com.devin.simpletools_server.mapper.v1.login.UsersMapper;
import com.devin.simpletools_server.mapper.v1.login.WxUserMapper;
import com.devin.simpletools_server.websocket.builder.BaseBuilder;
import com.devin.simpletools_server.service.v1.builder.UserBuilder;
import com.devin.simpletools_server.service.v1.login.LoginService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Objects;

/**
 * 2025/2/10 22:39
 * <p></p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    private static final Duration DURATION = Duration.ofHours(5);

    @Autowired
    private AccountUserMapper accountUserMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private WxUserMapper wxUserMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    @Override
    public String accountLogin(LoginReq loginReq) {
        AccountUser accountUser = accountUserMapper.getAccountUser(loginReq);
        if (Objects.nonNull(accountUser)) {
            // 保存用户信息
            RedisUtil.set(RedisKey.generateKey(RedisKey.USER_ONLINE_KEY, accountUser.getUserId()), accountUser.getUserId());
            // 返回用户token信息
            return jwtUtil.createToken(accountUser.getUserId());
        }
        return "";
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void registerWx(String openId) {
        Users user = UserBuilder.buildBaseUsers();
        int userInsert = usersMapper.insert(user);
        AssertUtil.isTrue(userInsert == 1, "微信用户注册失败");
        WxUser wx = UserBuilder.buildWxUser(openId, user);
        int wxInsert = wxUserMapper.insert(wx);
        AssertUtil.isTrue(wxInsert == 1, "微信用户注册失败");
    }

    @Override
    public void logout() {
        log.info("登出：{}", RequestContext.getContext());
        // 用户登出通知
        applicationEventPublisher.publishEvent(new UserOfflineEvent(this, RequestContext.getContext()));
        // 将uid移除上下文
        RequestContext.removeContext();
    }
}
