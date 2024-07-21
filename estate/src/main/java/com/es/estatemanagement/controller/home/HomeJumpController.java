package com.es.estatemanagement.controller.home;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 房产管理-页面跳转
 */
@Controller
@RequestMapping("/")
public class HomeJumpController {

    //跳转到房产管理页面
    @RequestMapping("/homelist.html")
    public String homelist(){
        return "/home/homelist.html";
    }

    //跳转到房产管理添加页面
    @RequestMapping("/homeadd.html")
    public String homeadd(){
        return "/home/homeadd.html";
    }

}
