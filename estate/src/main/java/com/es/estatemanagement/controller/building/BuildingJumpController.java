package com.es.estatemanagement.controller.building;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *
 * </p>
 * @author yang
 * @date 2024/5/20
 */
@Controller
@RequestMapping("/")
public class BuildingJumpController {

    //跳转到栋数管理列表页面
    @RequestMapping(value = "/buildinglst.html")
    public String buildinglst() {
        return "/building/buildinglst.html";
    }

    //跳转到栋数管理添加页面
    @RequestMapping(value = "/buildingadd.html")
    public String buildingadd() {
        return "/building/buildingadd.html";
    }

}
