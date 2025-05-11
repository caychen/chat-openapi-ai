package com.caychen.chatai.agent.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: Caychen
 * @Date: 2025/5/11 11:39
 * @Description:
 */
@Data
@TableName("appointment")
public class Appointment {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String username;

    @TableField("id_card")
    private String idCard;

    private String phone;

    private String date;

    private String time;

    @TableField("vehicle_model")
    private String vehicleModel;

    @TableField("shop_id")
    private Long shopId;

    private String contact;

}
