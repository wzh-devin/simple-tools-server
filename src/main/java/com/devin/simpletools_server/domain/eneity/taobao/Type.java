package com.devin.simpletools_server.domain.eneity.taobao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * 2025/1/16 18:13
 * <p>
 *     类型表
 * </p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Data
@TableName("type")
public class Type implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 类型表id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 类型名称
     */
    private String name;

    /**
     * 类型描述
     */
    private String typeDesc;

    /**
     * 类型创建时间
     */
    private LocalDate createTime;

    /**
     * 类型更新时间
     */
    private LocalDate updateTime;

    public Type() {
    }
}
