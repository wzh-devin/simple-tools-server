package com.devin.simpletools_server.controller.v1;

import com.devin.simpletools_server.common.annocation.ApiV1;
import com.devin.simpletools_server.common.utils.ApiResult;
import com.devin.simpletools_server.domain.eneity.taobao.Category;
import com.devin.simpletools_server.domain.eneity.taobao.CategoryItem;
import com.devin.simpletools_server.domain.vo.req.CategoryReq;
import com.devin.simpletools_server.service.v1.taobao.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 2025/1/17 19:29
 * <p>
 *     类别控制器
 * </p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@ApiV1
@RestController
@RequestMapping("/taobao/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * 获取所有的类别
     * @return
     */
    @GetMapping("/getAll")
    public ApiResult<List<Category>> getAllList() {
        return ApiResult.success(categoryService.getAllList());
    }

    /**
     * 根据参数获取类别
     * @param categoryReq
     * @return
     */
    @GetMapping("/getListByParams")
    public ApiResult<List<Category>> getListByParams(@RequestBody CategoryReq categoryReq) {
        return ApiResult.success(categoryService.getListByParams(categoryReq));
    }

    /**
     * 新增一级类别
     * @param categoryReq
     * @return
     */
    @PostMapping("/addCategory")
    public ApiResult<Void> addCategory(@RequestBody CategoryReq categoryReq) {
        categoryService.addCategory(categoryReq);
        return ApiResult.success();
    }

    /**
     * 修改一级类目
     * @param categoryReq
     * @return
     */
    @PostMapping("/editCategory")
    public ApiResult<Void> editCategory(@RequestBody CategoryReq categoryReq) {
        categoryService.editCategory(categoryReq);
        return ApiResult.success();
    }

    /**
     * 删除一级类目
     * @return
     */
    @PostMapping("/deleteCategory")
    public ApiResult<Void> deleteCategory(@RequestParam("id") Long id) {
        categoryService.deleteCategory(id);
        return ApiResult.success();
    }

    /**
     * 根据id获取二级类目
     * @return
     */
    @GetMapping("/getCategoryItems")
    public ApiResult<List<CategoryItem>> getCategoryItem(@RequestParam("id") Long id) {
        return ApiResult.success(categoryService.getCategoryItems(id));
    }

    /**
     * 新增二级类目
     * @return
     */
    @PostMapping("/addCategoryItem")
    public ApiResult<Void> addCategoryItem() {
        return ApiResult.success();
    }

    /**
     * 修改二级类目
     * @return
     */
    @PostMapping("/editCategoryItem")
    public ApiResult<Void> editCategoryItem() {
        return ApiResult.success();
    }

    /**
     * 删除二级类目
     * @return
     */
    @PostMapping("/deleteCategoryItem")
    public ApiResult<Void> deleteCategoryItem() {
        return ApiResult.success();
    }
}
