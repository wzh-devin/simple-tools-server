package com.devin.simpletools_server.domain.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * 2025/1/16 21:00
 * <p></p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Data
public class ProdDto {
    /**
     * 商品id
     */
    private Long prodId;

    /**
     * 商品名称
     */
    private String prodName;

    /**
     * 商品描述
     */
    private String prodDesc;

    /**
     * 服务id
     */
    private Long serviceId;

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 服务描述
     */
    private String serviceDesc;

    /**
     * 类型id
     */
    private Long typeId;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 类型描述
     */
    private String typeDesc;

    /**
     * 商品链接
     */
    private String prodLink;

    /**
     * 商品备用链接
     */
    private String prodBackupLink;

    /**
     * 是否正常
     */
    private Integer isActive;

    /**
     * 商品创建时间
     */
    private LocalDate createTime;

    /**
     * 商品更新时间
     */
    private LocalDate updateTime;
}
