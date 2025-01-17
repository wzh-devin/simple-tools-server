package com.devin.simpletools_server.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 2025/1/16 22:02
 * <p>
 *     业务异常枚举
 * </p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public enum BusinessErrorEnum {

    SYSTEM_ERROR(-1, "系统异常"),
    BUSINESS_ERROR(1001, "业务异常===>{0}"),
    ;

    private final Integer errCode;
    private final String errMsg;
}
