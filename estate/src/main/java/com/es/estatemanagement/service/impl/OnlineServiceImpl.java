package com.es.estatemanagement.service.impl;

import com.es.estatemanagement.domain.Online;
import com.es.estatemanagement.mapper.OnlineMapper;
import com.es.estatemanagement.service.OnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OnlineServiceImpl implements OnlineService {

    @Autowired
    OnlineMapper onlineMapper;

    @Override
    public List<Online> findByLineId() {
        return onlineMapper.findByLineId();
    }
}
