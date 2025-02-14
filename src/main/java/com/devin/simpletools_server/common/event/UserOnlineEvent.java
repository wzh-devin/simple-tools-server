package com.devin.simpletools_server.common.event;

import com.devin.simpletools_server.domain.eneity.login.AccountUser;
import com.devin.simpletools_server.domain.eneity.login.Users;
import com.devin.simpletools_server.domain.eneity.login.WxUser;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 2025/2/14 15:54
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Getter
public class UserOnlineEvent extends ApplicationEvent {

    private WxUser wxUser;

    private AccountUser accountUser;

    public UserOnlineEvent(Object source, WxUser wxUser) {
        super(source);
        this.wxUser = wxUser;
    }

    public UserOnlineEvent(Object source, AccountUser accountUser) {
        super(source);
        this.accountUser = accountUser;
    }
}
