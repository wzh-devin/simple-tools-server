package com.devin.simpletools_server.domain.vo.req;

import lombok.Data;

/**
 * 2025/1/17 20:48
 * <p></p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Data
public class CategoryReq {
    /**
     * 类别id
     */
    private Long id;

    /**
     * 类别名
     */
    private String name;

    /**
     * 类别描述
     */
    private String description;

    /**
     * 是否激活
     */
    private Integer isActive;
}
