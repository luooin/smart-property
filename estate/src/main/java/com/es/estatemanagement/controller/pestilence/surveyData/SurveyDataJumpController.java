package com.es.estatemanagement.controller.pestilence.surveyData;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 问卷题目
 * 页面跳转
 * */
@Controller
@RequestMapping("/")
public class SurveyDataJumpController {

    //跳转到问卷题目页面
    @RequestMapping("/survey_date.html")
    public String surveyProjectList(Integer id, Model model){
        model.addAttribute("surveyId",id);
        return "/pestilence/survey_date.html";
    }

    //跳转到问卷题目添加页面
    @RequestMapping("/survey_date_add.html")
    public String surveyProjectAdd(){
        return "/pestilence/survey_date_add.html";
    }

}
