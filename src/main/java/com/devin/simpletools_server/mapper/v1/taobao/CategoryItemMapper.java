package com.devin.simpletools_server.mapper.v1.taobao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.devin.simpletools_server.domain.eneity.taobao.Category;
import com.devin.simpletools_server.domain.eneity.taobao.CategoryItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * 2025/1/17 19:31
 * <p></p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Mapper
public interface CategoryItemMapper extends BaseMapper<CategoryItem> {
}
