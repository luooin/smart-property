package com.es.estatemanagement.service;

import com.es.estatemanagement.domain.Depot;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface DepotService {
    public List<Depot> findAll();

    public Page<Depot> search(Map searchMap);

    public Boolean add(Depot depot);

    public Depot findById(Integer id);

    public Boolean update(Depot depot);

    public Boolean del(List<Integer> ids);

    public Depot myDepotIdBiz(Integer id);
}
