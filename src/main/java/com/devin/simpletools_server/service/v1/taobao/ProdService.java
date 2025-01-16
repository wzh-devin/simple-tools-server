package com.devin.simpletools_server.service.v1.taobao;

import com.devin.simpletools_server.domain.dto.ProdDto;
import com.devin.simpletools_server.domain.vo.resp.ProdResp;

import java.util.List;

/**
 * 2025/1/16 18:17
 * <p></p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
public interface ProdService {

    /**
     * 获取所有商品的详细信息
     * @return
     */
    List<ProdDto> getAllDetailInfo(Integer isActive);
}
