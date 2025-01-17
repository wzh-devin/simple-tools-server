package com.devin.simpletools_server.domain.eneity.taobao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;

/**
 * 2025/1/17 18:45
 * <p>
 *     商品实体类
 * </p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Data
@TableName("commodity")
public class Commodity {
    @TableId
    private Long id;

    @TableField("name")
    private String name;

    @TableField("description")
    private String description;

    @TableField("category_item_id")
    private Long categoryItemId;

    @TableField("is_active")
    private Integer isActive;

    @TableField("create_time")
    private LocalDate createTime;

    @TableField("update_time")
    private LocalDate updateTime;
}
