package com.es.estatemanagement.service;

import com.es.estatemanagement.domain.RepairTypeUser;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface RepairTypeUserService {
    public List<RepairTypeUser> findAll();

    public Page<RepairTypeUser> search(Map searchMap);

    public RepairTypeUser findByTypeUserId(String typeUserId);

    public Boolean update(RepairTypeUser repairTypeUser);

    public Boolean del(List<String> typeUserId);
}
