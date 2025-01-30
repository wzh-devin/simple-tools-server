package com.devin.simpletools_server.common.enums;

import com.alibaba.fastjson2.JSONObject;
import com.devin.simpletools_server.common.utils.ApiResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * 2025/1/18 0:31
 * <p>
 *     Http处理错误枚举
 * </p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public enum HttpErrorEnum {

    SYS_ERROR(503, "系统异常: {0}"),
    BUSINESS_ERROR(500, "业务处理异常: {0}")
    ;

    private final Integer errCode;
    private final String errMsg;

    /**
     * 向前端发送错误信息
     * @param response
     * @throws IOException
     */
    public void sendHttpError(HttpServletResponse response, Object ...args) throws IOException {
        response.setStatus(errCode);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSONObject.toJSONString(ApiResult.fail(errCode, MessageFormat.format(errMsg, args))));
    }
}
