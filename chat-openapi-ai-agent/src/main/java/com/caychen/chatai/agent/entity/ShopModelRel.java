
package com.caychen.chatai.agent.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * @Author: Caychen
 * @Date: 2025/5/11 11:42
 * @Description: 4S店与车型关联实体类
 */
@Data
@TableName("shop_model_rel")
public class ShopModelRel {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.NONE)
    private Long id;

    /**
     * 4s店铺id
     */
    @TableField("shop_id")
    private Long shopId;

    /**
     * 在售车型id
     */
    @TableField("model_id")
    private Long modelId;
}
