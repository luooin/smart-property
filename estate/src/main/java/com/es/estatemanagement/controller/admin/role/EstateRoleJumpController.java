package com.es.estatemanagement.controller.admin.role;


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
public class EstateRoleJumpController {

    //跳转到用户组管理页面
    @RequestMapping(value = "/admingroup.html")
    public String admingroup() {
        return "/admin/admingroup.html";
    }

    //跳转到用户组管理添加页面
    @RequestMapping(value = "/admingroupadd.html")
    public String admingroupadd() {
        return "/admin/admingroupadd.html";
    }
}
