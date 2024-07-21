package com.es.estatemanagement.service;

import com.es.estatemanagement.domain.SurveyProject;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface SurveyProjectService {

    public List<SurveyProject> findAll();

    public Page<SurveyProject> search(Map searchMap);

    public Boolean add(SurveyProject surveyProject);

    public SurveyProject findById(Integer id);

    public Boolean update(SurveyProject surveyProject);

    public Boolean del(List<Integer> ids);

    public List<String> projectName();

    public SurveyProject findByProjectName(String projectName);
}
