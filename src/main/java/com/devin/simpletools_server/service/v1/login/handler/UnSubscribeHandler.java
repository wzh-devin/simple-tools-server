package com.devin.simpletools_server.service.v1.login.handler;

import com.devin.simpletools_server.service.v1.login.WxMsgService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 2025/2/11 17:13
 * <p>
 *     公众号取消关注执行器
 * </p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Component
@Slf4j
public class UnSubscribeHandler extends AbstractHandler {

    private final WxMsgService wxMsgService;

    public UnSubscribeHandler(@Lazy WxMsgService wxMsgService) {
        this.wxMsgService = wxMsgService;
    }

    /**
     * 公众号取消关注事件
     * @param wxMpXmlMessage
     * @param map
     * @param wxMpService
     * @param wxSessionManager
     * @return
     * @throws WxErrorException
     */
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        log.info("公众号取消关注事件触发");
        // TODO 微信用户取消关注，进行下线操作
        return null;
    }
}
