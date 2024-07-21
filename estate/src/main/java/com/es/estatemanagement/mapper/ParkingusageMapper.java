package com.es.estatemanagement.mapper;

import com.es.estatemanagement.domain.Parkingusage;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface ParkingusageMapper extends Mapper<Parkingusage> {

    @Select("SELECT * FROM tb_parking_use WHERE id=#{id}")
    public Parkingusage findParkingusageId(Integer id);


}
