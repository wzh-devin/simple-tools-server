package com.devin.simpletools_server.domain.vo.req;

import lombok.Data;

/**
 * 2025/1/31 14:50
 * <p></p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Data
public class CategoryItemReq extends CategoryReq {
    /**
     * 二级类目id
     */
    private Long itemId;
}
