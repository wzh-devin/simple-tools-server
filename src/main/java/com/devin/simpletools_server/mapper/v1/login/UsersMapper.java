package com.devin.simpletools_server.mapper.v1.login;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.devin.simpletools_server.domain.eneity.login.Users;
import org.apache.ibatis.annotations.Mapper;

/**
 * 2025/2/10 22:27
 * <p></p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Mapper
public interface UsersMapper extends BaseMapper<Users> {
}
