package com.devin.simpletools_server.common.config;

import com.devin.simpletools_server.common.properties.WxMpProperties;
import com.devin.simpletools_server.service.v1.login.handler.ScanHandler;
import com.devin.simpletools_server.service.v1.login.handler.SubscribeHandler;
import com.devin.simpletools_server.service.v1.login.handler.UnSubscribeHandler;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.annotation.PostConstruct;

import static me.chanjar.weixin.common.api.WxConsts.EventType.*;
import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType.EVENT;

/**
 * 2025/2/11 15:19
 * <p></p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(WxMpProperties.class)
public class WxMpConfiguration {

    @Lazy
    private final SubscribeHandler subscribeHandler;

    @Lazy
    private final UnSubscribeHandler unSubscribeHandler;

    @Lazy
    private final ScanHandler scanHandler;

    private final WxMpProperties properties;

    @Bean
    public WxMpService wxMpService() {
        WxMpService service = new WxMpServiceImpl();

        WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
        config.setAppId(properties.getAppId());
        config.setSecret(properties.getSecret());
        config.setToken(properties.getToken());
        config.setAesKey(properties.getAesKey());

        service.setWxMpConfigStorage(config);

        return service;
    }

    /**
     * 配置路由规则
     * @return
     */
    @Bean
    public WxMpMessageRouter messageRouter(WxMpService wxMpService) {
        final WxMpMessageRouter newRouter = new WxMpMessageRouter(wxMpService);

        // 微信用户关注执行路由
        newRouter.rule().async(false).msgType(EVENT).event(SUBSCRIBE).handler(this.subscribeHandler).end();

        // 微信用户取消关注执行路由
        newRouter.rule().async(false).msgType(EVENT).event(UNSUBSCRIBE).handler(this.unSubscribeHandler).end();

        // 微信用户扫码执行路由
        newRouter.rule().async(false).msgType(EVENT).event(SCAN).handler(this.scanHandler).end();

        return newRouter;
    }

}
