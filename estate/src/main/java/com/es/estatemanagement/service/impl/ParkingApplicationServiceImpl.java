package com.es.estatemanagement.service.impl;

import com.es.estatemanagement.domain.Car;
import com.es.estatemanagement.domain.Parking;
import com.es.estatemanagement.domain.ParkingApplication;
import com.es.estatemanagement.mapper.ParkingApplicationMapper;
import com.es.estatemanagement.service.ParkingApplicationService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ParkingApplicationServiceImpl implements ParkingApplicationService {

    @Resource
    ParkingApplicationMapper parkingApplicationMapper;


    @Override
    public List<ParkingApplication> findAll() {
        return parkingApplicationMapper.selectAll();
    }

    @Override
    public Page<ParkingApplication> search(Map searchMap) {
        //通用Mapper多条件搜索，通用写法
        Example example = new Example(ParkingApplication.class);   //指定查询的表tb_parking_application
        //初始化分页条件
        int pageNum = 1;
        int pageSize = 5;
        if(example != null){
            Example.Criteria criteria = example.createCriteria();   //创建查询条件
            //根据车牌号模糊查询
            if(StringUtil.isNotEmpty((String) searchMap.get("carCode"))){
                criteria.andLike("carCode","%"+(String)searchMap.get("carCode")+"%");
            }
            //根据申请人名称模糊查询
            if(StringUtil.isNotEmpty((String) searchMap.get("proposer"))){
                criteria.andLike("proposer","%"+(String)searchMap.get("proposer")+"%");
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
        Page<ParkingApplication> parkingApplicationPage = (Page<ParkingApplication>)parkingApplicationMapper.selectByExample(example);
        return parkingApplicationPage;
    }

    @Override
    public Boolean add(ParkingApplication parkingApplication) {
        int row = parkingApplicationMapper.insert(parkingApplication);
        if(row > 0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ParkingApplication findById(Integer id) {
        return parkingApplicationMapper.myParkingApplication(id);
    }

    @Override
    public Boolean update(ParkingApplication parkingApplication) {
        int row = parkingApplicationMapper.updateByPrimaryKeySelective(parkingApplication);
        return row > 0;
    }

    @Override
    public Boolean del(List<Long> ids) {
        for (Long id : ids) {
            parkingApplicationMapper.deleteByPrimaryKey(id);
        }
        return true;
    }




    @Override
    public Parking parkingStarusBiz(Integer id) {
        return parkingApplicationMapper.parkingStarus(id);
    }

    @Override
    public int updateStatusBiz(Parking parking) {
        return parkingApplicationMapper.updateStatus(parking);  
    }

    @Override
    public Car carOwnerIdBiz(String carNumber) {
        return parkingApplicationMapper.carOwnerId(carNumber);
    }

    @Override
    public Parking parkingNameBiz(String name) {
        return parkingApplicationMapper.parkingName(name);
    }

}
