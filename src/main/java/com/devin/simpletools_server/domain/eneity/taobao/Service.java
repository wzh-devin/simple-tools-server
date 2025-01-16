package com.devin.simpletools_server.domain.eneity.taobao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * 2025/1/16 18:11
 * <p>
 *     服务表
 * </p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Data
@TableName("service")
public class Service implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
     * 服务id
     */
    private Long id;

    /**
     * 服务名
     */
    private String name;

    /**
     * 服务描述
     */
    private String serviceDesc;

    /**
     * 商品是否激活（1：正常，0：失效）
     */
    private Integer isActive;

    /**
     * 服务创建时间
     */
    private LocalDate createTime;

    /**
     * 服务更新时间
     */
    private LocalDate updateTime;

    public Service() {}
}
