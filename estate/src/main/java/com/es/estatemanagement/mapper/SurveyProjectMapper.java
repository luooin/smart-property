package com.es.estatemanagement.mapper;

import com.es.estatemanagement.domain.SurveyProject;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface SurveyProjectMapper extends Mapper<SurveyProject> {

    @Select("select project_name from tb_survey_project")
    public List<String> projectName();

    @Select("select * from  tb_survey_project where project_name=#{projectName}")
    public SurveyProject findByProjectName(String projectName);

}
