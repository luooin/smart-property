package com.es.estatemanagement.service;


import com.es.estatemanagement.domain.AddressProvince;

import java.util.List;

public interface AddressProvinceService {

    public List<AddressProvince> findAll();

    public String findByCode(String code);
}
