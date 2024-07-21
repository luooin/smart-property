package com.es.estatemanagement.service.impl;

import com.es.estatemanagement.domain.Area;
import com.es.estatemanagement.mapper.AreaMapper;
import com.es.estatemanagement.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    AreaMapper areaMapper;

    @Override
    public List<Area> findByAreaId() {
        return areaMapper.findByAreaId();
    }
}
