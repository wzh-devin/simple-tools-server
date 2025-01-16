package com.devin.simpletools_server.controller.v1;

import com.devin.simpletools_server.common.utils.ApiResult;
import com.devin.simpletools_server.domain.dto.ProdDto;
import com.devin.simpletools_server.domain.vo.resp.ProdResp;
import com.devin.simpletools_server.service.v1.taobao.ProdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 2025/1/16 18:15
 * <p></p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/taobao/prod")
public class ProdController {

    private final ProdService productService;

    /**
     * 获取商品所有详细信息
     * @return
     */
    @GetMapping("/getAllDetailInfo")
    public ApiResult<List<ProdDto>> getAllDetailInfo(@RequestParam("isActive") Integer isActive) {
        return ApiResult.success(productService.getAllDetailInfo(isActive));
    }
}
