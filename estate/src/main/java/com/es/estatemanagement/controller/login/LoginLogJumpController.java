package com.es.estatemanagement.controller.login;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 日志管理-页面跳转
 */
@Controller
@RequestMapping("/")
public class LoginLogJumpController {

    //跳转到登入日志页面
    @RequestMapping("/login_log.html")
    public String loginLog() {
        return "/loginlog/login_log.html";
    }

    //跳转到操作日志页面
    @RequestMapping("/oper_log.html")
    public String operLog() {
        return "/loginlog/oper_log.html";
    }

}
