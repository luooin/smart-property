package com.es.estatemanagement.service.impl;

import com.es.estatemanagement.domain.Parkingusage;
import com.es.estatemanagement.mapper.ParkingusageMapper;
import com.es.estatemanagement.service.ParkingusageService;
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
public class ParkingusageServiceImpl implements ParkingusageService {


    @Resource
    ParkingusageMapper parkingusageMapper;


    @Override
    public List<Parkingusage> findAll() {
        return parkingusageMapper.selectAll();
    }

    @Override
    public Page<Parkingusage> search(Map searchMap) {
        //通用Mapper多条件搜索，通用写法
        Example example = new Example(Parkingusage.class);   //指定查询的表tb_parking
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
            //根据车牌号名称模糊查询
            if(StringUtil.isNotEmpty((String) searchMap.get("carNumber"))){
                criteria.andLike("carNumber","%"+(String)searchMap.get("carNumber")+"%");
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
        Page<Parkingusage> parkingusagePage = (Page<Parkingusage>) parkingusageMapper.selectByExample(example);
        return parkingusagePage;
    }

    @Override
    public Boolean add(Parkingusage parkingusage) {
        parkingusage.setCreateTime(new Date());
        parkingusage.setUpdateTime(new Date());
        int row = parkingusageMapper.insert(parkingusage);
        if(row > 0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Parkingusage findById(Integer id) {
        return parkingusageMapper.findParkingusageId(id);
    }

    @Override
    public Boolean update(Parkingusage parkingusage) {
        parkingusage.setUpdateTime(new Date());

        int row = parkingusageMapper.updateByPrimaryKeySelective(parkingusage);
        if(row > 0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            parkingusageMapper.deleteByPrimaryKey(id);
        }
        return true;
    }
}
