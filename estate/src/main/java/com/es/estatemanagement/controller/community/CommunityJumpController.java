package com.es.estatemanagement.controller.community;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 小区管理-页面跳转
 */
@Controller
@RequestMapping("/")
public class CommunityJumpController {

    //跳转到小区列表
    @RequestMapping(value = "/communitylist.html")
    public String communitylist() {
        return "/community/communitylist.html";
    }

    //跳转到小区添加页面
    @RequestMapping(value = "/communityadd.html")
    public String communityadd() {
        return "/community/communityadd.html";
    }

    //跳转到小区报表
    @RequestMapping(value = "/indexs.html")
    public String communityIndexs() {
        return "/community/indexs.html";
    }

}
