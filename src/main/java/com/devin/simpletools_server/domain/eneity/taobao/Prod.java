package com.devin.simpletools_server.domain.eneity.taobao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 2025/1/16 18:05
 * <p>
 *     商品表
 * </p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Data
@TableName("prod")
public class Prod implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
     * 商品id
     */
    private Long id;

    /**
     * 商品名
     */
    @TableField("name")
    private String name;

    /**
     * 商品描述
     */
    @TableField("prod_desc")
    private String prodDesc;

    /**
     * 商品的操作系统类型
     */
    @TableField("prod_os")
    private String prodOs;

    /**
     * 商品服务id
     */
    @TableField("service_id")
    private Long serviceId;

    /**
     * 商品类型id
     */
    @TableField("type_id")
    private Long typeId;

    /**
     * 商品链接
     */
    @TableField("prod_link")
    private String prodLink;

    /**
     * 商品备用链接
     */
    @TableField("prod_backup_link")
    private String prodBackupLink;

    /**
     * 商品是否正常（1：正常，0：暂停）
     */
    @TableField("is_active")
    private Integer isActive;

    /**
     * 商品创建时间
     */
    @TableField("create_time")
    private LocalDate createTime;

    /**
     * 商品更新时间
     */
    @TableField("update_time")
    private LocalDate updateTime;

    public Prod() {
    }
}
