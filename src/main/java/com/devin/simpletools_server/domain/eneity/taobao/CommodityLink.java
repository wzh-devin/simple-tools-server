package com.devin.simpletools_server.domain.eneity.taobao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;

/**
 * 2025/1/17 18:49
 * <p>
 *     商品链接
 * </p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Data
@TableName("commodity_link")
public class CommodityLink {

    @TableId
    private Long id;

    @TableField("commodity_id")
    private Long commodityId;

    @TableField("link")
    private String link;

    @TableField("description")
    private String description;

    @TableField("is_active")
    private Integer isActive;

    @TableField("create_time")
    private LocalDate createTime;

    @TableField("update_time")
    private LocalDate updateTime;
}
