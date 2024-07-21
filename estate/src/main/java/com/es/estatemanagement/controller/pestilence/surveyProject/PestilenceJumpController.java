package com.es.estatemanagement.controller.pestilence.surveyProject;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 疫情管理
 * 页面跳转
 * */
@Controller
@RequestMapping("/")
public class PestilenceJumpController {

    //跳转到疫情设置页面
    @RequestMapping("/survey_project.html")
    public String surveyProjectList(){
        return "/pestilence/survey_project.html";
    }

    //跳转到疫情设置添加页面
    @RequestMapping("/survey_project_add.html")
    public String surveyProjectAdd(){
        return "/pestilence/survey_project_add.html";
    }

}
