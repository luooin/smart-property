package com.es.estatemanagement.service.impl;

import com.es.estatemanagement.domain.RepairTypeUser;
import com.es.estatemanagement.mapper.RepairTypeUserMapper;
import com.es.estatemanagement.service.RepairTypeUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * 维修师傅实现
 * */
@Service
public class RepairTypeUserServiceImpl implements RepairTypeUserService {

    @Autowired
    RepairTypeUserMapper repairTypeUserMapper;

    @Override
    public List<RepairTypeUser> findAll() {
        List<RepairTypeUser> typeUserList = repairTypeUserMapper.selectAll();
        return typeUserList;
    }

    @Override
    public Page<RepairTypeUser> search(Map searchMap) {
        //通用Mapper多条件搜索，标准写法
        Example example = new Example(RepairTypeUser.class);
        //初始化分页条件
        int pageNum = 1;
        int pageSize = 5;
        if (searchMap != null) {
            Example.Criteria criteria = example.createCriteria();   //创建查询条件
            //分页
            if ((Integer) searchMap.get("pageNum") != null) {
                pageNum = (Integer) searchMap.get("pageNum");
            }

            if ((Integer) searchMap.get("pageSize") != null) {
                pageSize = (Integer) searchMap.get("pageSize");
            }
        }
        PageHelper.startPage(pageNum, pageSize);
        Page<RepairTypeUser> typeUserList = (Page<RepairTypeUser>) repairTypeUserMapper.selectByExample(example);
        return typeUserList;
    }

    @Override
    public RepairTypeUser findByTypeUserId(String typeUserId) {
        return repairTypeUserMapper.findById(typeUserId);
    }

    @Override
    public Boolean update(RepairTypeUser repairTypeUser) {
        int row = repairTypeUserMapper.updateByTypeUserId(repairTypeUser);
        if(row > 0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean del(List<String> typeUserId) {
        for (String id : typeUserId) {
            repairTypeUserMapper.deleteByTypeUserId(id);
        }
        return true;
    }
}
