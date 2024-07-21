package com.es.estatemanagement.service.impl;

import com.es.estatemanagement.domain.Dict;
import com.es.estatemanagement.mapper.DictMapper;
import com.es.estatemanagement.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictServiceImpl implements DictService {

    @Autowired
    DictMapper dictMapper;

    @Override
    public List<Dict> findByStatusName(String statusName) {
        return dictMapper.findByStatusName(statusName);
    }

    @Override
    public List<Dict> findByStatusCd() {
        return dictMapper.findByStatusCd();
    }
}
