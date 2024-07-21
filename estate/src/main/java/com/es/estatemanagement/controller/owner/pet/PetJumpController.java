package com.es.estatemanagement.controller.owner.pet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 宠物管理-页面跳转
 */
@Controller
@RequestMapping("/")
public class PetJumpController {

    //跳转到宠物管理页面
    @RequestMapping("/pet_list.html")
    public String petList() {
        return "/owner/pet_list.html";
    }

    //跳转到宠物管理添加页面
    @RequestMapping("/pet_add.html")
    public String petAdd() {
        return "/owner/pet_add.html";
    }
}
