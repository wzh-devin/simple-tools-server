package com.devin.simpletools_server.websocket.builder;

import cn.hutool.core.date.DateTime;
import com.devin.simpletools_server.common.enums.RespTypeEnum;
import com.devin.simpletools_server.common.enums.WsTypeEnum;
import com.devin.simpletools_server.common.utils.ApiResult;
import com.devin.simpletools_server.domain.vo.resp.WxLoginURL;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 2025/2/11 17:39
 * <p></p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
public class BaseBuilder {

    /**
     * 构建微信登录二维码
     * @param wxMpQrCodeTicket
     * @return
     */
    public static ApiResult<?> buildResp(WxMpQrCodeTicket wxMpQrCodeTicket, String type) {
        ApiResult<WxLoginURL> result = new ApiResult<>();
        result.setType(type);
        result.setData(new WxLoginURL(wxMpQrCodeTicket.getUrl()));
        return result;
    }

    /**
     * 构建返回信息
     * @param token
     * @return
     */
    public static ApiResult<?> buildResp(String token) {
        ApiResult<String> result = new ApiResult<>();
        result.setData(token);
        result.setType(RespTypeEnum.WX_TOKEN.getType());
        return result;
    }

    /**
     * 构建心跳消息
     * @return
     */
    public static ApiResult<?> buildPong(String pongType) {
        ApiResult<Long> result = new ApiResult<>();
        result.setData(System.currentTimeMillis());
        result.setType(pongType);
        return result;
    }
}
