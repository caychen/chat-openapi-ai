package com.caychen.chatai.agent.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * @Author: Caychen
 * @Date: 2025/5/11 11:42
 * @Description: 排班实体类
 */
@Data
@TableName("scheduler")
public class Scheduler {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.NONE)
    private Long id;

    /**
     * 日期
     */
    @TableField("date")
    private String date;

    /**
     * 时间
     */
    @TableField("time")
    private String time;

    /**
     * 店铺id
     */
    @TableField("shop_id")
    private Long shopId;

    /**
     * 可约数量
     */
    @TableField("total")
    private Integer total;

    /**
     * 剩余可约数量
     */
    @TableField("remain")
    private Integer remain;
}
