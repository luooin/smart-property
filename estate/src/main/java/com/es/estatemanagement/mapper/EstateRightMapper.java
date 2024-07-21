package com.es.estatemanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.es.estatemanagement.domain.EstateRight;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EstateRightMapper extends BaseMapper<EstateRight> {


    @Select("<script>"+"select m.right_code,m.right_parent_code,m.right_type,m.right_text,m.right_url,m.right_tip,m.status,m.is_deleted,m.icon from tb_estate_right m \n" +
            "inner join tb_estate_role_right rm on m.right_code=rm.rf_right_code \n" +
            "inner join tb_estate_user_role ur on ur.role_id=rm.rf_role_id\n" +
            "where ur.user_id=(select id from tb_estate_manager where login_name=#{username})\n" +
            "and m.status = 1\n" +
            "and rm.is_deleted = 0\n" +
            "and ur.is_deleted = 0\n" +
            "and m.is_deleted = 0" +
            "<if test='rightType!=null and rightType!= \"\"'>" +
            "and r.right_type=#{rightType}" +
            "</if>"+"</script>")
    public List<EstateRight> findAllByEstateRight(Map map);


}
