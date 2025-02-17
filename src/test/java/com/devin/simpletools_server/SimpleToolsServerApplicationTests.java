package com.devin.simpletools_server;

import com.alibaba.fastjson2.JSONObject;
import com.devin.simpletools_server.common.utils.RedisUtil;
import com.devin.simpletools_server.domain.eneity.login.Users;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.LocalDateTime;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SimpleToolsServerApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    public void removeRedis() {
        redisTemplate.delete("test");
    }

    /**
     * 测试redis
     */
    @Test
    public void testRedis() {
//        redisTemplate.opsForValue().set("test", "test");
        Users user = Users.builder()
            .id(1L)
            .isActive(1)
            .createTime(LocalDateTime.now())
            .updateTime(LocalDateTime.now())
            .lastLogin(LocalDateTime.now())
            .build();
        redisTemplate.opsForValue().set("user", JSONObject.toJSONString(user));

        log.info("{}", JSONObject.parseObject(redisTemplate.opsForValue().get("user").toString(), Users.class));
    }

    @Test
    public void testRedisUtil() {
        Users user = Users.builder()
            .id(1L)
            .isActive(1)
            .createTime(LocalDateTime.now())
            .updateTime(LocalDateTime.now())
            .lastLogin(LocalDateTime.now())
            .build();
        RedisUtil.set("user", user);
        log.info("{}", RedisUtil.get("user", Users.class));
    }

}
