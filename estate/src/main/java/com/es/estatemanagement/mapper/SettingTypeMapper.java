package com.es.estatemanagement.mapper;

import com.es.estatemanagement.domain.SettingType;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SettingTypeMapper extends Mapper<SettingType> {

    //查询报修设置类型
    @Select("select repair_setting_type_id,repair_setting_type_name from tb_setting_type")
    public List<SettingType> findBySettingType();

}
