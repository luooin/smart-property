package com.es.estatemanagement.service.impl;

import com.es.estatemanagement.domain.AddressTown;
import com.es.estatemanagement.mapper.AddressTownMapper;
import com.es.estatemanagement.service.AddressTownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressTownServiceImpl implements AddressTownService {

    @Autowired
    AddressTownMapper addressTownMapper;

    @Override
    public List<AddressTown> findByCityCode(String cityCode) {
        return addressTownMapper.findByCityCode(cityCode);
    }

    @Override
    public String findByCode(String code) {
        return addressTownMapper.findByCode(code);
    }

}
