package com.devin.simpletools_server.dao.v1.taobao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.devin.simpletools_server.domain.eneity.taobao.Category;
import com.devin.simpletools_server.mapper.v1.taobao.CategoryMapper;
import org.springframework.stereotype.Service;

/**
 * 2025/1/17 19:32
 * <p></p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Service
public class CategoryDao extends ServiceImpl<CategoryMapper, Category> {
}
