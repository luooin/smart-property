package com.es.estatemanagement.service;

import com.es.estatemanagement.domain.SurveyEscalation;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface SurveyEscalationService {

    public List<SurveyEscalation> findAll();

    public Page<SurveyEscalation> search(Map searchMap);

    public Boolean add(SurveyEscalation surveyEscalation);

    public SurveyEscalation findById(Integer id);

    public Boolean update(SurveyEscalation surveyEscalation);

    public Boolean del(List<Integer> ids);

    public List<String> getProjectName();

}
