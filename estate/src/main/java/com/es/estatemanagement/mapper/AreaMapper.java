package com.es.estatemanagement.mapper;

import com.es.estatemanagement.domain.Area;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AreaMapper extends Mapper<Area> {
    //查询是否是公共区域
    @Select("select area_id,area_flag from tb_repair_area")
    public List<Area> findByAreaId();

}
