package com.caychen.chatai.agent.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: Caychen
 * @Date: 2025/5/11 11:42
 * @Description: 车型实体类
 */
@Data
@TableName("vehicle_model")
public class VehicleModel {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.NONE)
    private Long id;

    /**
     * 车型名称
     */
    @TableField("vehicle_model_name")
    private String vehicleModelName;

    /**
     * 价格
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 特点
     */
    @TableField("feature")
    private String feature;
}
