package com.es.estatemanagement.service.impl;

import com.es.estatemanagement.domain.House;
import com.es.estatemanagement.mapper.HouseMapper;
import com.es.estatemanagement.service.HouseService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;
import java.util.Map;

@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    HouseMapper houseMapper;

    @Override
    public List<House> findAll() {
        List<House> buildings = houseMapper.selectAll();
        return buildings;
    }

    @Override
    public Page<House> search(Map searchMap) {
        //通用Mapper多条件搜索，标准写法
        Example example = new Example(House.class);//指定查询的表tb_community
        //1.初始化分页条件
        int pageNum = 1;
        int pageSize = 3;
        if(searchMap != null){
            Example.Criteria criteria = example.createCriteria();//创建查询条件
            //时间区间
            if(StringUtil.isNotEmpty((String) searchMap.get("startTime"))){
                criteria.andGreaterThanOrEqualTo("liveTime",searchMap.get("startTime"));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("endTime"))){
                criteria.andLessThanOrEqualTo("liveTime",searchMap.get("endTime"));
                criteria.andLessThanOrEqualTo("liveTime",searchMap.get("endTime"));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("communityName"))){
                criteria.andEqualTo("communityName",(String) searchMap.get("communityName"));
            }
            //名称模糊搜索
            if(StringUtil.isNotEmpty((String) searchMap.get("name"))){
                criteria.andLike("name", "%"+(String) searchMap.get("name")+"%");
            }

            //分页
            /*if(StringUtil.isNotEmpty((String) searchMap.get("pageNum"))){
                pageNum = Integer.parseInt((String) searchMap.get("pageNum"));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("pageSize"))){
                pageSize = Integer.parseInt((String) searchMap.get("pageSize"));
            }*/
            if((Integer) searchMap.get("pageNum") !=null){
                pageNum = (Integer) searchMap.get("pageNum");
            }
            if((Integer) searchMap.get("pageSize") !=null){
                pageSize = (Integer) searchMap.get("pageSize");
            }
        }
        PageHelper.startPage(pageNum,pageSize);//使用PageHelper插件完成分页
        Page<House> homeList = (Page<House>) houseMapper.selectByExample(example);
        return homeList;
    }

    @Override
    public Boolean add(House community) {
        int row = houseMapper.insert(community);
        if(row>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public House findById(Integer id) {
        return houseMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean update(House building) {
        int row = houseMapper.updateByPrimaryKeySelective(building);
        if(row>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id:ids) {
            houseMapper.deleteByPrimaryKey(id);
        }
        return true;
    }
}
