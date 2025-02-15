package com.devin.simpletools_server.common.event;

import com.devin.simpletools_server.domain.eneity.login.AccountUser;
import com.devin.simpletools_server.domain.eneity.login.WxUser;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 2025/2/15 20:27
 * <p>
 *     用户下线事件
 * </p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Getter
public class UserOfflineEvent extends ApplicationEvent {
    private final Long uid;

    public UserOfflineEvent(Object source, Long uid) {
        super(source);
        this.uid = uid;
    }
}
