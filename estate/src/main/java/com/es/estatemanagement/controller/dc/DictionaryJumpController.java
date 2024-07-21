package com.es.estatemanagement.controller.dc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *字典管理-页面跳转
 */
@Controller
@RequestMapping("/")
public class DictionaryJumpController {

    //跳转到字典管理页面
    @RequestMapping("/dictionary.html")
    public String dictionary(){
        return "/dc/dictionary.html";
    }

    //跳转到字典管理添加页面
    @RequestMapping("/dictionaryadd.html")
    public String dictionaryadd(){
        return "/dc/dictionaryadd.html";
    }

    //跳转到字典管理列表页面
    @RequestMapping("/dictionaryview.html")
    public String dictionaryview(){
        return "/dc/dictionaryview.html";
    }








}
