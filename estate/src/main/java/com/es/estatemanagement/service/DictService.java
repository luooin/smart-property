package com.es.estatemanagement.service;

import com.es.estatemanagement.domain.Dict;

import java.util.List;

public interface DictService {

    public List<Dict> findByStatusName(String statusName);


    public List<Dict> findByStatusCd();

}
