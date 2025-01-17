package com.devin.simpletools_server.common.exception;

import com.devin.simpletools_server.common.enums.HttpErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @Autowired
    private HttpServletResponse response;

    /**
     * 业务异常处理器
     * @param e
     */
    @ExceptionHandler(BusinessException.class)
    public void globalExceptionHandler(BusinessException e) throws IOException {
        log.error("业务异常: {}", e.getMessage());
        HttpErrorEnum.BUSINESS_ERROR.sendHttpError(response, e.getMessage());
    }

    /**
     * sql唯一性约束异常
     * @param e
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public void SQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) throws IOException {
        if (e.getMessage().startsWith("Duplicate entry")) {
            String name = e.getMessage().split(" ")[2].concat("已经存在");
            log.error("唯一性约束异常: {}", name);
            HttpErrorEnum.BUSINESS_ERROR.sendHttpError(response, name);
        }
    }
}
