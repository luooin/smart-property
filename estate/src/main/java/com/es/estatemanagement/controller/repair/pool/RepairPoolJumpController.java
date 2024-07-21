package com.es.estatemanagement.controller.repair.pool;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 报修设置页面跳转
 * */
@Controller
@RequestMapping("/")
public class RepairPoolJumpController {
    //跳转到电话报修页面
    @RequestMapping("/repairpoollist.html")
    public String repairpoollist(){
        return "/pool/repairpoollist.html";
    }

    //跳转到报修登记页面
    @RequestMapping("/repairpooladd.html")
    public String repairpooladd(){
        return "/pool/repairpooladd.html";
    }

}
