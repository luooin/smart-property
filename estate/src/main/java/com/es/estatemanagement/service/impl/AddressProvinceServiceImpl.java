package com.es.estatemanagement.service.impl;


import com.es.estatemanagement.domain.AddressProvince;
import com.es.estatemanagement.mapper.AddressProvinceMapper;
import com.es.estatemanagement.service.AddressProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressProvinceServiceImpl implements AddressProvinceService {
    @Autowired
    AddressProvinceMapper addressProvinceMapper;

    @Override
    public List<AddressProvince> findAll() {
        List<AddressProvince> addressProvinces = addressProvinceMapper.selectAll();
        return addressProvinces;
    }

    @Override
    public String findByCode(String code) {
        return addressProvinceMapper.findByCode(code);
    }

}
