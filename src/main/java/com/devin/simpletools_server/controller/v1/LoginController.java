package com.devin.simpletools_server.controller.v1;

import com.devin.simpletools_server.common.annocation.ApiV1;
import com.devin.simpletools_server.common.utils.ApiResult;
import com.devin.simpletools_server.domain.vo.req.LoginReq;
import com.devin.simpletools_server.service.v1.login.LoginService;
import com.devin.simpletools_server.websocket.WebSocketService;
import com.devin.simpletools_server.websocket.impl.WebSocketServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 2025/2/10 22:34
 * <p>
 * 登录控制器
 * </p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@ApiV1
@RestController
@RequestMapping()
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    /**
     * 用户账号登录接口
     *
     * @return
     */
    @PostMapping("/login/account")
    public ApiResult<String> accountLogin(@RequestBody LoginReq loginReq) {
        return ApiResult.success(loginService.accountLogin(loginReq));
    }

    /**
     * 退出登录
     * @return
     */
    @PostMapping("/logout")
    public ApiResult<Void> logout() {
        loginService.logout();
        return ApiResult.success();
    }
}
