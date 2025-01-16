package com.devin.simpletools_server.dao.v1.taobao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.devin.simpletools_server.common.enums.ActiveStatusEnum;
import com.devin.simpletools_server.domain.dto.ProdDto;
import com.devin.simpletools_server.domain.eneity.taobao.Prod;
import com.devin.simpletools_server.mapper.v1.taobao.ProdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2025/1/16 18:22
 * <p></p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Service
public class ProdDao extends ServiceImpl<ProdMapper, Prod> {

    @Autowired
    private ProdMapper prodMapper;

    /**
     * 获取所有商品的详细信息
     */
    public List<ProdDto> getAllProdList(Integer isActive) {
        return prodMapper.selectAllProd(isActive);
    }
}
