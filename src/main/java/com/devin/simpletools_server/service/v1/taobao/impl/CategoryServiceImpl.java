package com.devin.simpletools_server.service.v1.taobao.impl;

import com.devin.simpletools_server.common.enums.OperateTypeEnum;
import com.devin.simpletools_server.common.utils.AssertUtil;
import com.devin.simpletools_server.dao.v1.taobao.CategoryDao;
import com.devin.simpletools_server.dao.v1.taobao.CategoryItemDao;
import com.devin.simpletools_server.domain.eneity.taobao.Category;
import com.devin.simpletools_server.domain.eneity.taobao.CategoryItem;
import com.devin.simpletools_server.domain.vo.req.CategoryItemReq;
import com.devin.simpletools_server.domain.vo.req.CategoryReq;
import com.devin.simpletools_server.service.v1.taobao.CategoryService;
import com.devin.simpletools_server.service.v1.taobao.builder.BuilderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 2025/1/17 19:30
 * <p></p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    private final CategoryItemDao categoryItemDao;

    @Override
    public List<Category> getAllList() {
        return categoryDao.getAllList();
    }

    @Override
    public List<Category> getListByParams(CategoryReq categoryReq) {
        return categoryDao.getAllListByParams(categoryReq);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addCategory(CategoryReq categoryReq) {
        Category category = BuilderEntity.buildCategory(categoryReq, OperateTypeEnum.ADD);
        boolean saveResult = categoryDao.save(category);

        // TODO 后续优化传入的信息，进行统一管理
        AssertUtil.isTrue(saveResult, "新增类目失败");
    }

    @Override
    public void editCategory(CategoryReq categoryReq) {
        Category category = BuilderEntity.buildCategory(categoryReq, OperateTypeEnum.UPDATE);
        // TODO 由于涉及的数量级较小，所以这里采用直接更新的方式
        boolean editResult = categoryDao.updateById(category);
        AssertUtil.isTrue(editResult, "类目修改失败");
    }

    @Override
    public void deleteCategory(Long id) {
        // 判断是否存在子类别
        List<CategoryItem> categoryItemList = categoryItemDao.getItemsByCategory(id);
        AssertUtil.isEmpty(categoryItemList, "类目删除失败，该类目存在子类别");

        // 删除一级类目
        boolean delResult = categoryDao.removeById(id);
        AssertUtil.isTrue(delResult, "类目删除失败");
    }

    @Override
    public List<CategoryItem> getCategoryItems() {
        return categoryItemDao.list();
    }

    @Override
    public List<CategoryItem> getCategoryItemsId(Long id) {
        return categoryItemDao.getItemsByCategory(id);
    }

    @Override
    public void addCategoryItem(CategoryItemReq categoryItemReq) {
        // 构建二级类目实体
        CategoryItem categoryItem = BuilderEntity.buildCategoryItem(categoryItemReq, OperateTypeEnum.ADD);
        boolean saveResult = categoryItemDao.save(categoryItem);
        AssertUtil.isTrue(saveResult, "新增二级类目失败");
    }

    @Override
    public void editCategoryItem(CategoryItemReq categoryItemReq) {
        CategoryItem categoryItem = BuilderEntity.buildCategoryItem(categoryItemReq, OperateTypeEnum.UPDATE);
        boolean updateResult = categoryItemDao.updateById(categoryItem);
        AssertUtil.isTrue(updateResult, "修改二级类目失败");
    }

    @Override
    public void deleteCategoryItem(Long id) {
        boolean delResult = categoryItemDao.removeById(id);
        AssertUtil.isTrue(delResult, "删除二级类目失败");
    }
}
