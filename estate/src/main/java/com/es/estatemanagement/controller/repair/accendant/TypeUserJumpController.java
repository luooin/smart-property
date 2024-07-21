package com.es.estatemanagement.controller.repair.accendant;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 维修师傅页面跳转
 * */
@Controller
@RequestMapping("/")
public class TypeUserJumpController {
    @RequestMapping("/accendantlist.html")
    public String accendantlist(){
        return "/setting/accendantlist.html";
    }

    //跳转到修改在线状态页面
    @RequestMapping("/accendantupdate.html")
    public String accendantupdate(){
        return "/setting/accendantupdate.html";
    }

    //跳转到添加维修师傅页面
    @RequestMapping("/accendantadd.html")
    public String accendantadd(){
        return "/setting/accendantadd.html";
    }
}
