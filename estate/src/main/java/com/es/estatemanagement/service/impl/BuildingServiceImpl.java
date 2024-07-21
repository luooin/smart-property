package com.es.estatemanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.es.estatemanagement.domain.Community;
import com.es.estatemanagement.mapper.BuildingMapper;
import com.es.estatemanagement.domain.Building;
import com.es.estatemanagement.mapper.CommunityMapper;
import com.es.estatemanagement.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class BuildingServiceImpl extends ServiceImpl<BuildingMapper, Building> implements BuildingService {

    @Autowired
    CommunityMapper communityMapper;


    @Override
    public List<Building> findAll() {
        List<Building> buildings = baseMapper.selectList(null);
        return buildings;
    }

    @Override
    public IPage<Building> search(Map searchMap) {
        //通用Mapper多条件搜索，标准写法
//        Example example = new Example(Building.class);//指定查询的表tb_community
        //1.初始化分页条件
        int pageNum = 1;
        int pageSize = 3;

        QueryWrapper<Building> wrapper = new QueryWrapper<>();
        if (searchMap != null) {

            if (StringUtil.isNotEmpty((String) searchMap.get("startTime"))) {
                wrapper.ge("create_time", searchMap.get("startTime"));
            }

            if (StringUtil.isNotEmpty((String) searchMap.get("endTime"))) {
                wrapper.le("create_time", searchMap.get("endTime"));
            }

            if (StringUtil.isNotEmpty((String) searchMap.get("communityName"))) {
                wrapper.eq("community_name", (String) searchMap.get("communityName"));
            }

            if (StringUtil.isNotEmpty((String) searchMap.get("name"))) {
                wrapper.like("name", searchMap.get("name"));
            }

            if ((Integer) searchMap.get("pageNum") != null) {
                pageNum = (Integer) searchMap.get("pageNum");
            }
            if ((Integer) searchMap.get("pageSize") != null) {
                pageSize = (Integer) searchMap.get("pageSize");
            }
        }
//        PageHelper.startPage(pageNum, pageSize);//使用PageHelper插件完成分页
        Page<Building> page = new Page<>(pageNum, pageSize);
        IPage<Building> buildings = baseMapper.selectPage(page, wrapper);
        return buildings;
    }

    @Override
    public Integer findByName(String communityName, String name) {

        return baseMapper.findByCommunityNameAndName(communityName, name);
    }

    @Override
    public Boolean add(Building building) {
        building.setCreateTime(new Date());
        building.setUpdateTime(new Date());
        int row = baseMapper.insert(building);
        if (row > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Building findById(Integer id) {
        return baseMapper.selectById(id);
    }

    @Override
    public Boolean update(Building building) {
        building.setUpdateTime(new Date());
        int row = baseMapper.updateById(building);
        if (row > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            baseMapper.deleteById(id);
        }
        return true;
    }

    @Override
    public List<Building> findByCommunityName() {
        return baseMapper.findByCommunityName();
    }

    @Override
    public List<Building> findByName(String communityName) {
        return baseMapper.findByName(communityName);
    }

    @Transactional
    @Override
    public Integer findCount(String communityName) {
        Integer totalBuildings = baseMapper.findCount(communityName);
        Example example = new Example(Community.class);//指定查询的表tb_community
        Example.Criteria criteria = example.createCriteria();//创建查询条件
        criteria.andEqualTo("name", communityName);
        Community community = communityMapper.selectOneByExample(example);
        community.setTotalBuildings(totalBuildings);
        communityMapper.updateByPrimaryKeySelective(community);
        return baseMapper.findCount(communityName);
    }

    @Transactional
    @Override
    public Integer findSum(String communityName) {
        Integer totalHouseholds = baseMapper.findSum(communityName);
        Example example = new Example(Community.class);//指定查询的表tb_community
        Example.Criteria criteria = example.createCriteria();//创建查询条件
        criteria.andEqualTo("name", communityName);
        Community community = communityMapper.selectOneByExample(example);
        community.setTotalHouseholds(totalHouseholds);
        communityMapper.updateByPrimaryKeySelective(community);
        return baseMapper.findSum(communityName);
    }


}
