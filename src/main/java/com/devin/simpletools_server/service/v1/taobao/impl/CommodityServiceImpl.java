package com.devin.simpletools_server.service.v1.taobao.impl;

import com.devin.simpletools_server.common.enums.OperateTypeEnum;
import com.devin.simpletools_server.common.utils.AssertUtil;
import com.devin.simpletools_server.dao.v1.taobao.CategoryDao;
import com.devin.simpletools_server.dao.v1.taobao.CategoryItemDao;
import com.devin.simpletools_server.dao.v1.taobao.CommodityDao;
import com.devin.simpletools_server.dao.v1.taobao.CommodityLinkDao;
import com.devin.simpletools_server.domain.dto.CommodityDto;
import com.devin.simpletools_server.domain.eneity.taobao.Category;
import com.devin.simpletools_server.domain.eneity.taobao.CategoryItem;
import com.devin.simpletools_server.domain.eneity.taobao.Commodity;
import com.devin.simpletools_server.domain.eneity.taobao.CommodityLink;
import com.devin.simpletools_server.domain.vo.req.CommodityReq;
import com.devin.simpletools_server.service.v1.taobao.CommodityService;
import com.devin.simpletools_server.service.v1.taobao.builder.BuilderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 2025/1/17 19:14
 * <p></p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class CommodityServiceImpl implements CommodityService {

    private final CommodityDao commodityDao;

    private final CommodityLinkDao commodityLinkDao;

    private final CategoryItemDao categoryItemDao;

    private final CategoryDao categoryDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addCommodity(CommodityReq commodityReq) {
        Commodity commodity = BuilderEntity.buildCommodity(commodityReq, OperateTypeEnum.ADD);
        boolean saveResult = commodityDao.save(commodity);
        AssertUtil.isTrue(saveResult, "商品信息添加失败");
    }

    @Override
    public List<CommodityDto> getAllInfo() {
        List<Commodity> commodityList = commodityDao.selectAllInfo();

        // 实体列表 ==> DTO
        List<CommodityDto> commodityDtoList = commodityList.stream()
                .map(BuilderEntity::buildCommodityDto)
                .toList();

        for (CommodityDto commodityDto : commodityDtoList) {
            // 查询二级类别
            CategoryItem categoryItem = categoryItemDao.getById(commodityDto.getCategoryItemDto().getCategoryItemId());
            // 查询一级类目
            Category category = categoryDao.getById(categoryItem.getCategoryId());
            // 构建二级类目的DTO
            commodityDto.setCategoryItemDto(BuilderEntity.buildCategoryItemDto(categoryItem, category));
        }

        return commodityDtoList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void editCommodity(CommodityReq commodityReq) {
        Commodity commodity = BuilderEntity.buildCommodity(commodityReq, OperateTypeEnum.UPDATE);
        boolean updateResult = commodityDao.updateById(commodity);
        AssertUtil.isTrue(updateResult, "商品信息修改失败");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteCommodity(Long commodityId) {
        // 查询是否含有链接
        List<CommodityLink> commodityLinks = commodityLinkDao.selectByCommodityId(commodityId);
        AssertUtil.isEmpty(commodityLinks, "该商品存在链接，请先删除链接");

        // 删除商品
        boolean delResult = commodityDao.removeById(commodityId);
        AssertUtil.isTrue(delResult, "商品信息删除失败");
    }

    @Override
    public List<CommodityLink> getLinks(Long commodityId) {
        return commodityLinkDao.selectByCommodityId(commodityId).stream()
                .peek(commodityLink -> commodityLink.setOperSys(commodityLink.getOperSys().toLowerCase())).toList();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addLink(CommodityLink commodityLink) {
        boolean saveResult = commodityLinkDao.save(commodityLink);
        AssertUtil.isTrue(saveResult, "新增链接失败");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void editLink(CommodityLink commodityLink) {
        boolean updateResult = commodityLinkDao.updateById(commodityLink);
        AssertUtil.isTrue(updateResult, "修改链接失败");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void batchDeleteLink(List<Long> linkIds) {
        boolean deleteLinks = commodityLinkDao.removeBatchByIds(linkIds);
        AssertUtil.isTrue(deleteLinks, "批量删除链接失败");
    }
}
