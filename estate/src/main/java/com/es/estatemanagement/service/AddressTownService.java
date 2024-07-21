package com.es.estatemanagement.service;

import com.es.estatemanagement.domain.AddressTown;

import java.util.List;

public interface AddressTownService {

    public List<AddressTown> findByCityCode(String cityCode);

    public String findByCode(String code);
}
