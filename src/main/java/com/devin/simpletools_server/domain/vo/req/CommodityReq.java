package com.devin.simpletools_server.domain.vo.req;

import lombok.Data;

/**
 * 2025/1/18 14:35
 * <p>
 *    商品的请求对象封装
 * </p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Data
public class CommodityReq {
    /**
     * 商品id
     */
    private Long commodityId;

    /**
     * 商品所属二级类别id
     */
    private Long categoryItemId;

    /**
     * 商品名
     */
    private String commodityName;

    /**
     * 商品描述
     */
    private String commodityDesc;

    /**
     * 商品是否在激活状态
     */
    private Integer isActive;
}
