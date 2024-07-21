package com.es.estatemanagement.mapper;

import com.es.estatemanagement.domain.AddressCity;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface AddressCityMapper extends Mapper<AddressCity> {

    @Select("select * from tb_address_city where province_code=#{provinceCode}")
    public List<AddressCity> findByProvinceCode(String provinceCode);

    @Select("select name from tb_address_city where code=#{code}")
    public String findByCode(String code);
}
