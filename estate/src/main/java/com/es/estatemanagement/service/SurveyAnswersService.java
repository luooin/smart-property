package com.es.estatemanagement.service;

import com.es.estatemanagement.domain.SurveyAnswers;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface SurveyAnswersService {
    public List<SurveyAnswers> findAll();

    public Page<SurveyAnswers> search(Map searchMap);

    public List<SurveyAnswers> findByquestionId(Integer questionId);

    public Boolean add(SurveyAnswers surveyProject);

    public SurveyAnswers findById(Integer id);

    public Boolean update(SurveyAnswers surveyProject);

    public Boolean del(List<Integer> ids);

    public List<SurveyAnswers> findByQuestionIdInName(String projectName);

}
