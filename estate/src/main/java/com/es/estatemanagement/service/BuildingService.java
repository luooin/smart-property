package com.es.estatemanagement.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.es.estatemanagement.domain.Building;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface BuildingService extends IService<Building> {

    public List<Building> findAll();

    public IPage<Building> search(Map searchMap);

    public Integer findByName(String communityName, String name);

    public Boolean add(Building building);

    public Building findById(Integer id);

    public Boolean update(Building building);

    public Boolean del(List<Integer> ids);

    public List<Building> findByCommunityName();

    public List<Building> findByName(String communityName);

    public Integer findCount(String communityName);

    public Integer findSum(String communityName);

}
