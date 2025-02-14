package com.devin.simpletools_server.domain.vo.req;

import lombok.Data;

import java.util.Date;

/**
 * 2025/2/14 23:54
 * <p>
 *     websocket的心跳检测包
 * </p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Data
public class WsPing {
    private String type;
    private Date timestamp;
    private String token;
}
