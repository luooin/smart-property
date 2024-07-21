package com.es.estatemanagement.controller.pestilence.surveyEscalation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 疫情上报
 * 页面跳转
 * */
@Controller
@RequestMapping("/")
public class SurveyEscalationJumpController {

    //跳转到疫情上报页面
    @RequestMapping("/survey_escalation.html")
    public String surveyEscalationList(Integer id, Model model){
        model.addAttribute("surveyId",id);
        return "/pestilence/survey_escalation.html";
    }

    //跳转到疫情登记页面
    @RequestMapping("/survey_escalation_add.html")
    public String surveyEscalationAdd(){
        return "/pestilence/survey_escalation_add.html";
    }
}
