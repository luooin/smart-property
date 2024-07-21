package com.es.estatemanagement.controller.car.parkingapplication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 车位申请
 * 页面跳转
 * */
@Controller
@RequestMapping("/")
public class ParkingApplicationJumpController {

    //跳转到车位申请管理页面
    @RequestMapping("/parkingapplicationlist.html")
    public String parkinglist(){
        return "/car/parkingapplication/parkingapplicationlist.html";
    }

    //跳转到车位申请增加页面
    @RequestMapping("/parkingapplicationadd.html")
    public String parkadd(){ return "/car/parkingapplication/parkingapplicationadd.html"; }

    //跳转到选择业主信息页面
    @RequestMapping("/owner.html")
    public String owner(){ return "/car/parkingapplication/owner.html";}

    //跳转到审核申请车位页面
    @RequestMapping("/audit_result.html")
    public String audit_result(){ return "/car/parkingapplication/audit_result.html";}
}
