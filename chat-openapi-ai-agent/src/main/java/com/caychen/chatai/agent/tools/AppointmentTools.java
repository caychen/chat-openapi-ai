package com.caychen.chatai.agent.tools;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.caychen.chatai.agent.entity.Appointment;
import com.caychen.chatai.agent.entity.Scheduler;
import com.caychen.chatai.agent.entity.Shop;
import com.caychen.chatai.agent.entity.VehicleModel;
import com.caychen.chatai.agent.service.AppointmentService;
import com.caychen.chatai.agent.service.SchedulerService;
import com.caychen.chatai.agent.service.ShopModelRelService;
import com.caychen.chatai.agent.service.ShopService;
import com.caychen.chatai.agent.service.VehicleModelService;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @Author: Caychen
 * @Date: 2025/5/11 13:58
 * @Description:
 */
@Slf4j
@Component
public class AppointmentTools {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private VehicleModelService vehicleModelService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopModelRelService shopModelRelService;

    @Autowired
    private SchedulerService schedulerService;

    @Tool(name = "查询车型数据", value = "当用户询问有关车型的时候，调用数据库查询所有的车型数据，并返回给用户")
    public List<VehicleModel> queryVehicleModel() {
        log.info("查询车型接口...");
        return vehicleModelService.list();
    }

    @Tool(name = "查下试驾预约的记录")
    public Appointment  queryAppointment(Appointment appointment) {
        log.info("用户查询试驾预约记录...");
        return appointmentService.getOne(appointment);
    }

    @Tool(name = "确认预约试驾", value = "根据参数，先执行工具方法haveEnabledAppointment来查询是否可预约，并直接给用户回答是否可预约，最后需要用户确认所有的预约信息，用户确认完成之后，调用此方法")
    public String bookAppoint(Appointment appointment) {
        log.info("用户提交预约试驾...");

        // 查询数据库中是否包含对应的预约记录
        Appointment exist = appointmentService.getOne(appointment);
        if (exist == null) {
            appointment.setId(null);
            if(appointmentService.save(appointment)){
                return "预约成功";
            }else {
                return "预约失败";
            }
        }
        return "你在相同的时间段和4S店，已经有相同的预约了，不能重复预约";
    }

    @Tool(name = "取消预约试驾", value = "根据参数，查询当前的预约是否存在，如果存在，则删除预约记录，并返回成功，否则返回失败")
    public String cancelAppoint(Appointment appointment) {
        log.info("用户确认取消试驾...");
        Appointment exists = appointmentService.getOne(appointment);
        if (exists != null) {
            appointmentService.removeById(exists.getId());
            return "取消成功";
        } else {
            return "试驾预约记录未找到，无法取消";
        }
    }

    @Tool(name = "获取所在城市的4S店")
    public List<Shop> queryShop(String city) {
        log.info("用户查询4S店信息...");
        LambdaQueryWrapper<Shop> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Shop::getCity, city);
        return shopService.list(queryWrapper);
    }

    @Tool(name = "查询4S店最近一周可以预约的时段", value = "如果用户想查看4S店的预约时段，则帮忙用户查询对应4S店最近一周的可预约时段")
    public Object queryScheduler(@P(value = "4S店") String shopName) {
        log.info("用户查询4S店最近一周的可预约时段,4S店：{}",shopName);
        LambdaQueryWrapper<Shop> shopQueryWrapper = new LambdaQueryWrapper<>();
        shopQueryWrapper.eq(Shop::getName, shopName);
        Shop shop = shopService.getOne(shopQueryWrapper);

        if (shop == null) {
            return "未查询到你想预约的4S店信息，请重新输入你想预约的4S店";
        }

        Instant instant = Instant.now().plus(Duration.ofDays(7));
        LocalDate localDate = LocalDate.ofInstant(instant, ZoneId.systemDefault());

        LambdaQueryWrapper<Scheduler> schedulerQueryWrapper = new LambdaQueryWrapper<>();
        schedulerQueryWrapper.eq(Scheduler::getShopId, shop.getId());
        schedulerQueryWrapper.le(Scheduler::getDate, localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return schedulerService.list(schedulerQueryWrapper);
    }

    @Tool(name = "查询4S店在用户想要的时段是否可约")
    public String haveEnabledAppointment(
            @P(value = "日期") String date,
            @P(value = "时间") String time,
            @P(value = "4S店") String shopName
    ) {
        log.info("查询4S店在用户想要的时段是否可约...");
        LambdaQueryWrapper<Shop> shopQueryWrapper = new LambdaQueryWrapper<>();
        shopQueryWrapper.eq(Shop::getName, shopName);
        Shop shop = shopService.getOne(shopQueryWrapper);

        if (shop == null) {
            return "未查询到你想预约的4S店信息，请重新输入你想预约的4S店";
        }

        LambdaQueryWrapper<Scheduler> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Scheduler::getShopId, shop.getId())
                .eq(Scheduler::getDate, date)
                .eq(Scheduler::getTime, time);

        Scheduler scheduler = schedulerService.getOne(queryWrapper);

        if (scheduler == null) {
            return "不好意思，你想预约的4S店在你想预约时段未有排班信息，请重新选择一个";
        } else if (scheduler.getRemain() > 1) {
            return "恭喜你，可以预约";
        } else {
            return "真不好意思，预约已满了，要不重新选择一个时段吧";
        }

    }

}
