package com.devin.simpletools_server.websocket.impl;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson2.JSONObject;
import com.auth0.jwt.interfaces.Claim;
import com.devin.simpletools_server.common.enums.RespTypeEnum;
import com.devin.simpletools_server.common.enums.WsTypeEnum;
import com.devin.simpletools_server.common.event.UserOnlineEvent;
import com.devin.simpletools_server.common.utils.ApiResult;
import com.devin.simpletools_server.common.utils.JwtUtil;
import com.devin.simpletools_server.domain.eneity.login.WxUser;
import com.devin.simpletools_server.domain.vo.req.WsPing;
import com.devin.simpletools_server.service.v1.login.LoginService;
import com.devin.simpletools_server.service.v1.login.UserService;
import com.devin.simpletools_server.websocket.WebSocketService;
import com.devin.simpletools_server.websocket.builder.BaseBuilder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 2025/2/13 19:05
 * <p>
 * 微信的websocket服务
 * </p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Component
@ServerEndpoint("/ws/wx/{type}")
public class WebSocketServiceImpl implements WebSocketService {

    private static WxMpService wxMpService;

    private static UserService userService;

    private static JwtUtil jwtUtil;

    private static ApplicationEventPublisher applicationEventPublisher;

    private static final Duration DURATION = Duration.ofHours(5);

    /**
     * 记录下连接的用户，根据SESSION_POOL，来区分向不同用户发送消息
     * key: 根据Session生成的Code值
     * value: Session
     */
    protected static final ConcurrentHashMap<Integer, Session> SESSION_POOL = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("type") Integer type) {
        switch (WsTypeEnum.of(type)) {
            case SCAN_QR:
                loginQR(session, RespTypeEnum.WX_LOGIN.getType());
                break;
            default:
                break;
        }
    }

    @SneakyThrows
    @OnMessage
    public void onMessage(Session session, String message) {
        // 接收心跳消息
        WsPing wsPing = JSONObject.parseObject(message, WsPing.class);
        if (WsTypeEnum.QR_PING.getDesc().equals(wsPing.getType())) {
            loginQR(session, WsTypeEnum.QR_PONG.getDesc());
        }
        if (WsTypeEnum.LOGIN_PING.getDesc().equals(wsPing.getType())) {
            // 处理登录逻辑
//            sendMsg(session, BaseBuilder.buildPong(WsTypeEnum.LOGIN_PONG.getDesc()));
            loginCheck(session, wsPing.getToken());
        }
    }

    @OnError
    public void onError(Session session, Throwable t) {
        log.error("error, {}", t.getMessage());
    }

    @OnClose
    public void onClose() {
        log.info("onClose");
    }

    /**
     * 生成临时二维码
     */
    @SneakyThrows
    private void loginQR(Session session, String type) {
        // 生成登陆的code值
        Integer code = generateCode(session);
        // 请求微信服务平台，返回临时的二维码
        WxMpQrCodeTicket wxMpQrCodeTicket = wxMpService.getQrcodeService().qrCodeCreateTmpTicket(code, (int) DURATION.toSeconds());
        // 将临时的二维码发送给前端
//        session.getBasicRemote().sendText(JSONObject.toJSONString(BaseBuilder.buildResp(wxMpQrCodeTicket)));
        sendMsg(session, BaseBuilder.buildResp(wxMpQrCodeTicket, type));
    }

    @Override
    public void loginScanSuccess(Integer code, String openId) {
        // 获取对应的session
        Session session = SESSION_POOL.get(code);
        if (Objects.isNull(session)) return;
        // 获取用户的信息
        WxUser user = userService.getWxUser(openId);
        // jwt加密
        String token = jwtUtil.createToken(user.getUserId());
        // 通知用户最后一次上线信息
        applicationEventPublisher.publishEvent(new UserOnlineEvent(this, user));
        // 将token信息返回到前端
        sendMsg(session, BaseBuilder.buildResp(token));
    }

    @SneakyThrows
    private void sendMsg(Session session, ApiResult<?> apiResult) {
        session.getBasicRemote().sendText(JSONObject.toJSONString(apiResult));
    }

    /**
     * 登录心跳检查
     * @param session
     * @param token
     */
    private void loginCheck(Session session, String token) {
        // 校验token
        Long openId = jwtUtil.getUidOrNull(token);
        if (Objects.isNull(openId)) {
            // TODO 发送校验失败消息，让用户重新登录
            sendMsg(session, BaseBuilder.buildPong(WsTypeEnum.TOKEN_PONG_FALSE.getDesc()));
            return;
        }
        // 发送成功消息，不用让用户继续操作
        sendMsg(session, BaseBuilder.buildPong(WsTypeEnum.LOGIN_PONG.getDesc()));
    }

    /**
     * 生成唯一的code，并保存与session的映射
     */
    private Integer generateCode(Session session) {
        Integer code;
        do {
            code = RandomUtil.randomInt(Integer.MAX_VALUE);
        } while (Objects.nonNull(SESSION_POOL.putIfAbsent(code, session)));
        return code;
    }

    /**
     * 由于websocket连接并不是单例的，所以spring并不能直接注入
     * 会产生注入不了的问题，所以这里使用setter方法构造注入
     *
     * @param wxMpService
     */
    @Autowired
    public void setWxMpService(WxMpService wxMpService) {
        WebSocketServiceImpl.wxMpService = wxMpService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        WebSocketServiceImpl.userService = userService;
    }

    @Autowired
    public void setJwtUtil(JwtUtil jwtUtil) {
        WebSocketServiceImpl.jwtUtil = jwtUtil;
    }

    @Autowired
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        WebSocketServiceImpl.applicationEventPublisher = applicationEventPublisher;
    }
}
