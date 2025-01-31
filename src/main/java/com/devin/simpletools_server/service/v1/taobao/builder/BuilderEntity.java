package com.devin.simpletools_server.service.v1.taobao.builder;

import com.devin.simpletools_server.common.enums.OperateTypeEnum;
import com.devin.simpletools_server.common.utils.SnowFlake;
import com.devin.simpletools_server.domain.dto.CategoryItemDto;
import com.devin.simpletools_server.domain.dto.CommodityDto;
import com.devin.simpletools_server.domain.eneity.taobao.Category;
import com.devin.simpletools_server.domain.eneity.taobao.CategoryItem;
import com.devin.simpletools_server.domain.eneity.taobao.Commodity;
import com.devin.simpletools_server.domain.vo.req.CategoryItemReq;
import com.devin.simpletools_server.domain.vo.req.CategoryReq;
import com.devin.simpletools_server.domain.vo.req.CommodityReq;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.Function;

/**
 * 2025/1/17 22:53
 * <p>
 *     实体构建类
 * </p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
public class BuilderEntity {
    public static Category buildCategory(CategoryReq categoryReq, OperateTypeEnum operate) {
        Category category = new Category();
        if (OperateTypeEnum.ADD.equals(operate)) {
            category.setId(SnowFlake.nextId());
            category.fillTime();
        } else {
            category.setId(categoryReq.getId());
            category.setUpdateTime(LocalDateTime.now());
        }
        category.setName(categoryReq.getName());
        category.setDescription(categoryReq.getDescription());
        category.setIsActive(categoryReq.getIsActive());
        return category;
    }

    /**
     * 构建商品的dto
     * @param commodity
     * @return
     */
    public static CommodityDto buildCommodityDto(Commodity commodity) {
        return CommodityDto.builder()
                .commodityId(commodity.getId())
                .commodityName(commodity.getName())
                .commodityDesc(commodity.getDescription())
                .categoryItemDto(CategoryItemDto.builder().categoryItemId(commodity.getCategoryItemId()).build())
                .isActive(commodity.getIsActive())
                .build();
    }

    /**
     * 构建二级类目的dto
     * @param categoryItem
     * @param category
     * @return
     */
    public static CategoryItemDto buildCategoryItemDto(CategoryItem categoryItem, Category category) {
        return CategoryItemDto.builder()
                .categoryItemId(categoryItem.getId())
                .categoryItemName(categoryItem.getName())
                .categoryItemDesc(categoryItem.getDescription())
                .isActive(categoryItem.getIsActive())
                .categoryId(category.getId())
                .categoryName(category.getName())
                .build();
    }

    /**
     * 构建商品实体类
     * @param commodityReq
     * @return
     */
    public static Commodity buildCommodity(CommodityReq commodityReq, OperateTypeEnum operate) {
        Commodity commodity = new Commodity();
        if (OperateTypeEnum.ADD.equals(operate)) {
            commodity.setId(SnowFlake.nextId());
            commodity.fillTime();
        } else {
            commodity.setId(commodityReq.getCommodityId());
            commodity.setUpdateTime(LocalDateTime.now());
        }
        commodity.setCategoryItemId(commodityReq.getCategoryItemId());
        commodity.setName(commodityReq.getCommodityName());
        commodity.setDescription(commodityReq.getCommodityDesc());
        commodity.setIsActive(commodityReq.getIsActive());
        commodity.setCategoryItemId(commodityReq.getCategoryItemId());
        return commodity;
    }

    /**
     * 构建二级类目实体
     * @param categoryItemReq
     * @param operate
     * @return
     */
    public static CategoryItem buildCategoryItem(CategoryItemReq categoryItemReq, OperateTypeEnum operate) {
        CategoryItem categoryItem = new CategoryItem();
        if (OperateTypeEnum.ADD.equals(operate)) {
            categoryItem.setId(SnowFlake.nextId());
            categoryItem.fillTime();
        } else {
            categoryItem.setId(categoryItemReq.getId());
            categoryItem.setUpdateTime(LocalDateTime.now());
        }
        categoryItem.setName(categoryItemReq.getName());
        categoryItem.setDescription(categoryItemReq.getDescription());
        categoryItem.setIsActive(Objects.isNull(categoryItemReq.getIsActive()) ? 1 : categoryItemReq.getIsActive());
        categoryItem.setCategoryId(categoryItemReq.getCategoryId());
        return categoryItem;

    }
}
