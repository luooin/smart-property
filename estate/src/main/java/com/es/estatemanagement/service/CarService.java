package com.es.estatemanagement.service;

import com.es.estatemanagement.domain.Car;
import com.es.estatemanagement.domain.CarBrand;
import com.es.estatemanagement.domain.CarType;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface CarService {
    public List<Car> findAll();

    public Page<Car> search(Map searchMap);

    public Boolean add(Car house);

    public Car findById(Integer id);

    public Boolean update(Car car);

    public Boolean del(List<Integer> ids);


    //车辆类型所有信息
    public List<CarType>carTypeListBiz();
    //车辆品牌所有信息
    public List<CarBrand>carBrandListBiz();
}
