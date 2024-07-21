package com.es.estatemanagement.service.impl;

import com.es.estatemanagement.domain.ReturnVisit;
import com.es.estatemanagement.mapper.ReturnVisitMapper;
import com.es.estatemanagement.service.ReturnVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnVisitServiceImpl implements ReturnVisitService {

    @Autowired
    ReturnVisitMapper returnVisitMapper;

    @Override
    public List<ReturnVisit> findByVisitId() {
        return returnVisitMapper.findByVisitId();
    }
}
