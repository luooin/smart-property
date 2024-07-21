package com.es.estatemanagement.controller.car.depot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 停车场管理
 * 页面跳转
 * */
@Controller
@RequestMapping("/")
public class DepotJumpController {
    //跳转到车位管理页面
    @RequestMapping("/depotlist.html")
    public String parkinglist(){
        return "/car/depot/depotlist.html";
    }


    //跳转到停车场增加页面
    @RequestMapping("/depotadd.html")
    public String parkadd(){ return "/car/depot/depotadd.html"; }
}
