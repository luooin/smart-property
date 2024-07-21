package com.es.estatemanagement.controller.car.parkingusage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 车位使用
 * 页面跳转
 * */
@Controller
@RequestMapping("/")
public class ParkingusageJumpController {


    //跳转到车位使用管理页面
    @RequestMapping("/parkingusagelist.html")
    public String parkinglist(){
        return "/car/parkingspace/parkingusagelist.html";
    }

    //跳转到车位使用管理增加页面
    @RequestMapping("/parkingusageadd.html")
    public String parkadd(){ return "/car/parkingspace/parkingusageadd.html"; }

}
