package com.es.estatemanagement.service.impl;

import com.es.estatemanagement.domain.Car;
import com.es.estatemanagement.domain.Parking;
import com.es.estatemanagement.mapper.ParkingMapper;
import com.es.estatemanagement.service.ParkingService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    ParkingMapper parkingMapper;

    /**
     * 查询所有
     * */
    @Override
    public List<Parking> findAll() {
        List<Parking> parkings = parkingMapper.selectAll();
        return parkings;
    }

    /**
     * 分页查询
     * */
    @Override
    public Page<Parking> search(Map searchMap) {
        //通用Mapper多条件搜索，通用写法
        Example example = new Example(Parking.class);   //指定查询的表tb_parking
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
            //根据车牌号模糊查询
            if(StringUtil.isNotEmpty((String) searchMap.get("code"))){
                criteria.andLike("code","%"+(String)searchMap.get("code")+"%");
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
        Page<Parking> parkingList = (Page<Parking>) parkingMapper.selectByExample(example);
        return parkingList;
    }

    @Override
    public Boolean add(Parking parking) {
        parking.setCreateTime(new Timestamp(System.currentTimeMillis()));
        parking.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        int row = parkingMapper.insert(parking);
        return row > 0;
    }

    @Override
    public Parking findById(Integer id) {
        return parkingMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean update(Parking parking) {
        parking.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        int row = parkingMapper.updateByPrimaryKeySelective(parking);
        return row > 0;
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            parkingMapper.deleteByPrimaryKey(id);
        }
        return true;
    }

    @Override
    public List<Parking> findByCommunityName() {
        return parkingMapper.findByCommunityName();
    }

    @Override
    public Car carNumberBiz(String carNumber) {
        Parking parking=parkingMapper.parkingCarCode(carNumber);
        Car car=parkingMapper.carNumber(carNumber);
        if (parking.getCarCode().equals(car.getCarNumber())){
            return car;
        }
        return null;

    }

    @Override
    public List<Parking>  quantityBiz(String communityName) {
        return parkingMapper.quantity(communityName);
    }
}
