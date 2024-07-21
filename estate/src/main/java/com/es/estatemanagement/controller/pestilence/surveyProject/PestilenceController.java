package com.es.estatemanagement.controller.pestilence.surveyProject;


import com.alibaba.fastjson.JSONArray;
import com.es.estatemanagement.common.MessageConstant;
import com.es.estatemanagement.common.PageResult;
import com.es.estatemanagement.common.Result;
import com.es.estatemanagement.common.StatusCode;
import com.es.estatemanagement.domain.SurveyEscalation;
import com.es.estatemanagement.domain.SurveyProject;
import com.es.estatemanagement.service.impl.SurveyProjectServiceImpl;
import com.es.estatemanagement.util.excel.ExcelUtils;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 疫情管理
 * </p>
 * @author yang
 * @date 2024/5/20
 */
@RestController
@RequestMapping("/pestilence")
@Api(tags = "疫情管理",description = "只负责接收前端浏览器发送的请求和请求参数，调用service层获取业务逻辑加工处理后的数据",value = "BuildingController类")
public class PestilenceController {
    @Autowired
    private SurveyProjectServiceImpl surveyProjectService;


    @RequestMapping("/find")
    public Result find(){
        List<SurveyProject> all = surveyProjectService.findAll();
        return new Result(true,200,"请求成功",all);
    }

    @RequestMapping("/projectName")
    public Result projectName(){
        List<String> all = surveyProjectService.projectName();
        return new Result(true,200,"请求成功",all);
    }

    @RequestMapping("/findByprojectName")
    public Result findByprojectName(@RequestParam(defaultValue = "", required = false) String projectName){
        SurveyProject all = surveyProjectService.findByProjectName(projectName);
        return new Result(true,200,"请求成功",all);
    }

    @RequestMapping("/search")
    public PageResult search(@RequestBody Map searchMap){
        Page<SurveyProject> page = surveyProjectService.search(searchMap);
        return new PageResult(true, StatusCode.OK, MessageConstant.PROJECT_SEARCH_SUCCESS,page.getResult(),page.getTotal());
    }

    @RequestMapping("/add")
    public Result add(@RequestBody SurveyProject surveyProject){
        Boolean add = surveyProjectService.add(surveyProject);
        return new Result(true,StatusCode.OK,MessageConstant.PROJECT_ADD_SUCCESS);
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
        SurveyProject surveyProject = surveyProjectService.findById(id);
        return new Result(true,StatusCode.OK,MessageConstant.PROJECT_FIND_BY_ID_SUCCESS,surveyProject);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody SurveyProject surveyProject){
        Boolean add = surveyProjectService.update(surveyProject);
        return new Result(true,StatusCode.OK,MessageConstant.PROJECT_UPDATE_SUCCESS);
    }

    @RequestMapping("/del")
    public Result del(@RequestBody List<Integer> ids){
        Boolean flag = surveyProjectService.del(ids);
        return new Result(true,StatusCode.OK,MessageConstant.PROJECT_DELETE_SUCCESS);
    }


}
