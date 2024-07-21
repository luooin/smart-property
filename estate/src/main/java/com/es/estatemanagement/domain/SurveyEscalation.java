package com.es.estatemanagement.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.es.estatemanagement.util.excel.ExcelExport;
import com.es.estatemanagement.util.excel.ExcelImport;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 疫情上报表
 */
@Table(name = "tb_survey_escalation")
public class SurveyEscalation implements Serializable {

    @Id
    private Integer id;             //主键(疫情上报)
    @ExcelImport("项目")
    @ExcelExport(value = "项目")
    private String projectName;     //项目
    @ExcelImport("登记人")
    @ExcelExport(value = "登记人")
    private String name;            //登记人
    @ExcelImport("题目")
    @ExcelExport(value = "题目")
    private String dataQuestion;    //题目
    @ExcelImport("内容")
    @ExcelExport(value = "内容")
    private String choiceText;      //内容
    @ExcelImport("类型")
    @ExcelExport(value = "类型")
    private String projectType;     //类型
    @ExcelImport("时间")
    @ExcelExport(value = "时间")
    private Date createTime;        //时间


    @TableField(exist = false)
    private List<SurveyEscalation> projectList;     //题目,内容

    @Override
    public String toString() {
        return "SurveyEscalation{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", name='" + name + '\'' +
                ", dataQuestion='" + dataQuestion + '\'' +
                ", choiceText='" + choiceText + '\'' +
                ", projectType='" + projectType + '\'' +
                ", createTime=" + createTime +
                ", projectList=" + projectList +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataQuestion() {
        return dataQuestion;
    }

    public void setDataQuestion(String dataQuestion) {
        this.dataQuestion = dataQuestion;
    }

    public String getChoiceText() {
        return choiceText;
    }

    public void setChoiceText(String choiceText) {
        this.choiceText = choiceText;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Object getProjectList() {
        return projectList;
    }

    public void setProjectList(List<SurveyEscalation> projectList) {
        this.projectList = projectList;
    }

}
