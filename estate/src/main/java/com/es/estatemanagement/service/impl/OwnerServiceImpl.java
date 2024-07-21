package com.es.estatemanagement.service.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.es.estatemanagement.domain.House;
import com.es.estatemanagement.domain.Owner;
import com.es.estatemanagement.mapper.HouseMapper;
import com.es.estatemanagement.mapper.OwnerMapper;
import com.es.estatemanagement.service.OwnerService;
import com.es.estatemanagement.util.JWTUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;
import java.util.Map;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    OwnerMapper ownerMapper;

    @Override
    public List<Owner> findAll(String communityName) {
        List<Owner> buildings = ownerMapper.findByOwner(communityName);
        return buildings;
    }

    @Override
    public Page<Owner> search(Map searchMap) {
        //通用Mapper多条件搜索，标准写法
        Example example = new Example(Owner.class);//指定查询的表tb_community
        //1.初始化分页条件
        int pageNum = 1;
        int pageSize = 2;
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
            if (StringUtil.isNotEmpty((String) searchMap.get("communityName"))) {
                criteria.andEqualTo("communityName", (String) searchMap.get("communityName"));
            }
            //名称模糊搜索
            if (StringUtil.isNotEmpty((String) searchMap.get("name"))) {
                criteria.andLike("name", "%" + (String) searchMap.get("name") + "%");
            }
            //分页
            /*if(StringUtil.isNotEmpty((String) searchMap.get("pageNum"))){
                pageNum = Integer.parseInt((String) searchMap.get("pageNum"));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("pageSize"))){
                pageSize = Integer.parseInt((String) searchMap.get("pageSize"));
            }*/
            if ((Integer) searchMap.get("pageNum") != null) {
                pageNum = (Integer) searchMap.get("pageNum");
            }
            if ((Integer) searchMap.get("pageSize") != null) {
                pageSize = (Integer) searchMap.get("pageSize");
            }
        }
        PageHelper.startPage(pageNum, pageSize);//使用PageHelper插件完成分页
        Page<Owner> ownerList = (Page<Owner>) ownerMapper.selectByExample(example);
        return ownerList;
    }

    @Override
    public Integer findByName(String name) {
        return ownerMapper.findByName(name);
    }

    @Override
    public Boolean add(Owner owner) {
        owner.setCreateTime(new java.util.Date());
        owner.setUpdateTime(new java.util.Date());
        int row = ownerMapper.insert(owner);

        return row > 0;
    }

    @Override
    public Owner findById(Integer id) {
        return ownerMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean update(Owner owner) {
        owner.setUpdateTime(new java.util.Date());

        int row = ownerMapper.updateByPrimaryKeySelective(owner);
        return row > 0;
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            ownerMapper.deleteByPrimaryKey(id);
        }
        return true;
    }

    @Override
    public Integer findCount(String communityName) {
        return ownerMapper.findCount(communityName);
    }

    @Override
    public Integer findCounts(String communityName) {
        return ownerMapper.findCounts(communityName);
    }

}
