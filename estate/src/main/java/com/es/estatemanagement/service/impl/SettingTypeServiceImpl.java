package com.es.estatemanagement.service.impl;

import com.es.estatemanagement.domain.SettingType;
import com.es.estatemanagement.mapper.SettingTypeMapper;
import com.es.estatemanagement.service.SettingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingTypeServiceImpl implements SettingTypeService {

    @Autowired
    SettingTypeMapper settingTypeMapper;

    @Override
    public List<SettingType> findBySettingType() {
        return settingTypeMapper.findBySettingType();
    }
}
