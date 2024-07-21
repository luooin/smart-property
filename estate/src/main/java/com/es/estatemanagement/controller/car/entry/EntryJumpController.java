package com.es.estatemanagement.controller.car.entry;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 进场记录
 * 页面跳转
 * */
@Controller
@RequestMapping("/")
public class EntryJumpController {

    //跳转到车位管理页面
    @RequestMapping("/entrylist.html")
    public String parkinglist(){
        return "/car/entry/entrylist.html";
    }
}
