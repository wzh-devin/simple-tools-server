package com.devin.simpletools_server.service.v1.login.impl;

import com.devin.simpletools_server.common.utils.JwtUtil;
import com.devin.simpletools_server.domain.eneity.login.AccountUser;
import com.devin.simpletools_server.domain.vo.req.LoginReq;
import com.devin.simpletools_server.mapper.v1.login.AccountUserMapper;
import com.devin.simpletools_server.service.v1.login.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private AccountUserMapper accountUserMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String accountLogin(LoginReq loginReq) {
        AccountUser accountUser =  accountUserMapper.getAccountUser(loginReq);
        if (Objects.nonNull(accountUser)) {
            return jwtUtil.createToken(accountUser.getId());
        }
        return "";
    }
}
