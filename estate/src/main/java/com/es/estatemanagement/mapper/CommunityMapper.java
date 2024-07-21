package com.es.estatemanagement.mapper;

import com.es.estatemanagement.domain.Community;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.common.Mapper;

@Repository
public interface CommunityMapper extends Mapper<Community> {

    @Select("select id from tb_community where `name`=#{name}")
    public Integer findByName(String name);

}
