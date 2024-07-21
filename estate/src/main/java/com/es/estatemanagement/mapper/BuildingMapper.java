package com.es.estatemanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.es.estatemanagement.domain.Building;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface BuildingMapper extends BaseMapper<Building> {

    public Integer findCount(String communityName);

    public Integer findSum(String communityName);

    @Select("select community_name,community_id from tb_building group by community_id,community_name")
    public List<Building> findByCommunityName();

    @Select("select name,count(total_households) as total_households from tb_building where community_name=#{communityName} and is_deleted=0 group by name ")
    public List<Building> findByName(String communityName);

    @Select("select id from tb_building where community_name=#{communityName} and `name`=#{name}")
    public Integer findByCommunityNameAndName(String communityName, String name);

}
