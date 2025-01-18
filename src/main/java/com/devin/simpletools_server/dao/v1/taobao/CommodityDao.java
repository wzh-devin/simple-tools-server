package com.devin.simpletools_server.dao.v1.taobao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.devin.simpletools_server.domain.eneity.taobao.Commodity;
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
public class CommodityDao extends ServiceImpl<CommodityMapper, Commodity> {

    /**
     * 查询所有的商品信息
     * @return
     */
    public List<Commodity> selectAllInfo() {
        return lambdaQuery()
                .list();
    }
}
