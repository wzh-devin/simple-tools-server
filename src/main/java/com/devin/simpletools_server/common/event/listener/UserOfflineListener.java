package com.devin.simpletools_server.common.event.listener;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.devin.simpletools_server.common.constant.RedisKey;
import com.devin.simpletools_server.common.enums.ActiveStatusEnum;
import com.devin.simpletools_server.common.event.UserOfflineEvent;
import com.devin.simpletools_server.common.event.UserOnlineEvent;
import com.devin.simpletools_server.common.utils.AssertUtil;
import com.devin.simpletools_server.common.utils.RedisUtil;
import com.devin.simpletools_server.domain.eneity.login.AccountUser;
import com.devin.simpletools_server.domain.eneity.login.Users;
import com.devin.simpletools_server.domain.eneity.login.WxUser;
import com.devin.simpletools_server.mapper.v1.login.AccountUserMapper;
import com.devin.simpletools_server.mapper.v1.login.UsersMapper;
import com.devin.simpletools_server.mapper.v1.login.WxUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 2025/2/14 16:02
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Component
public class UserOfflineListener {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private AccountUserMapper accountUserMapper;

    @Autowired
    private WxUserMapper wxUserMapper;

    @TransactionalEventListener(classes = UserOfflineEvent.class, phase = TransactionPhase.AFTER_COMMIT, fallbackExecution = true)
    public void remove(UserOfflineEvent event) {
        log.info("用户信息删除");
        // 获取用户
        Users user = usersMapper.selectById(event.getUid());
        if (Objects.isNull(user)) return;
        // 删除Redis数据即可
        RedisUtil.del(RedisKey.generateKey(RedisKey.USER_ONLINE_KEY, user.getId()));
    }
}
