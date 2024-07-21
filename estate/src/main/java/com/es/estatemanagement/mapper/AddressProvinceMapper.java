package com.es.estatemanagement.mapper;

import com.es.estatemanagement.domain.AddressCity;
import com.es.estatemanagement.domain.AddressProvince;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface AddressProvinceMapper extends Mapper<AddressProvince> {
    @Select("select name from tb_address_province where code=#{code}")
    public String findByCode(String code);
}
