package com.devin.simpletools_server.v1.taobao.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 2025/1/16 18:40
 * <p></p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@SpringBootTest
public class ProductTest {

    @Autowired
    private ProdMapper productMapper;

    @Test
    void testProduct() {
        Prod product = productMapper.selectOne(new LambdaQueryWrapper<Prod>().eq(Prod::getId, 1L));
        System.out.println(product);
    }

}
