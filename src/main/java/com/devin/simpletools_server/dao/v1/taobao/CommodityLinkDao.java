package com.devin.simpletools_server.dao.v1.taobao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.devin.simpletools_server.common.enums.ActiveStatusEnum;
import com.devin.simpletools_server.domain.eneity.taobao.Commodity;
import com.devin.simpletools_server.domain.eneity.taobao.CommodityLink;
import com.devin.simpletools_server.mapper.v1.taobao.CommodityLinkMapper;
import com.devin.simpletools_server.mapper.v1.taobao.CommodityMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2025/1/17 19:15
 * <p></p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Service
public class CommodityLinkDao extends ServiceImpl<CommodityLinkMapper, CommodityLink> {

    /**
     * 根据商品id查询链接
     * @param commodityId
     * @return
     */
    public List<CommodityLink> selectByCommodityId(Long commodityId) {
        return lambdaQuery()
                .eq(CommodityLink::getIsActive, ActiveStatusEnum.NORMAL.getStatus())
                .eq(CommodityLink::getCommodityId, commodityId)
                .list();
    }
}
