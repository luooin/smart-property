package com.es.estatemanagement.service;
import com.es.estatemanagement.domain.Province;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface ProvinceService {

    public List<Province> findAll();

    public Page<Province> search(Map searchMap);

    public Boolean add(Province province);

    public Province findById(Integer id);

    public Boolean update(Province province);

    public Boolean del(List<Integer> ids);

}
