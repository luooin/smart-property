package com.es.estatemanagement.service.impl;

import com.es.estatemanagement.domain.Backlist;
import com.es.estatemanagement.domain.Entry;
import com.es.estatemanagement.mapper.BacklistMapper;
import com.es.estatemanagement.service.BacklistService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class BacklistServiceImpl implements BacklistService {
    @Resource
    BacklistMapper backlistMapper;

    @Override
    public List<Backlist> findAll() {
        return backlistMapper.selectAll();
    }

    @Override
    public Page<Backlist> search(Map searchMap) {
        //通用Mapper多条件搜索，通用写法
        Example example = new Example(Entry.class);   //指定查询的表tb_depot
        //初始化分页条件
        int pageNum = 1;
        int pageSize = 5;
        if (example != null) {
        }
        Example.Criteria criteria = example.createCriteria();   //创建查询条件
        if (StringUtil.isNotEmpty((String) searchMap.get("carCode"))) {
            criteria.andLike("carCode", "%" + (String) searchMap.get("carCode") + "%");
        }

        //id和状态
        if ((Integer) searchMap.get("id") != null) {
            criteria.andCondition("id" ,(Integer) searchMap.get("id"));
        }

        //分页
        if ((Integer) searchMap.get("pageNum") != null) {
            pageNum = (Integer) searchMap.get("pageNum");
        }

        if ((Integer) searchMap.get("pageSize") != null) {
            pageSize = (Integer) searchMap.get("pageSize");
        }
        //使用PageHelper插件完成分页
        PageHelper.startPage(pageNum,pageSize);
        Page<Backlist> backlistPagelist = (Page<Backlist>) backlistMapper.selectByExample(example);
        return backlistPagelist;
    }

    @Override
    public Boolean add(Backlist backlist) {
        int row = backlistMapper.insert(backlist);
        if(row > 0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Backlist findById(Integer id) {
        return backlistMapper.myBackId(id);
    }

    @Override
    public Boolean update(Backlist backlist) {
        int row = backlistMapper.updateByPrimaryKeySelective(backlist);
        if(row > 0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean del(List<Long> ids) {
        for (Long id : ids) {
            backlistMapper.deleteByPrimaryKey(id);
        }
        return true;
    }
}
