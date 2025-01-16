package com.devin.simpletools_server.service.v1.taobao.impl;

import com.devin.simpletools_server.dao.v1.taobao.ProdDao;
import com.devin.simpletools_server.domain.dto.ProdDto;
import com.devin.simpletools_server.domain.eneity.taobao.Prod;
import com.devin.simpletools_server.domain.vo.resp.ProdResp;
import com.devin.simpletools_server.service.v1.taobao.ProdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2025/1/16 18:18
 * <p></p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProdServiceImpl implements ProdService {

    private final ProdDao prodDao;

    @Override
    public List<ProdDto> getAllDetailInfo(Integer isActive) {
        return prodDao.getAllProdList(isActive);
    }
}
