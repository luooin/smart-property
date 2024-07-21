package com.es.estatemanagement.service;

import com.es.estatemanagement.domain.RepairSetting;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * 报修设置业务类
 * */
public interface RepairSettingService {
    public List<RepairSetting> findAll();

    public Page<RepairSetting> search(Map searchMap);

    public Boolean add(RepairSetting repairSetting);

    public RepairSetting findBySettingId(String SettingId);

    public Boolean update(RepairSetting repairSetting);

    public Boolean del(List<String> SettingIds);
}
