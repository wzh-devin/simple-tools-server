package com.devin.simpletools_server.common.exception;

import com.devin.simpletools_server.common.enums.BusinessErrorEnum;
import lombok.Data;

/**
 * 2025/1/16 22:01
 * <p>
 *     业务异常
 * </p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Data
public class BusinessException extends RuntimeException {

    private Integer errCode;

    private String errMsg;

    public BusinessException(String errMsg) {
        super(errMsg);
    }

    public BusinessException(Integer errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public BusinessException(BusinessErrorEnum errorEnum) {
        super(errorEnum.getErrMsg());
        this.errCode = errorEnum.getErrCode();
        this.errMsg = errorEnum.getErrMsg();
    }
}
