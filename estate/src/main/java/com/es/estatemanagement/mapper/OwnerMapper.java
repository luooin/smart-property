package com.es.estatemanagement.mapper;

import com.es.estatemanagement.domain.Owner;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OwnerMapper extends Mapper<Owner> {

    @Select(" select id from tb_owner where `name`=#{name}")
    public Integer findByName(String name);

    @Select(" select * from tb_owner where `community_name`=#{communityName} ")
    public List<Owner> findByOwner(String communityName);

    @Select("select id from tb_owner where `community_name`=#{communityName}")
    public List<String> findByCommunityId(String communityName);

    @Select("select COUNT(1) from tb_owner where community_name=#{communityName}")
    public Integer findCount(String communityName);

    @Select("select COUNT(1) from tb_owner where community_name=#{communityName} and type=2")
    public Integer findCounts(String communityName);

}
