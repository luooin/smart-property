package com.es.estatemanagement.controller.pestilence.surveyData;


import com.es.estatemanagement.common.MessageConstant;
import com.es.estatemanagement.common.PageResult;
import com.es.estatemanagement.common.Result;
import com.es.estatemanagement.common.StatusCode;
import com.es.estatemanagement.domain.SurveyAnswers;
import com.es.estatemanagement.domain.SurveyData;
import com.es.estatemanagement.domain.SurveyProject;
import com.es.estatemanagement.service.impl.SurveyAnswersServiceImpl;
import com.es.estatemanagement.service.impl.SurveyDataServiceImpl;
import com.es.estatemanagement.service.impl.SurveyProjectServiceImpl;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 问卷题目管理
 * </p>
 * @author yang
 * @date 2024/5/20
 */
@RestController
@RequestMapping("/surveyDate")
@Api(tags = "问卷题目管理", description = "只负责接收前端浏览器发送的请求和请求参数，调用service层获取业务逻辑加工处理后的数据", value = "BuildingController类")
public class SurveyDataController {

    @Autowired
    private SurveyDataServiceImpl surveyDataService;

    @Autowired
    private SurveyAnswersServiceImpl surveyAnswersService;

    @RequestMapping("/find")
    public Result find() {
        List<SurveyData> all = surveyDataService.findAll();
        return new Result(true, 200, "请求成功", all);
    }

    @RequestMapping("/findByquestionId")
    public Result findByquestionId(Integer questionId) {
        List<SurveyAnswers> surveyAnswers= surveyAnswersService.findByquestionId(questionId);
        return new Result(true, 200, "请求成功", surveyAnswers);
    }

    @RequestMapping("/findBySurveyIdInName")
    public Result findBySurveyIdInName(@RequestParam(defaultValue = "", required = false) String projectName) {
        List<SurveyData> surveyAnswers= surveyDataService.findBySurveyIdInName(projectName);
        return new Result(true, 200, "请求成功", surveyAnswers);
    }

    @RequestMapping("/findByQuestionIdInName")
    public Result findByQuestionIdInName(@RequestParam(defaultValue = "", required = false) String projectName) {
        List<SurveyAnswers> surveyAnswers= surveyAnswersService.findByQuestionIdInName(projectName);
        return new Result(true, 200, "请求成功", surveyAnswers);
    }


    @RequestMapping("/search")
    public PageResult search(@RequestBody Map searchMap) {
        Page<SurveyData> page = surveyDataService.search(searchMap);
        return new PageResult(true, StatusCode.OK, MessageConstant.DATA_SEARCH_SUCCESS, page.getResult(), page.getTotal());
    }

    @RequestMapping("/add")
    public Result add(@RequestBody SurveyData surveyData){
        Boolean add = surveyDataService.add(surveyData);
        return new Result(true, StatusCode.OK, MessageConstant.DATA_ADD_SUCCESS);
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        SurveyData surveyData = surveyDataService.findById(id);
        return new Result(true, StatusCode.OK, MessageConstant.DATA_FIND_BY_ID_SUCCESS, surveyData);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody SurveyData surveyData) {
        Boolean add = surveyDataService.update(surveyData);
        return new Result(true, StatusCode.OK, MessageConstant.DATA_UPDATE_SUCCESS);
    }

    @RequestMapping("/del")
    public Result del(@RequestBody List<Integer> ids) {
        Boolean flag = surveyDataService.del(ids);
        return new Result(true, StatusCode.OK, MessageConstant.DATA_DELETE_SUCCESS);
    }
}
