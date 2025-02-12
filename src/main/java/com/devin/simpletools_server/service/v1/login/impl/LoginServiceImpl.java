package com.devin.simpletools_server.service.v1.login.impl;

import cn.hutool.core.util.RandomUtil;
import com.devin.simpletools_server.common.utils.ApiResult;
import com.devin.simpletools_server.common.utils.AssertUtil;
import com.devin.simpletools_server.common.utils.JwtUtil;
import com.devin.simpletools_server.domain.eneity.login.AccountUser;
import com.devin.simpletools_server.domain.eneity.login.Users;
import com.devin.simpletools_server.domain.eneity.login.WxUser;
import com.devin.simpletools_server.domain.vo.req.LoginReq;
import com.devin.simpletools_server.domain.vo.resp.WxLoginURL;
import com.devin.simpletools_server.mapper.v1.login.AccountUserMapper;
import com.devin.simpletools_server.mapper.v1.login.UsersMapper;
import com.devin.simpletools_server.mapper.v1.login.WxUserMapper;
import com.devin.simpletools_server.service.v1.builder.BaseBuilder;
import com.devin.simpletools_server.service.v1.builder.UserBuilder;
import com.devin.simpletools_server.service.v1.login.LoginService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.service.WxService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Objects;
import java.util.Random;

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
    private WxMpService wxMpService;


    @Override
    public String accountLogin(LoginReq loginReq) {
        AccountUser accountUser = accountUserMapper.getAccountUser(loginReq);
        if (Objects.nonNull(accountUser)) {
            return jwtUtil.createToken(accountUser.getId());
        }
        return "";
    }

    @SneakyThrows
    @Override
    public ApiResult<?> wxLoginQR() {
        // 生成随机登陆的code值
        Integer code = generateLoginCode();
        // 请求微信服务平台，返回临时的二维码
        WxMpQrCodeTicket wxMpQrCodeTicket = wxMpService.getQrcodeService()
                .qrCodeCreateTmpTicket(code, (int) DURATION.toSeconds());
        return BaseBuilder.buildResp(wxMpQrCodeTicket);
    }

    @Override
    public void registerWx(String openId) {
        Users user = UserBuilder.buildBaseUsers();
        int userInsert = usersMapper.insert(user);
        AssertUtil.isTrue(userInsert == 1, "微信用户注册失败");
        WxUser wx = UserBuilder.buildWxUser(openId, user);
        int wxInsert = wxUserMapper.insert(wx);
        AssertUtil.isTrue(wxInsert == 1, "微信用户注册失败");
    }

    /**
     * 获取随机登录code码
     * @return
     */
    private Integer generateLoginCode() {
        return RandomUtil.randomInt(Integer.MAX_VALUE);
    }
}
