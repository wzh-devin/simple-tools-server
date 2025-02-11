package com.devin.simpletools_server.service.v1.login.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.devin.simpletools_server.common.utils.AssertUtil;
import com.devin.simpletools_server.domain.eneity.login.Users;
import com.devin.simpletools_server.domain.eneity.login.WxUser;
import com.devin.simpletools_server.mapper.v1.login.UsersMapper;
import com.devin.simpletools_server.mapper.v1.login.WxUserMapper;
import com.devin.simpletools_server.service.v1.builder.TextBuilder;
import com.devin.simpletools_server.service.v1.builder.UserBuilder;
import com.devin.simpletools_server.service.v1.login.WxMsgService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

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
@AllArgsConstructor
public class WxMsgServiceImpl implements WxMsgService {

    private final WxUserMapper wxUserMapper;
    private final UsersMapper usersMapper;
    private final WxMpService wxMpService;

    private static final ConcurrentHashMap<String, Integer> AUTHORIZE_MAP = new ConcurrentHashMap<>();


    @Override
    public WxMpXmlOutMessage scan(WxMpXmlMessage wxMpXmlMessage) {
        // 获取用户openId ==> 唯一标识一个微信用户
        String openId = wxMpXmlMessage.getFromUser();
        // 获取事件码
        Integer code = getEventKey(wxMpXmlMessage);

        if (Objects.isNull(code)) {
            return null;
        }

        // 判断是否为新用户
        WxUser wxUser = wxUserMapper.selectOne(new LambdaQueryWrapper<WxUser>().eq(WxUser::getOpenid, openId));

        // 已注册
        boolean registered = Objects.nonNull(wxUser);
        // 已授权
        boolean authorized = registered && StrUtil.isNotBlank(wxUser.getAvatarUrl());

        if (registered && authorized) {
            // TODO 推送登录成功消息
            return null;
        }

        if (!registered) {
            // 注册用户
            Users user = UserBuilder.buildBaseUsers();
            int userInsert = usersMapper.insert(user);
            AssertUtil.isTrue(userInsert == 1, "微信用户注册失败");
            WxUser wx = UserBuilder.buildWxUser(openId, user);
            int wxInsert = wxUserMapper.insert(wx);
            AssertUtil.isTrue(wxInsert == 1, "微信用户注册失败");
        }

        // 绑定登录状态
//        AUTHORIZE_MAP.put(openId, code);

        String text = """
            请进行登录：<a href="%s">登录</a>
            """.formatted("https://devin.wang");

        return new TextBuilder().build(text, wxMpXmlMessage, wxMpService);
    }

    private Integer getEventKey(WxMpXmlMessage wxMpXmlMessage) {
        try {
            return Integer.parseInt(wxMpXmlMessage.getEventKey());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
