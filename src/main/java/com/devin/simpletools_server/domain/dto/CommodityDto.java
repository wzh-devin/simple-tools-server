package com.devin.simpletools_server.domain.dto;

import com.devin.simpletools_server.domain.eneity.taobao.CategoryItem;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Builder;
import lombok.Data;

/**
 * 2025/1/18 14:49
 * <p>
 *     商品信息的DTO
 * </p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Data
@Builder
public class CommodityDto {
    /**
     * 商品id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long commodityId;

    /**
     * 商品名称
     */
    private String commodityName;

    /**
     * 商品描述
     */
    private String commodityDesc;

    /**
     * 商品是否是正常状态
     */
    private Integer isActive;

    /**
     * 商品的所属二级类别
     */
    private CategoryItemDto categoryItemDto;
}
