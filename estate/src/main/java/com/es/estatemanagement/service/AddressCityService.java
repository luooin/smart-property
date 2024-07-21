package com.es.estatemanagement.service;

import com.es.estatemanagement.domain.AddressCity;

import java.util.List;

public interface AddressCityService {

    public List<AddressCity> findByProvinceCode(String provinceCode);

    public String findByCode(String code);
}
