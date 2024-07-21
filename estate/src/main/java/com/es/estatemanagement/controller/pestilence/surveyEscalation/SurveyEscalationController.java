package com.es.estatemanagement.controller.pestilence.surveyEscalation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.es.estatemanagement.common.MessageConstant;
import com.es.estatemanagement.common.PageResult;
import com.es.estatemanagement.common.Result;
import com.es.estatemanagement.common.StatusCode;
import com.es.estatemanagement.domain.SurveyEscalation;
import com.es.estatemanagement.service.impl.SurveyEscalationServiceImpl;
import com.es.estatemanagement.util.excel.ExcelUtils;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 疫情上报管理
 * </p>
 * @author yang
 * @date 2024/5/20
 */
@RestController
@RequestMapping("/surveyEscalation")
@Api(tags = "疫情上报管理", description = "只负责接收前端浏览器发送的请求和请求参数，调用service层获取业务逻辑加工处理后的数据", value = "SurveyEscalationController类")
public class SurveyEscalationController {

    @Autowired
    SurveyEscalationServiceImpl surveyEscalationService;

    @PostMapping("/import")
    public String upload(@RequestPart("file") MultipartFile file) throws Exception {
        Map<String, JSONArray> map = ExcelUtils.readFileManySheet(file);
        //{"时间":"2022-11-23 08:55:46","题目":"请问你是hello哥吗?","rowNum":2,"项目":"关于疫情的调查问卷","登记人":"土杰","内容":"是","类型":"进出门上报"}
        map.forEach((key, value) -> {
//            System.out.println("Sheet名称：" + key);
//            System.out.println("Sheet数据：" + value);
            List<Object> statusList = JSONObject.parseArray(value.toJSONString(), Object.class);
            for (int i = 0; i < statusList.size(); i++) {
                Map maps = (Map) JSON.parse(String.valueOf(statusList.get(i)));
                String createTime = (String) maps.get("时间");
                String dataQuestion = (String) maps.get("题目");
                String projectName = (String) maps.get("项目");
                String name = (String) maps.get("登记人");
                String choiceText = (String) maps.get("内容");
                String projectType = (String) maps.get("类型");
                try {
                    SurveyEscalation surveyEscalation = new SurveyEscalation();
                    surveyEscalation.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTime));
                    surveyEscalation.setDataQuestion(dataQuestion);
                    surveyEscalation.setProjectName(projectName);
                    surveyEscalation.setName(name);
                    surveyEscalation.setChoiceText(choiceText);
                    surveyEscalation.setProjectType(projectType);
                    surveyEscalationService.add(surveyEscalation);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        return "/survey_escalation.html";
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) {
        List<SurveyEscalation> surveyEscalations = surveyEscalationService.findAll();
        ExcelUtils.export(response, "疫情上报表", surveyEscalations, SurveyEscalation.class);
    }

    /**
     * 导出多 Sheet 页实现
     */
    @GetMapping("/exportManySheet")
    public void exportManySheet(HttpServletResponse response) {
        List<SurveyEscalation> surveyEscalations = surveyEscalationService.findAll();
        List<String> projectName = surveyEscalationService.getProjectName();
        Map<String, List<List<Object>>> sheets = new LinkedHashMap<>();
        for (String pjcName : projectName) {
            for (SurveyEscalation escalation : surveyEscalations) {
                if (escalation.getProjectName().equals(pjcName)) {
                    List<List<Object>> sheet = new ArrayList<>();
                    List<Object> sheetHead = new ArrayList<>();
                    sheetHead.add("项目");
                    sheetHead.add("登记人");
                    sheetHead.add("题目");
                    sheetHead.add("内容");
                    sheetHead.add("类型");
                    sheetHead.add("时间");
                    sheet.add(sheetHead);
                    for (SurveyEscalation escalations : surveyEscalations) {
                        if (escalations.getProjectName().equals(pjcName)) {
                            List<Object> row = new ArrayList<>();
                            row.add(escalations.getProjectName());
                            row.add(escalations.getName());
                            row.add(escalations.getDataQuestion());
                            row.add(escalations.getChoiceText());
                            row.add(escalations.getProjectType());
                            row.add(escalations.getCreateTime());
                            sheet.add(row);
                        }
                    }
                    sheets.put(pjcName, sheet);
                }
            }
        }
        // 导出数据
        ExcelUtils.exportManySheet(response, "疫情上报表", sheets);
    }

    @RequestMapping("/search")
    public PageResult search(@RequestBody Map searchMap) {
        Page<SurveyEscalation> page = surveyEscalationService.search(searchMap);
        return new PageResult(true, StatusCode.OK, MessageConstant.ESCALATION_SEARCH_SUCCESS, page.getResult(), page.getTotal());
    }


    @RequestMapping("/add")
    public Result add(@RequestBody SurveyEscalation surveyEscalation) {
        Boolean add = surveyEscalationService.add(surveyEscalation);
        return new Result(true, StatusCode.OK, MessageConstant.ESCALATION_ADD_SUCCESS);
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        SurveyEscalation surveyEscalation = surveyEscalationService.findById(id);
        return new Result(true, StatusCode.OK, MessageConstant.ESCALATION_FIND_BY_ID_SUCCESS, surveyEscalation);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody SurveyEscalation surveyEscalation) {
        Boolean add = surveyEscalationService.update(surveyEscalation);
        return new Result(true, StatusCode.OK, MessageConstant.ESCALATION_UPDATE_SUCCESS);
    }

    @RequestMapping("/del")
    public Result del(@RequestBody List<Integer> ids) {
        Boolean flag = surveyEscalationService.del(ids);
        return new Result(true, StatusCode.OK, MessageConstant.ESCALATION_DELETE_SUCCESS);
    }
}
