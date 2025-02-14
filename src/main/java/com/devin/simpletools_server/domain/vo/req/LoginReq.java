package com.devin.simpletools_server.domain.vo.req;

import lombok.Data;

/**
 * 2025/2/10 22:47
 * <p>
 *     登录请求处理类
 * </p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Data
public class LoginReq {

    private String username;

    private String password;

}
