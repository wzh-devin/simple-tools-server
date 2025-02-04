package com.devin.simpletools_server.controller.v1;

import com.devin.simpletools_server.common.annocation.ApiV1;
import com.devin.simpletools_server.common.utils.ApiResult;
import com.devin.simpletools_server.domain.dto.CommodityDto;
import com.devin.simpletools_server.domain.eneity.taobao.CommodityLink;
import com.devin.simpletools_server.domain.vo.req.CommodityReq;
import com.devin.simpletools_server.service.v1.taobao.CommodityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 2025/1/17 18:54
 * <p>
 *     商品服务控制器
 * </p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@ApiV1
@RestController
@RequestMapping("/taobao/commodity")
@RequiredArgsConstructor
public class CommodityController {

    private final CommodityService commodityService;

    /**
     * 新增商品信息
     * @param commodityReq
     * @return
     */
    @PostMapping("/addCommodity")
    public ApiResult<Void> addCommodity(@RequestBody CommodityReq commodityReq) {
        commodityService.addCommodity(commodityReq);
        return ApiResult.success();
    }

    /**
     * 修改商品信息
     * @param commodityReq
     * @return
     */
    @PostMapping("/editCommodity")
    public ApiResult<Void> editCommodity(@RequestBody CommodityReq commodityReq) {
        commodityService.editCommodity(commodityReq);
        return ApiResult.success();
    }

    /**
     * 获取所有商品的信息
     * @return
     */
    @GetMapping("/getAllInfo")
    public ApiResult<List<CommodityDto>> getAllInfo() {
        return ApiResult.success(commodityService.getAllInfo());
    }

    /**
     * 删除商品信息
     * @param commodityId
     * @return
     */
    @PostMapping("/deleteCommodity")
    public ApiResult<Void> deleteCommodity(@RequestParam("commodityId") Long commodityId) {
        commodityService.deleteCommodity(commodityId);
        return ApiResult.success();
    }

    /**
     * 获取商品的链接
     * @param commodityId
     * @return
     */
    @GetMapping("/getLinks")
    public ApiResult<List<CommodityLink>> getLinks(@RequestParam("commodityId") Long commodityId) {
        return ApiResult.success(commodityService.getLinks(commodityId));
    }

    /**
     * 添加商品链接
     * @param commodityLink
     * @return
     */
    @PostMapping("/addLink")
    public ApiResult<Void> addLink(@RequestBody CommodityLink commodityLink) {
        commodityService.addLink(commodityLink);
        return ApiResult.success();
    }

    /**
     * 修改商品链接
     * @param commodityLink
     * @return
     */
    @PostMapping("/editLink")
    public ApiResult<Void> editLinks(@RequestBody CommodityLink commodityLink) {
        commodityService.editLink(commodityLink);
        return ApiResult.success();
    }

    /**
     * 批量删除链接
     * @param linkIds
     * @return
     */
    @PostMapping("/deleteLinks")
    public ApiResult<Void> deleteLinks(@RequestBody List<Long> linkIds) {
        commodityService.batchDeleteLink(linkIds);
        return ApiResult.success();
    }
}
