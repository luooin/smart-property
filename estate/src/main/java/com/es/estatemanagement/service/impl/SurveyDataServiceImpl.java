package com.es.estatemanagement.service.impl;

import com.es.estatemanagement.domain.SurveyAnswers;
import com.es.estatemanagement.domain.SurveyData;
import com.es.estatemanagement.mapper.SurveyAnswersMapper;
import com.es.estatemanagement.mapper.SurveyDataMapper;
import com.es.estatemanagement.service.SurveyDataService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SurveyDataServiceImpl implements SurveyDataService {

    @Autowired
    SurveyDataMapper surveyDataMapper;

    @Autowired
    SurveyAnswersMapper surveyAnswersMapper;

    @Override
    public List<SurveyData> findAll() {
        List<SurveyData> surveyData = surveyDataMapper.selectAll();
        return surveyData;
    }

    @Override
    public Page<SurveyData> search(Map searchMap) {
        //通用Mapper多条件搜索，标准写法
        Example example = new Example(SurveyData.class);//指定查询的表tb_community
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

            if (StringUtil.isNotEmpty((String) searchMap.get("surveyId"))) {
                criteria.andEqualTo("surveyId", searchMap.get("surveyId"));
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
        Page<SurveyData> surveyDataList = (Page<SurveyData>) surveyDataMapper.selectByExample(example);
        return surveyDataList;
    }


    @Transactional
    @Override
    public Boolean add(SurveyData surveyData){
        surveyData.setCreateTime(new Date());
        boolean bool = false;
        SurveyAnswers surveyAnswers = new SurveyAnswers();
        int row = surveyDataMapper.insert(surveyData);
        int questionId = surveyDataMapper.maxId();
        System.out.println(surveyData.getDomain());
        if (row > 0) {
            List<String> list = (List<String>) surveyData.getDomain();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) != "" && !list.get(i).equals("")) {
                    surveyAnswers.setQuestionId(questionId);
                    surveyAnswers.setChoiceText(list.get(i));
                    surveyAnswersMapper.insert(surveyAnswers);
                    bool = true;
                }
            }
        }
        if (bool) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public SurveyData findById(Integer id) {
        return surveyDataMapper.selectByPrimaryKey(id);
    }


    @Transactional
    @Override
    public Boolean update(SurveyData surveyData) {
        SurveyAnswers surveyAnswers = new SurveyAnswers();
        boolean bool = false;
        int row = surveyAnswersMapper.deleteByQuestionId(surveyData.getQuestionId()); //根据题目id删除选项
        int rows = 0;
        if (row > 0) {
            rows = surveyDataMapper.updateByPrimaryKeySelective(surveyData); //修改题目表信息
            if(rows>0){
                List<String> list = (List<String>) surveyData.getDomain();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) != "" && !list.get(i).equals("")) {
                        surveyAnswers.setQuestionId(surveyData.getQuestionId());
                        surveyAnswers.setChoiceText(list.get(i));
                        surveyAnswersMapper.insert(surveyAnswers); //添加题目选项
                        bool = true;
                    }
                }
            }
        }
        if (bool) {
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            List<Integer> answerId = surveyAnswersMapper.answerById(id);
            for (Integer idx : answerId) {
                surveyAnswersMapper.deleteByPrimaryKey(idx);
            }
            surveyDataMapper.deleteByPrimaryKey(id);
        }
        return true;
    }

    @Override
    public List<SurveyData> findBySurveyIdInName(String projectName) {
        return surveyDataMapper.findBySurveyIdInName(projectName);
    }
}
