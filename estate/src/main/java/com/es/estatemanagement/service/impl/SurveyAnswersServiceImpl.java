package com.es.estatemanagement.service.impl;

import com.es.estatemanagement.domain.SurveyAnswers;
import com.es.estatemanagement.domain.SurveyData;
import com.es.estatemanagement.mapper.SurveyAnswersMapper;
import com.es.estatemanagement.service.SurveyAnswersService;
import com.es.estatemanagement.service.SurveyDataService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;
import java.util.Map;

@Service
public class SurveyAnswersServiceImpl implements SurveyAnswersService {

    @Autowired
    SurveyAnswersMapper surveyAnswersMapper;

    @Override
    public List<SurveyAnswers> findAll() {
        List<SurveyAnswers> surveyAnswers = surveyAnswersMapper.selectAll();
        return surveyAnswers;
    }

    @Override
    public Page<SurveyAnswers> search(Map searchMap) {
        //通用Mapper多条件搜索，标准写法
        Example example = new Example(SurveyAnswers.class);//指定查询的表tb_community
        //1.初始化分页条件
        int pageNum = 1;
        int pageSize = 3;
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
                criteria.andLike("question", "%" + (String) searchMap.get("name") + "%");
            }

            if ((Integer) searchMap.get("pageNum") != null) {
                pageNum = (Integer) searchMap.get("pageNum");
            }
            if ((Integer) searchMap.get("pageSize") != null) {
                pageSize = (Integer) searchMap.get("pageSize");
            }
        }
        PageHelper.startPage(pageNum, pageSize);//使用PageHelper插件完成分页
        Page<SurveyAnswers> surveyAnswersList = (Page<SurveyAnswers>) surveyAnswersMapper.selectByExample(example);
        return surveyAnswersList;
    }

    @Override
    public List<SurveyAnswers> findByquestionId(Integer questionId) {
        return surveyAnswersMapper.findByquestionId(questionId);
    }


    @Transactional
    @Override
    public Boolean add(SurveyAnswers surveyAnswers) {
        int row = surveyAnswersMapper.insert(surveyAnswers);
        if (row > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public SurveyAnswers findById(Integer id) {
        return surveyAnswersMapper.selectByPrimaryKey(id);
    }


    @Override
    public Boolean update(SurveyAnswers surveyAnswers) {
        int row = surveyAnswersMapper.updateByPrimaryKeySelective(surveyAnswers);
        if (row > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            surveyAnswersMapper.deleteByPrimaryKey(id);
        }
        return true;
    }

    @Override
    public List<SurveyAnswers> findByQuestionIdInName(String projectName) {
        return surveyAnswersMapper.findByQuestionIdInName(projectName);
    }

}
