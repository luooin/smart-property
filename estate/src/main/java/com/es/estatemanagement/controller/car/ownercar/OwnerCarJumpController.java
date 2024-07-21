package com.es.estatemanagement.controller.car.ownercar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 业主车辆管理-页面跳转
 */
@Controller
@RequestMapping("/")
public class OwnerCarJumpController {


    //跳转到车辆管理页面
    @RequestMapping("/owner_car.html")
    public String ownerCar() {
        return "/car/owner/owner_car.html";
    }

    //跳转到车辆管理添加页面
    @RequestMapping("/owner_car_add.html")
    public String owner_car_add() {
        return "/car/owner/owner_car_add.html";
    }

}
