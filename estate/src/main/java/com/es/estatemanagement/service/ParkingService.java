package com.es.estatemanagement.service;

import com.es.estatemanagement.domain.Car;
import com.es.estatemanagement.domain.Parking;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface ParkingService {

    public List<Parking> findAll();

    public Page<Parking> search(Map searchMap);

    public Boolean add(Parking parking);

    public Parking findById(Integer id);

    public Boolean update(Parking parking);

    public Boolean del(List<Integer> ids);

    public List<Parking> findByCommunityName();


    //根据车牌号查询信息
    public Car carNumberBiz(String carNumber);


    //动态图显示车位
    public List<Parking>  quantityBiz(String communityName);
}
