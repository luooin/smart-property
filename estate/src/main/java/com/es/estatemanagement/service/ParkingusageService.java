package com.es.estatemanagement.service;

import com.es.estatemanagement.domain.Parkingusage;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface ParkingusageService {

    public List<Parkingusage> findAll();

    public Page<Parkingusage> search(Map searchMap);

    public Boolean add(Parkingusage parkingusage);

    public Parkingusage findById(Integer id);

    public Boolean update(Parkingusage parkingusage);

    public Boolean del(List<Integer> ids);
}
