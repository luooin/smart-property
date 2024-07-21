package com.es.estatemanagement.controller.car.backlist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 黑白名单
 * 页面跳转
 * */
@Controller
@RequestMapping("/")
public class BacklistJumpController {

    //跳转到车位管理页面
    @RequestMapping("/backlist.html")
    public String backList(){
        return "/car/back/backlist.html";
    }
}
