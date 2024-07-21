package com.es.estatemanagement.service.impl;

import com.es.estatemanagement.domain.Dictionary;
import com.es.estatemanagement.mapper.DictionaryMapper;
import com.es.estatemanagement.service.DictionaryService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Resource
    DictionaryMapper dictionaryMapper;


    @Override
    public List<Dictionary> findDictionaryBiz() {
        return dictionaryMapper.findDictionary();
    }

    @Override
    public Page<Dictionary> search(Map searchMap) {
        //通用Mapper多条件搜索，通用写法
        Example example = new Example(Dictionary.class);   //指定查询的表tb_depot
        //初始化分页条件
        int pageNum = 1;
        int pageSize = 5;
        if(example != null){
            Example.Criteria criteria = example.createCriteria();   //创建查询条件
            // 时间区间
            if(StringUtil.isNotEmpty((String) searchMap.get("startTime"))){
                criteria.andGreaterThanOrEqualTo("liveTime",searchMap.get("startTime"));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("endTime"))){
                criteria.andLessThanOrEqualTo("liveTime",searchMap.get("endTime"));
                criteria.andLessThanOrEqualTo("liveTime",searchMap.get("endTime"));
            }
            //根据字典父节点显示
            if(StringUtil.isNotEmpty((String) searchMap.get("parentId"))){
                criteria.andEqualTo("parentId",1);
            }

            //根据字典名称模糊查询
            if(StringUtil.isNotEmpty((String) searchMap.get("dictionaryName"))){
                criteria.andLike("dictionaryName","%"+(String)searchMap.get("dictionaryName")+"%");
            }
            //分页
            if((Integer)searchMap.get("pageNum") != null){
                pageNum = (Integer) searchMap.get("pageNum");
            }

            if((Integer)searchMap.get("pageSize") != null){
                pageSize = (Integer) searchMap.get("pageSize");
            }
        }
        //使用PageHelper插件完成分页
        PageHelper.startPage(pageNum,pageSize);
        Page<Dictionary> dictionaryPage = (Page<Dictionary>) dictionaryMapper.selectByExample(example);
        return dictionaryPage;
    }


    @Override
    public Boolean add(Dictionary dictionary) {
        dictionary.setCreationTime(new Date());
        int row = dictionaryMapper.insert(dictionary);
        if(row > 0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Dictionary findById(Integer id) {
        return dictionaryMapper.findById(id);
    }

    @Override
    public Boolean update(Dictionary dictionary) {
        int row = dictionaryMapper.updateByPrimaryKeySelective(dictionary);
        if(row > 0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean del(List<Long> ids) {
        for (Long id : ids) {
            dictionaryMapper.del(id);
        }
        return true;
    }

    @Override
    public Boolean updateBiz(Dictionary dictionary) {
        int row = dictionaryMapper.update(dictionary);
        if(row > 0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean delBiz(List<Long> dictionaryId) {
        for (Long id:dictionaryId) {
            dictionaryMapper.deleteByPrimaryKey(id);
        }
        return true;
    }

    @Override
    public List<Dictionary> findByParentIdBiz(String dictionaryType) {
        return dictionaryMapper.findByParentId(dictionaryType);
    }


}
