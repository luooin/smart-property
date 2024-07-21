package com.es.estatemanagement.controller.repair.setting;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 报修设置页面跳转
 * */
@Controller
@RequestMapping("/")
public class RepairSettingJumpController {
    //跳转到报修设置页面
    @RequestMapping("/repairsettinglist.html")
    public String repairsettinglist(){
        return "/setting/repairsettinglist.html";
    }

    //跳转到添加页面
    @RequestMapping("/repairsettingadd.html")
    public String repairsettingadd(){
        return "/setting/repairsettingadd.html";
    }
}
