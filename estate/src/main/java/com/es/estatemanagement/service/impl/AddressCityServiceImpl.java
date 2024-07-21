package com.es.estatemanagement.service.impl;

import com.es.estatemanagement.domain.AddressCity;
import com.es.estatemanagement.mapper.AddressCityMapper;
import com.es.estatemanagement.service.AddressCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressCityServiceImpl implements AddressCityService {

    @Autowired
    AddressCityMapper addressCityMapper;

    @Override
    public List<AddressCity> findByProvinceCode(String provinceCode) {
        return addressCityMapper.findByProvinceCode(provinceCode);
    }

    @Override
    public String findByCode(String code) {
        return addressCityMapper.findByCode(code);
    }

}
