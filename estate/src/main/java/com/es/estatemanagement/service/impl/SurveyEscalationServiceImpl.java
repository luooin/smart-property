package com.es.estatemanagement.service.impl;

import com.es.estatemanagement.domain.Building;
import com.es.estatemanagement.domain.SurveyEscalation;
import com.es.estatemanagement.mapper.SurveyEscalationMapper;
import com.es.estatemanagement.service.SurveyEscalationService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SurveyEscalationServiceImpl implements SurveyEscalationService {
    @Autowired
    SurveyEscalationMapper surveyEscalationMapper;

    @Override
    public List<SurveyEscalation> findAll() {
        List<SurveyEscalation> surveyEscalations = surveyEscalationMapper.selectAll();
        return surveyEscalations;
    }

    @Override
    public Page<SurveyEscalation> search(Map searchMap) {
        //通用Mapper多条件搜索，标准写法
        Example example = new Example(SurveyEscalation.class);
        //1.初始化分页条件
        int pageNum = 1;
        int pageSize = 5;
        if (searchMap != null) {
            Example.Criteria criteria = example.createCriteria();//创建查询条件
            //时间区间
            if (StringUtil.isNotEmpty((String) searchMap.get("startTime"))) {
                criteria.andGreaterThanOrEqualTo("createTime", searchMap.get("startTime"));
            }
            if (StringUtil.isNotEmpty((String) searchMap.get("endTime"))) {
                criteria.andLessThanOrEqualTo("createTime", searchMap.get("endTime"));
                criteria.andLessThanOrEqualTo("createTime", searchMap.get("endTime"));
            }

            //名称模糊搜索
            if (StringUtil.isNotEmpty((String) searchMap.get("name"))) {
                criteria.andLike("name", "%" + (String) searchMap.get("name") + "%");
            }

            //时间范围排序
            example.setOrderByClause("create_time DESC");
            surveyEscalationMapper.selectByExample(example);


            if ((Integer) searchMap.get("pageNum") != null) {
                pageNum = (Integer) searchMap.get("pageNum");
            }
            if ((Integer) searchMap.get("pageSize") != null) {
                pageSize = (Integer) searchMap.get("pageSize");
            }
        }
        PageHelper.startPage(pageNum, pageSize);//使用PageHelper插件完成分页
        Page<SurveyEscalation> surveyEscalations = (Page<SurveyEscalation>) surveyEscalationMapper.selectByExample(example);
        return surveyEscalations;
    }

    @Transactional
    @Override
    public Boolean add(SurveyEscalation surveyEscalation) {
        List<SurveyEscalation> surveyEscalations = (List<SurveyEscalation>) surveyEscalation.getProjectList();
        if (surveyEscalations != null) {
            for (int i = 0; i < surveyEscalations.size(); i++) {
                surveyEscalation.setDataQuestion(surveyEscalations.get(i).getDataQuestion());
                surveyEscalation.setChoiceText(surveyEscalations.get(i).getChoiceText());
                surveyEscalation.setCreateTime(new Date());
                surveyEscalationMapper.insert(surveyEscalation);
//          System.out.println(surveyEscalations.get(i).getDataQuestion()+":"+surveyEscalations.get(i).getChoiceText());
            }
        } else {
            surveyEscalationMapper.insert(surveyEscalation);
        }
        return true;
    }

    @Override
    public SurveyEscalation findById(Integer id) {
        return surveyEscalationMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean update(SurveyEscalation surveyEscalation) {
        int row = surveyEscalationMapper.updateByPrimaryKeySelective(surveyEscalation);
        if (row > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            surveyEscalationMapper.deleteByPrimaryKey(id);
        }
        return true;
    }

    @Override
    public List<String> getProjectName() {
        return surveyEscalationMapper.getProjectName();
    }
}
