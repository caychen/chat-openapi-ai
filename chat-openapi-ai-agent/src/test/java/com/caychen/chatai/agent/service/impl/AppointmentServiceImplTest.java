package com.caychen.chatai.agent.service.impl;

import com.caychen.chatai.agent.entity.Appointment;
import com.caychen.chatai.agent.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppointmentServiceImplTest {

    @Autowired
    private AppointmentService appointmentService;

    @Test
    public void testFind() {
        Appointment appointment = new Appointment();
        appointment.setDate("2025-05-11");
        Appointment one = appointmentService.getOne(appointment);
        System.out.println(one);
    }
}