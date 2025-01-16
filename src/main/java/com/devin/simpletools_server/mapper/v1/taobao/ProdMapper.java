package com.devin.simpletools_server.mapper.v1.taobao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.devin.simpletools_server.domain.dto.ProdDto;
import com.devin.simpletools_server.domain.eneity.taobao.Prod;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 2025/1/16 18:19
 * <p></p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Mapper
public interface ProdMapper extends BaseMapper<Prod> {

    /**
     * 查询所有正常的商品信息
     * @return
     */
    List<ProdDto> selectAllProd(Integer isActive);
}
