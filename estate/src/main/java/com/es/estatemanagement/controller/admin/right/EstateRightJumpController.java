package com.es.estatemanagement.controller.admin.right;

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
public class EstateRightJumpController {

    //跳转到权限规则页面
    @RequestMapping(value = "/authrule.html")
    public String authrule() {
        return "/admin/authrule.html";
    }

    //跳转到权限规则添加页面
    @RequestMapping(value = "/auth_ruleadd.html")
    public String auth_ruleadd() {
        return "/admin/auth_ruleadd.html";
    }

}
