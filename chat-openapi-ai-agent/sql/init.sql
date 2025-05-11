CREATE TABLE `appointment` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `username` varchar(50) DEFAULT NULL COMMENT '预约人姓名',
    `id_card` varchar(50) DEFAULT NULL COMMENT '预约人身份证号',
    `phone` varchar(50) DEFAULT NULL COMMENT '预约人手机号',
    `date` varchar(20) DEFAULT NULL COMMENT '预约日期',
    `time` varchar(20) DEFAULT NULL COMMENT '预约时间',
    `vehicle_model` varchar(255) DEFAULT NULL COMMENT '意向车型',
    `shop_name` varchar(50) DEFAULT NULL COMMENT '4s店id',
    `contact` varchar(50) DEFAULT NULL COMMENT '4s联系人',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='预约表';


CREATE TABLE `scheduler` (
    `id` bigint(11) NOT NULL,
    `date` varchar(20) DEFAULT NULL COMMENT '日期',
    `time` varchar(20) DEFAULT NULL COMMENT '时间',
    `shop_id` bigint(10) DEFAULT NULL COMMENT '店铺id',
    `total` int(2) DEFAULT NULL COMMENT '可约数量',
    `remain` int(2) DEFAULT NULL COMMENT '剩余可约数量',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='排班表';


CREATE TABLE `shop` (
    `id` bigint(11) NOT NULL COMMENT '主键',
    `province` varchar(100) DEFAULT NULL COMMENT '省份',
    `city` varchar(100) DEFAULT NULL COMMENT '城市',
    `district` varchar(100) DEFAULT NULL COMMENT '区域',
    `name` varchar(50) DEFAULT NULL COMMENT '4s店名称',
    `address` varchar(255) DEFAULT NULL COMMENT '4s店地址',
    `mobile` varchar(255) DEFAULT NULL COMMENT '4s店电话',
    `level` int(1) DEFAULT NULL COMMENT '4s店评价：1~5分',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='4s店铺表';


CREATE TABLE `shop_model_rel` (
    `id` bigint(20) NOT NULL COMMENT '主键',
    `shop_id` bigint(20) NOT NULL COMMENT '4s店铺id',
    `model_id` bigint(20) NOT NULL COMMENT '在售车型',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='4s店车型关系表';


CREATE TABLE `vehicle_model` (
    `id` bigint(20) NOT NULL COMMENT '主键',
    `vehicle_model_name` varchar(255) DEFAULT NULL COMMENT '车型名称',
    `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
    `feature` text COMMENT '特点',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='车型表';