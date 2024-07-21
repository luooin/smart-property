package com.es.estatemanagement.service;

import com.es.estatemanagement.domain.RepairPool;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface RepairPoolService {
    public List<RepairPool> findAll();

    public Page<RepairPool> search(Map searchMap);

    public Boolean add(RepairPool repairPool);

    public RepairPool findByRepairId(String repairId);

    public Boolean update(RepairPool repairPool);

    public Boolean del(List<String> poolIds);
}
