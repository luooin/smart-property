package com.es.estatemanagement.service.impl;

import com.es.estatemanagement.domain.Car;
import com.es.estatemanagement.domain.CarBrand;
import com.es.estatemanagement.domain.CarType;
import com.es.estatemanagement.mapper.CarMapper;
import com.es.estatemanagement.mapper.OwnerMapper;
import com.es.estatemanagement.service.CarService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;
import java.util.Map;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarMapper carMapper;

    @Autowired
    OwnerMapper ownerMapper;

    @Override
    public List<Car> findAll() {
        List<Car> car = carMapper.selectAll();
        return car;
    }

    @Override
    public Page<Car> search(Map searchMap) {
        //通用Mapper多条件搜索，标准写法
        Example example = new Example(Car.class);//指定查询的表tb_community
        //1.初始化分页条件
        int pageNum = 1;
        int pageSize = 5;
        if(searchMap != null){
            Example.Criteria criteria = example.createCriteria();//创建查询条件
            //时间区间
            if(StringUtil.isNotEmpty((String) searchMap.get("startTime"))){
                criteria.andGreaterThanOrEqualTo("createTime",searchMap.get("startTime"));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("endTime"))){
                criteria.andLessThanOrEqualTo("createTime",searchMap.get("endTime"));
                criteria.andLessThanOrEqualTo("createTime",searchMap.get("endTime"));
            }
            if(StringUtil.isNotEmpty((String) searchMap.get("communityName"))){
                criteria.orIn("ownerId",ownerMapper.findByCommunityId((String) searchMap.get("communityName")));
            }
            //名称模糊搜索
            if(StringUtil.isNotEmpty((String) searchMap.get("name"))){
                criteria.andLike("carNumber", "%"+(String) searchMap.get("name")+"%");
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
        Page<Car> carList = (Page<Car>) carMapper.selectByExample(example);
        return carList;
    }

    @Override
    public Boolean add(Car car) {
        car.setCreateTime(new java.util.Date());
        car.setUpdateTime(new java.util.Date());
        int row = carMapper.insert(car);
        return row > 0;
    }

    @Override
    public Car findById(Integer id) {
        return carMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean update(Car car) {
        car.setUpdateTime(new java.util.Date());
        int row = carMapper.updateByPrimaryKeySelective(car);
        if(row>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id:ids) {
            carMapper.deleteByPrimaryKey(id);
        }
        return true;
    }

    @Override
    public List<CarType> carTypeListBiz() {
        return carMapper.carTypeList();
    }

    @Override
    public List<CarBrand> carBrandListBiz() {
        return carMapper.carBrandList();
    }
}
