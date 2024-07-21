package com.es.estatemanagement.domain;


import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 疫情项目表
 */
@Table(name = "tb_survey_project")
public class SurveyProject implements Serializable {

//    https://www.cnblogs.com/markleilei/p/15562785.html
    @Id
    private Integer surveyId;    //项目ID
    private String projectType;  //项目类型
    private String projectName;  //项目名称
    private Date startTime;      //开始时间
    private Date terminationTime;//结束时间

    public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getTerminationTime() {
        return terminationTime;
    }

    public void setTerminationTime(Date terminationTime) {
        this.terminationTime = terminationTime;
    }
}
