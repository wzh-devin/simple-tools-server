package com.devin.simpletools_server.service.v1.taobao.builder;

import com.devin.simpletools_server.common.enums.OperateTypeEnum;
import com.devin.simpletools_server.common.utils.SnowFlake;
import com.devin.simpletools_server.domain.eneity.taobao.Category;
import com.devin.simpletools_server.domain.vo.req.CategoryReq;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

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
            category.setId(categoryReq.getCategoryId());
            category.setUpdateTime(LocalDateTime.now());
        }
        category.setName(categoryReq.getCategoryName());
        category.setDescription(categoryReq.getDescription());
        category.setIsActive(categoryReq.getIsActive());
        return category;
    }
}
