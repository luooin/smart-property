package com.es.estatemanagement.controller.owner.vehicle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 车辆管理-页面跳转
 */
@Controller
@RequestMapping("/")
public class VehicleJumpController {

    //跳转到车辆管理页面
    @RequestMapping("/vehicle_list.html")
    public String vehicleList() {
        return "/owner/vehicle_list.html";
    }

    //跳转到车辆管理添加页面
    @RequestMapping("/vehicle_add.html")
    public String vehicleAdd() {
        return "/owner/vehicle_add.html";
    }

}
