package com.devin.simpletools_server.domain.eneity.taobao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 2025/1/17 18:51
 * <p>
 *     类别表
 * </p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Data
@TableName("category")
public class Category {
    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("description")
    private String description;

    @TableField("is_active")
    private Integer isActive;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 填充时间
     */
    public void fillTime() {
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }
}
