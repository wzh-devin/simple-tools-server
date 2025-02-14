package com.devin.simpletools_server.common.event.listener;

import com.devin.simpletools_server.common.enums.ActiveStatusEnum;
import com.devin.simpletools_server.common.event.UserOnlineEvent;
import com.devin.simpletools_server.domain.eneity.login.AccountUser;
import com.devin.simpletools_server.domain.eneity.login.Users;
import com.devin.simpletools_server.domain.eneity.login.WxUser;
import com.devin.simpletools_server.mapper.v1.login.UsersMapper;
import com.devin.simpletools_server.service.v1.login.UserService;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
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
public class UserOnlineListener {

    @Autowired
    private UsersMapper usersMapper;

    @TransactionalEventListener(classes = UserOnlineEvent.class, phase = TransactionPhase.AFTER_COMMIT, fallbackExecution = true)
    public void updateInfo(UserOnlineEvent event) {
        log.info("更新用户信息");
        Long uid;
        if (Objects.nonNull(event.getWxUser())) {
            uid = event.getWxUser().getUserId();
        } else {
            uid = event.getAccountUser().getUserId();
        }

        Users user = Users.builder()
            .id(uid)
            .isActive(ActiveStatusEnum.NORMAL.getStatus())
            .lastLogin(LocalDateTime.now())
            .build();
        usersMapper.updateById(user);
    }
}
