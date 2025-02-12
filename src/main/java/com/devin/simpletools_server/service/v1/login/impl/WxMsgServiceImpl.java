package com.devin.simpletools_server.service.v1.login.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.devin.simpletools_server.common.utils.AssertUtil;
import com.devin.simpletools_server.domain.eneity.login.Users;
import com.devin.simpletools_server.domain.eneity.login.WxUser;
import com.devin.simpletools_server.mapper.v1.login.UsersMapper;
import com.devin.simpletools_server.mapper.v1.login.WxUserMapper;
import com.devin.simpletools_server.service.v1.builder.TextBuilder;
import com.devin.simpletools_server.service.v1.builder.UserBuilder;
import com.devin.simpletools_server.service.v1.login.LoginService;
import com.devin.simpletools_server.service.v1.login.WxMsgService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 2025/2/11 20:37
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Service
public class WxMsgServiceImpl implements WxMsgService {

    @Autowired
    private WxUserMapper wxUserMapper;

    @Autowired
    private LoginService loginService;

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private HttpServletResponse response;

    @Value("${wx.mp.callback}")
    private String callback;

    private static final String URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";

    private static final ConcurrentHashMap<String, Integer> WAIT_AUTHORIZE_MAP = new ConcurrentHashMap<>();


    @Override
    public WxMpXmlOutMessage scan(WxMpXmlMessage wxMpXmlMessage) {
        // 获取用户openId ==> 唯一标识一个微信用户
        String openId = wxMpXmlMessage.getFromUser();
        // 获取事件码
        Integer code = getEventKey(wxMpXmlMessage);

        // 判断是否为新用户
        WxUser wxUser = wxUserMapper.selectOne(new LambdaQueryWrapper<WxUser>().eq(WxUser::getOpenid, openId));

        // 已注册
        boolean registered = Objects.nonNull(wxUser);
        // 已授权
        boolean authorized = registered && StrUtil.isNotBlank(wxUser.getAvatarUrl());

        if (registered && authorized) {
            return new TextBuilder().build("登录成功", wxMpXmlMessage, wxMpService);
        }

        if (!registered) {
            // 注册微信新用户
            loginService.registerWx(openId);
        }

        // 绑定等待授权登录状态
        WAIT_AUTHORIZE_MAP.put(openId, code);

        String text = "<a href=\"%s\">同意授权</a>"
            .formatted(String.format(URL,
                wxMpService.getWxMpConfigStorage().getAppId(),
                URLEncoder.encode(callback + "/wx/callback")));

        return new TextBuilder().build(text, wxMpXmlMessage, wxMpService);
    }

    @Override
    public void authorize(WxOAuth2UserInfo userInfo) {
        // 获取用户信息
        String openId = userInfo.getOpenid();
        // 更新微信用户信息
        WxUser wxUser = UserBuilder.buildWxUser(openId, userInfo);
        int update = wxUserMapper.update(wxUser, new LambdaQueryWrapper<WxUser>().eq(WxUser::getOpenid, openId));
        AssertUtil.isTrue(update == 1, "更新微信用户失败");

        // 取消绑定的微信用户验证状态
        WAIT_AUTHORIZE_MAP.remove(openId);
        // 通知用户登录成功
        sendMsg("登录成功");
    }

    /**
     * 发送信息
     *
     * @param msg
     */
    @SneakyThrows
    private void sendMsg(String msg) {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(
            """
                <html>
                    <head>
                        <meta charset="UTF-8">
                    </head>
                    <body>
                        <h1>%s</h1>
                    </body>
                </html>
                """.formatted(msg));
    }

    private Integer getEventKey(WxMpXmlMessage wxMpXmlMessage) {
        try {
            return Integer.parseInt(wxMpXmlMessage.getEventKey());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
