package com.devin.simpletools_server.service.v1.login;

import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * 2025/2/11 20:37
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
public interface WxMsgService {
    /**
     * 微信扫码事件执行逻辑
     * @param wxMpXmlMessage
     * @return
     */
    WxMpXmlOutMessage scan(WxMpXmlMessage wxMpXmlMessage);

    /**
     * 微信授权登录函数
     * @param userInfo
     */
    void authorize(WxOAuth2UserInfo userInfo);
}
