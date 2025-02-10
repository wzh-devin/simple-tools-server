package com.devin.simpletools_server.mapper.v1.login;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.devin.simpletools_server.domain.eneity.login.AccountUser;
import com.devin.simpletools_server.domain.vo.req.LoginReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 2025/2/10 22:29
 * <p></p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Mapper
public interface AccountUserMapper extends BaseMapper<AccountUser> {
    AccountUser getAccountUser(@Param("loginReq") LoginReq loginReq);
}
