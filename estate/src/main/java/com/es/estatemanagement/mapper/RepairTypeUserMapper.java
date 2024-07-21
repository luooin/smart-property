package com.es.estatemanagement.mapper;

import com.es.estatemanagement.domain.RepairTypeUser;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.sql.Timestamp;

public interface RepairTypeUserMapper extends Mapper<RepairTypeUser> {

    @Select({
            "<script>",
            "select type_user_id,repair_type,staff_id,staff_name,community_id,status_cd,remark,create_time " +
                    "from tb_repair_type_user " +
                    "where type_user_id = #{typeUserId}",
            "</script>"
    })
    @Results({
            @Result(property = "typeUserId",column = "type_user_id",javaType = String.class),
            @Result(property = "repairType",column = "repair_type",javaType = String.class),
            @Result(property = "staffId",column = "staff_id",javaType = String.class),
            @Result(property = "staffName",column = "staff_name",javaType = String.class),
            @Result(property = "communityId",column = "community_id",javaType = Integer.class),
            @Result(property = "statusCd",column = "status_cd",javaType = String.class),
            @Result(property = "remark",column = "remark",javaType = String.class),
            @Result(property = "createTime",column = "create_time",javaType = Timestamp.class),
    })
    public RepairTypeUser findById(@Param("typeUserId")String typeUserId);

    @Update({
            "<script>",
            "update tb_repair_type_user " +
                    "set status_cd = #{statusCd},remark = #{remark} " +
                    "where type_user_id = #{typeUserId}",
            "</script>"
    })
    @Results({
            @Result(property = "status_cd",column = "statusCd",javaType = String.class),
            @Result(property = "type_user_id",column = "typeUserId",javaType = String.class),
            @Result(property = "remark",column = "remark",javaType = String.class),
    })
    public Integer updateByTypeUserId(RepairTypeUser repairTypeUser);

    @Delete({
            "delete FROM tb_repair_type_user WHERE type_user_id=#{typeUserId}"
    })
    public Integer deleteByTypeUserId(String typeUserId);
}
