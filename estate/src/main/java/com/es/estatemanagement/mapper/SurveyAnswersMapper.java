package com.es.estatemanagement.mapper;

import com.es.estatemanagement.domain.SurveyAnswers;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SurveyAnswersMapper extends Mapper<SurveyAnswers> {

    @Select("select answer_id from tb_survey_answers where question_id=#{questionId}")
    public List<Integer> answerById(Integer questionId);

    @Select("select * from tb_survey_answers where question_id=#{questionId}")
    public List<SurveyAnswers> findByquestionId(Integer questionId);

    @Delete("DELETE FROM tb_survey_answers where question_id=#{questionId}")
    public Integer deleteByQuestionId(Integer questionId);

    @Select("select answer_id from tb_survey_answers where question_id in (select question_id from  tb_survey_data where survey_id=#{surveyId})")
    public List<Integer> findBySurveyId(Integer surveyId);

    @Select("select * from tb_survey_answers where question_id in (select question_id from tb_survey_data where survey_id in (select survey_id from  tb_survey_project where project_name=#{projectName}))")
    public List<SurveyAnswers> findByQuestionIdInName(String projectName);


}
