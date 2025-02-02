package com.devin.simpletools_server.service.v1.taobao;

import com.devin.simpletools_server.domain.eneity.taobao.Category;
import com.devin.simpletools_server.domain.eneity.taobao.CategoryItem;
import com.devin.simpletools_server.domain.vo.req.CategoryItemReq;
import com.devin.simpletools_server.domain.vo.req.CategoryReq;

import java.util.List;

/**
 * 2025/1/17 19:30
 * <p></p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
public interface CategoryService {

    /**
     * 获取所有的类别
     * @return
     */
    List<Category> getAllList();

    /**
     * 根据参数，获取对应的类别
     * @param categoryReq
     * @return
     */
    List<Category> getListByParams(CategoryReq categoryReq);

    /**
     * 新增类别
     * @param categoryReq
     */
    void addCategory(CategoryReq categoryReq);

    /**
     * 修改一级类目
     * @param categoryReq
     */
    void editCategory(CategoryReq categoryReq);

    /**
     * 删除一级类目
     * @param id
     */
    void deleteCategory(Long id);

    /**
     * 查询所有的二级类目
     * @return
     */
    List<CategoryItem> getCategoryItems();

    /**
     * 根据id，插叙二级类目
     * @param id
     * @return
     */
    List<CategoryItem> getCategoryItemsId(Long id);

    /**
     * 新增二级类目
     * @param categoryItemReq
     */
    void addCategoryItem(CategoryItemReq categoryItemReq);

    /**
     * 修改二级类目
     * @param categoryItemReq
     */
    void editCategoryItem(CategoryItemReq categoryItemReq);

    /**
     * 根据id，删除二级类目
     * @param id
     */
    void deleteCategoryItem(Long id);
}
