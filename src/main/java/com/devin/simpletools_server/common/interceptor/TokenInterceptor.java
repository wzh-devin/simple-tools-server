package com.devin.simpletools_server.common.interceptor;

import com.devin.simpletools_server.common.constant.RedisKey;
import com.devin.simpletools_server.common.utils.JwtUtil;
import com.devin.simpletools_server.common.utils.RedisUtil;
import com.devin.simpletools_server.common.utils.RequestContext;
import com.devin.simpletools_server.mapper.v1.login.AccountUserMapper;
import com.devin.simpletools_server.mapper.v1.login.WxUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.Optional;

/**
 * 2025/2/15 21:33
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("进入拦截器");

        // 浏览器预检请求，直接放行
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }

        String token = getToken(request);

        // 校验token
        Long uid = jwtUtil.getUidOrNull(token);

        if (Objects.isNull(uid)) {
            return false;
        }

        // 将uid存放到线程中
        RequestContext.setContext(uid);

        // 放行
        return true;
    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return Optional.ofNullable(header)
            .map(String::trim)
            .filter(s -> s.startsWith("Bearer "))
            .map(s -> s.substring("Bearer ".length()))
            .orElseThrow(() -> new RuntimeException("token is null"));
    }
}
