package com.es.estatemanagement.mapper;

import com.es.estatemanagement.domain.Depot;
import com.es.estatemanagement.domain.Owner;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DepotMapper extends Mapper<Depot> {


    @Select("SELECT * FROM tb_depot WHERE id=#{id}")
    public Depot myDepotId(Integer id);


    @Select(" select id from tb_owner where `name`=#{name}")
    public Integer findByName(String name);

    @Select(" select * from tb_owner where `community_name`=#{communityName} ")
    public List<Owner> findByOwner(String communityName);

    @Select("select id from tb_owner where `community_name`=#{communityName}")
    public List<String> findByCommunityId(String communityName);

    @Delete("DELETE FROM tb_depot where `id`=#{id}")
    public void del(Integer id);

}
