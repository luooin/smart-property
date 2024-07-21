package com.es.estatemanagement.controller.owner.personnel;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 人员管理-页面跳转
 */
@Controller
@RequestMapping("/")
public class PersonnelJumpController {

    //跳转到人员管理页面
    @RequestMapping("/personnel_list.html")
    public String personnelList(){
        return "/owner/personnel_list.html";
    }

    //跳转到人员管理添加页面
    @RequestMapping("/personnel_add.html")
    public String personnelAdd(){
        return "/owner/personnel_add.html";
    }


}
