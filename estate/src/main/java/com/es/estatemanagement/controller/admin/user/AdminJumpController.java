package com.es.estatemanagement.controller.admin.user;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 *
 * </p>
 * @author yang
 * @date 2024/5/20
 */
@Controller
@RequestMapping("/")
public class AdminJumpController {

    //跳转到物业管理人员列表页面
    @RequestMapping(value = "/adminlist.html")
    public String adminlist() {
        return "/admin/adminlist.html";
    }

    //跳转到物业管理人员添加页面
    @RequestMapping(value = "/adminadd.html")
    public String adminadd() {
        return "/admin/adminadd.html";
    }

}
