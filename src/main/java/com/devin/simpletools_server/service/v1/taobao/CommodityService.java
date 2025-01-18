package com.devin.simpletools_server.service.v1.taobao;

import com.devin.simpletools_server.domain.dto.CommodityDto;
import com.devin.simpletools_server.domain.eneity.taobao.Commodity;
import com.devin.simpletools_server.domain.eneity.taobao.CommodityLink;
import com.devin.simpletools_server.domain.vo.req.CommodityReq;

import java.util.List;

/**
 * 2025/1/17 19:14
 * <p></p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
public interface CommodityService {

    /**
     * 新增商品
     * @param commodityReq
     */
    void addCommodity(CommodityReq commodityReq);

    /**
     * 获取所有商品的信息
     * @return
     */
    List<CommodityDto> getAllInfo();

    /**
     * 获取链接信息
     * @param commodityId
     * @return
     */
    List<CommodityLink> getLinks(Long commodityId);

    /**
     * 修改商品信息
     * @param commodityReq
     */
    void editCommodity(CommodityReq commodityReq);

    /**
     * 删除商品信息
     * @param commodityId
     */
    void deleteCommodity(Long commodityId);
}
