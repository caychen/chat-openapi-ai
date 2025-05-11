package com.caychen.chatai.agent.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: Caychen
 * @Date: 2025/5/11 11:42
 * @Description: 4s店铺实体类
 */
@Data
@TableName("shop")
public class Shop {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区域
     */
    private String district;

    /**
     * 4s店名称
     */
    private String name;

    /**
     * 4s店地址
     */
    private String address;

    /**
     * 4s店电话
     */
    private String mobile;

    /**
     * 4s店评价：1~5分
     */
    private Integer level;

}
