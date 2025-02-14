package com.devin.simpletools_server.common.utils;

import com.devin.simpletools_server.common.enums.RespTypeEnum;
import lombok.Data;

/**
 * 2025/1/16 18:30
 * <p>
 *     统一返回接口类型
 * </p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Data
public class ApiResult<T> {
    private Boolean success;
    private Integer errCode;
    private String errMsg;
    private Object type;
    private T data;

    public static <T> ApiResult<T> success() {
        ApiResult<T> result = new ApiResult<>();
        result.setSuccess(Boolean.TRUE);
        result.setData(null);
        return result;
    }

    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> result = new ApiResult<>();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static <T> ApiResult<T> fail(Integer errCode, String errMsg) {
        ApiResult<T> result = new ApiResult<>();
        result.setSuccess(false);
        result.setErrCode(errCode);
        result.setErrMsg(errMsg);
        return result;
    }
}
