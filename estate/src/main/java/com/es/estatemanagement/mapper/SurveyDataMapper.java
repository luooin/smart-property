package com.es.estatemanagement.mapper;

import com.es.estatemanagement.domain.SurveyData;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface SurveyDataMapper extends Mapper<SurveyData> {

    @Select("select question_id from tb_survey_data where survey_id=#{surveyId}")
    public List<Integer> findBySurveyId(Integer surveyId);


    @Select("select MAX(question_id) from tb_survey_data ")
    public Integer maxId();

    @Select("select * from tb_survey_data where survey_id in (select survey_id from  tb_survey_project where project_name=#{projectName})")
    public List<SurveyData> findBySurveyIdInName(String projectName);

}
