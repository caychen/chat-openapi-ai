package com.caychen.chatai.agent.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caychen.chatai.agent.entity.Appointment;
import com.caychen.chatai.agent.mapper.AppointmentMapper;
import com.caychen.chatai.agent.service.AppointmentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @Author: Caychen
 * @Date: 2025/5/11 13:13
 * @Description:
 */
@Service
public class AppointmentServiceImpl extends ServiceImpl<AppointmentMapper, Appointment> implements AppointmentService {

    @Override
    public Appointment getOne(Appointment appointment) {
        LambdaQueryWrapper<Appointment> queryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.isNotBlank(appointment.getDate())) {
            queryWrapper.eq(Appointment::getDate, appointment.getDate());
        }
        if (StringUtils.isNotBlank(appointment.getTime())) {
            queryWrapper.eq(Appointment::getTime, appointment.getTime());
        }
        if (appointment.getShopName() != null) {
            queryWrapper.eq(Appointment::getShopName, appointment.getShopName());
        }
        if (appointment.getVehicleModel() != null) {
            queryWrapper.eq(Appointment::getVehicleModel, appointment.getVehicleModel());
        }
        if (StringUtils.isNotBlank(appointment.getUsername())) {
            queryWrapper.eq(Appointment::getUsername, appointment.getUsername());
        }
        if (StringUtils.isNotBlank(appointment.getIdCard())) {
            queryWrapper.eq(Appointment::getIdCard, appointment.getIdCard());
        }
        if (StringUtils.isNotBlank(appointment.getPhone())) {
            queryWrapper.eq(Appointment::getPhone, appointment.getPhone());
        }

        return baseMapper.selectOne(queryWrapper);
    }
}
