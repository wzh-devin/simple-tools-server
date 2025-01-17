package com.devin.simpletools_server.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 2025/1/17 23:13
 * <p>
 *     操作类型枚举
 * </p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public enum OperateTypeEnum {

    ADD("新增"),
    SELECT("查询"),
    UPDATE("更新"),
    DELETE("删除"),
    ;

    /**
     * 操作类型
     */
    private final String operate;
}
