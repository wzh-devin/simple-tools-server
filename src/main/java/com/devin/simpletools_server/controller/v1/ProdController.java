package com.devin.simpletools_server.controller.v1;

import com.devin.simpletools_server.common.utils.ApiResult;
import com.devin.simpletools_server.service.v1.taobao.ProdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/taobao/product")
public class ProdController {

    private final ProdService productService;

    @GetMapping("/getAllInfo")
    public ApiResult<Void> getList() {
        return ApiResult.success();
    }
}
