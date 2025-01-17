package com.devin.simpletools_server.common.annocation;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;

import java.lang.annotation.*;

/**
 * 2025/1/17 18:58
 * <p>
 *     版本控制注解
 * </p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Mapper
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiV1 {
}
