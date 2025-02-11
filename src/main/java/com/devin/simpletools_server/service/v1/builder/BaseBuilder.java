package com.devin.simpletools_server.service.v1.builder;

import com.devin.simpletools_server.common.enums.RespTypeEnum;
import com.devin.simpletools_server.common.utils.ApiResult;
import com.devin.simpletools_server.domain.vo.resp.WxLoginURL;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;

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
    public static ApiResult<?> buildResp(WxMpQrCodeTicket wxMpQrCodeTicket) {
        ApiResult<WxLoginURL> result = new ApiResult<>();
        result.setType(RespTypeEnum.WX_LOGIN.getType());
        result.setData(new WxLoginURL(wxMpQrCodeTicket.getUrl()));
        return result;
    }

}
