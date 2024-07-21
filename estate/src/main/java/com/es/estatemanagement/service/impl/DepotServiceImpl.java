package com.es.estatemanagement.service.impl;

import com.es.estatemanagement.domain.Depot;
import com.es.estatemanagement.mapper.DepotMapper;
import com.es.estatemanagement.service.DepotService;
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
public class DepotServiceImpl implements DepotService {

    @Resource
    DepotMapper depotMapper;

    @Override
    public List<Depot> findAll() {
        return depotMapper.selectAll();
    }

    /**
     * 分页查询
     * */

    @Override
    public Page<Depot> search(Map searchMap) {
        //通用Mapper多条件搜索，通用写法
        Example example = new Example(Depot.class);   //指定查询的表tb_depot
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
            //根据停车场名称模糊查询
            if(StringUtil.isNotEmpty((String) searchMap.get("depotName"))){
                criteria.andLike("depotName","%"+(String)searchMap.get("depotName")+"%");
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
        Page<Depot> depotList = (Page<Depot>) depotMapper.selectByExample(example);
        return depotList;
    }

    @Override
    public Boolean add(Depot depot) {
        depot.setCreateTime(new Date());
        int row = depotMapper.insert(depot);

        if(row > 0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Depot findById(Integer id) {
        return depotMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean update(Depot depot) {
        int row = depotMapper.updateByPrimaryKeySelective(depot);
        if(row > 0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            depotMapper.del(id);
        }
        return true;
    }

    @Override
    public Depot myDepotIdBiz(Integer id) {
        return depotMapper.myDepotId(id);
    }
}
