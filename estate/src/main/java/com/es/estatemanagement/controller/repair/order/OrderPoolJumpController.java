package com.es.estatemanagement.controller.repair.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 工单池页面跳转
 * */
@Controller
@RequestMapping("/")
public class OrderPoolJumpController {

    //跳转到电话报修页面
    @RequestMapping("/orderpoollist.html")
    public String orderpoollist(){
        return "/orderPool/orderpoollist.html";
    }

    //跳转到报修登记页面
    @RequestMapping("/orderpooladd.html")
    public String orderpooladd(){
        return "/orderPool/orderpooladd.html";
    }
}
