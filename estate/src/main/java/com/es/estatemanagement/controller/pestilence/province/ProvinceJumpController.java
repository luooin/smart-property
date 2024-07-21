package com.es.estatemanagement.controller.pestilence.province;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 返省上报
 * 页面跳转
 * */
@Controller
@RequestMapping("/")
public class ProvinceJumpController {

    //跳转到返省上报页面
    @RequestMapping("/province.html")
    public String provincetList(){
        return "/pestilence/province.html";
    }

    //跳转到返省上报页面
    @RequestMapping("/province_add.html")
    public String provinceAddList(){
        return "/pestilence/province_add.html";
    }

    //跳转到疫情报表页面
    @RequestMapping("/province_dpbb.html")
    public String provinceDpbb(){
        return "/pestilence/province_dpbb.html";
    }
}
