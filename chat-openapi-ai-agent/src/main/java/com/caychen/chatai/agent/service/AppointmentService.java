package com.caychen.chatai.agent.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.caychen.chatai.agent.entity.Appointment;

/**
 * @Author: Caychen
 * @Date: 2025/5/11 13:12
 * @Description:
 */
public interface AppointmentService extends IService<Appointment> {

    Appointment getOne(Appointment appointment);
}
