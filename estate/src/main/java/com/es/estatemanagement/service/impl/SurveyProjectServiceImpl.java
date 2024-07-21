package com.es.estatemanagement.service.impl;

import com.es.estatemanagement.domain.SurveyProject;
import com.es.estatemanagement.mapper.SurveyAnswersMapper;
import com.es.estatemanagement.mapper.SurveyDataMapper;
import com.es.estatemanagement.mapper.SurveyProjectMapper;
import com.es.estatemanagement.service.SurveyProjectService;
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
public class SurveyProjectServiceImpl  implements SurveyProjectService {

    @Autowired
    SurveyProjectMapper surveyProjectMapper;

    @Autowired
    SurveyDataMapper surveyDataMapper;

    @Autowired
    SurveyAnswersMapper surveyAnswersMapper;

    @Override
    public List<SurveyProject> findAll() {
        List<SurveyProject> surveyProjects = surveyProjectMapper.selectAll();
        return surveyProjects;
    }

    @Override
    public Page<SurveyProject> search(Map searchMap) {
        //通用Mapper多条件搜索，标准写法
        Example example = new Example(SurveyProject.class);//指定查询的表tb_community
        //1.初始化分页条件
        int pageNum = 1;
        int pageSize = 3;
        if(searchMap != null){
            Example.Criteria criteria = example.createCriteria();//创建查询条件
            //时间区间
            if(StringUtil.isNotEmpty((String) searchMap.get("startTime"))){
                criteria.andGreaterThanOrEqualTo("startTime",searchMap.get("startTime"));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("endTime"))){
                criteria.andLessThanOrEqualTo("terminationTime",searchMap.get("endTime"));
                criteria.andLessThanOrEqualTo("terminationTime",searchMap.get("endTime"));
            }

            //名称模糊搜索
            if(StringUtil.isNotEmpty((String) searchMap.get("name"))){
                criteria.andLike("projectName", "%"+(String) searchMap.get("name")+"%");
            }

            if((Integer) searchMap.get("pageNum") !=null){
                pageNum = (Integer) searchMap.get("pageNum");
            }
            if((Integer) searchMap.get("pageSize") !=null){
                pageSize = (Integer) searchMap.get("pageSize");
            }
        }
        PageHelper.startPage(pageNum,pageSize);//使用PageHelper插件完成分页
        Page<SurveyProject> surveyProjectList = (Page<SurveyProject>) surveyProjectMapper.selectByExample(example);
        return surveyProjectList;
    }

    @Override
    public Boolean add(SurveyProject surveyProject) {
        int row = surveyProjectMapper.insert(surveyProject);
        if(row>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public SurveyProject findById(Integer id) {
        return surveyProjectMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean update(SurveyProject surveyProject) {
        int row = surveyProjectMapper.updateByPrimaryKeySelective(surveyProject);
        if(row>0){
            return true;
        }else{
            return false;
        }
    }

    @Transactional
    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id:ids) {
            List<Integer> answerId=surveyAnswersMapper.findBySurveyId(id);
            for (Integer answer:answerId) {
                surveyAnswersMapper.deleteByPrimaryKey(answer); //先删除问卷题目选项
            }
            List<Integer> dataID=surveyDataMapper.findBySurveyId(id);
            for (Integer data:dataID) {
                surveyDataMapper.deleteByPrimaryKey(data);      //在删除问卷题目
            }
            surveyProjectMapper.deleteByPrimaryKey(id);         //最后在删除问卷项目
        }
        return true;
    }

    @Override
    public List<String> projectName() {
        return surveyProjectMapper.projectName();
    }

    @Override
    public SurveyProject findByProjectName(String projectName) {
        return surveyProjectMapper.findByProjectName(projectName);
    }
}
