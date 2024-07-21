package com.es.estatemanagement.mapper;

import com.es.estatemanagement.domain.AddressTown;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface AddressTownMapper extends Mapper<AddressTown> {

    @Select("select * from tb_address_town where city_code=#{cityCode}")
    public List<AddressTown> findByCityCode(String cityCode);

    @Select("select name from tb_address_town where code=#{code}")
    public String findByCode(String code);

}
