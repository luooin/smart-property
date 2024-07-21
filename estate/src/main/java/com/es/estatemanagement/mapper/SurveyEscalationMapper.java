package com.es.estatemanagement.mapper;

import com.es.estatemanagement.domain.SurveyEscalation;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface SurveyEscalationMapper extends Mapper<SurveyEscalation> {
    @Select("select project_name from  tb_survey_escalation group by project_name")
    public List<String> getProjectName();
}
