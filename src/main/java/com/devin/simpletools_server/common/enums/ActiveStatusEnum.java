package com.devin.simpletools_server.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 2025/1/16 20:46
 * <p>
 *     激活状态枚举
 * </p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public enum ActiveStatusEnum {
    NORMAL(1, "正常"),
    DISABLE(0, "停用");

    private final Integer status;
    private final String desc;
}
