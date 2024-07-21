package com.es.estatemanagement.mapper;


import com.es.estatemanagement.domain.Car;
import com.es.estatemanagement.domain.CarBrand;
import com.es.estatemanagement.domain.CarType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CarMapper extends Mapper<Car> {


    //车辆类型所有信息
    @Select("SELECT * FROM tb_car_type")
    public List<CarType>carTypeList();
    //车辆品牌所有信息
    @Select("SELECT * FROM tb_car_brand;")
    public List<CarBrand>carBrandList();



}
