package com.es.estatemanagement.service;

import com.es.estatemanagement.domain.Car;
import com.es.estatemanagement.domain.Parking;
import com.es.estatemanagement.domain.ParkingApplication;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface ParkingApplicationService {

    public List<ParkingApplication> findAll();

    public Page<ParkingApplication> search(Map searchMap);

    public Boolean add(ParkingApplication parkingApplication);

    public ParkingApplication findById(Integer id);

    public Boolean update(ParkingApplication parkingApplication);

    public Boolean del(List<Long> ids);



    //查询车位状态
    public Parking parkingStarusBiz(Integer id);
    //修改车位状态
    public int updateStatusBiz(Parking parking);
    //业主名称查询id
    public Car carOwnerIdBiz(String carNumber);
    //车位名称查询id
    public Parking parkingNameBiz(String name);

}
