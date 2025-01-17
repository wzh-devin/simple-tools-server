package com.devin.simpletools_server.common.enums;

import com.devin.simpletools_server.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 2025/1/16 22:07
 * <p>
 *     全局异常处理器
 * </p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常处理器
     * @param e
     */
    @ExceptionHandler(BusinessException.class)
    public void globalExceptionHandler(BusinessException e) {
        log.error("业务异常: {}", e.getMessage());
        // TODO：后续返回给前端
    }

    /**
     * sql唯一性约束异常
     * @param e
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public void SQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        if (e.getMessage().startsWith("Duplicate entry")) {
            log.error("唯一性约束异常: {} 已经存在", e.getMessage().split(" ")[2]);
            // TODO: 返回给前端
        }
    }
}
