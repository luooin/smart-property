package com.es.estatemanagement.controller.car.parking;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 车辆管理
 * 页面跳转
 * */
@Controller
@RequestMapping("/")
public class ParkingJumpController {

    //跳转到车位管理页面
    @RequestMapping("/parkinglist.html")
    public String parkinglist(){
        return "/car/parkingspace/parkinglist.html";
    }

    //跳转到车位增加页面
    @RequestMapping("/parkadd.html")
    public String parkadd(){ return "/car/parkingspace/parkadd.html"; }
}
