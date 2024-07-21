package com.es.estatemanagement.service;

import com.es.estatemanagement.domain.SurveyData;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface SurveyDataService {

    public List<SurveyData> findAll();

    public Page<SurveyData> search(Map searchMap);

    public Boolean add(SurveyData surveyProject);

    public SurveyData findById(Integer id);

    public Boolean update(SurveyData surveyProject);

    public Boolean del(List<Integer> ids);

    public List<SurveyData> findBySurveyIdInName(String projectName);
}
