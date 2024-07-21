package com.es.estatemanagement.service;

import com.es.estatemanagement.domain.Backlist;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface BacklistService {
    public List<Backlist> findAll();

    public Page<Backlist> search(Map searchMap);

    public Boolean add(Backlist backlist);

    public Backlist findById(Integer id);

    public Boolean update(Backlist backlist);

    public Boolean del(List<Long> ids);
}
