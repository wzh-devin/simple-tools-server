package com.devin.simpletools_server.domain.dto;

import com.devin.simpletools_server.domain.eneity.taobao.CategoryItem;
import lombok.Builder;
import lombok.Data;

/**
 * 2025/1/18 14:49
 * <p>
 *     二级类别的DTO
 * </p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Data
@Builder
public class CategoryItemDto {
    /**
     * 二级类别id
     */
    private Long categoryItemId;

    /**
     * 二级类别的所属一级类目
     */
    private Long categoryId;

    /**
     * 二级类别的所属一级类目
     */
    private String categoryName;

    /**
     * 二级类别名称
     */
    private String categoryItemName;

    /**
     * 二级类别描述
     */
    private String categoryItemDesc;

    /**
     * 二级类别是否是正常状态
     */
    private Integer isActive;
}
