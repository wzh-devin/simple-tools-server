package com.devin.simpletools_server.dao.v1.taobao;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.devin.simpletools_server.domain.eneity.taobao.Category;
import com.devin.simpletools_server.domain.vo.req.CategoryReq;
import com.devin.simpletools_server.mapper.v1.taobao.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 2025/1/17 19:32
 * <p></p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Service
public class CategoryDao extends ServiceImpl<CategoryMapper, Category> {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 获取所有的类别
     * @return
     */
    public List<Category> getAllList() {
        return lambdaQuery()
                .list();
    }

    /**
     * 根据参数获取类别
     * @param categoryReq
     * @return
     */
    public List<Category> getAllListByParams(CategoryReq categoryReq) {
        return lambdaQuery()
                .eq(!Objects.isNull(categoryReq.getIsActive()),
                        Category::getIsActive, categoryReq.getIsActive())
                .eq(!Objects.isNull(categoryReq.getCategoryId()),
                        Category::getId, categoryReq.getCategoryId())
                .eq(!StringUtils.isEmpty(categoryReq.getCategoryName()),
                        Category::getName, categoryReq.getCategoryName())
                .list();
    }
}
