package com.es.estatemanagement.mapper;

import com.es.estatemanagement.domain.RepairPool;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.sql.Timestamp;

/**
 * 电话报修
 * */
public interface RepairPoolMapper extends Mapper<RepairPool> {
    @Select({
            "<script>",
            "select repair_id,community_id,repair_type,repair_name,tel,appointment_time,context,create_time,status_cd," +
                    "send_orders_id,address,maintenance_type,repair_channel,repair_materials,repair_fee,pay_type " +
            "from tb_repair_pool " ,
            "where repair_id = #{repairId}",
            "</script>"
    })
    public RepairPool findById(@Param("repairId")String repairId);

    @Update({
            "<script>",
            "update tb_repair_pool ",
            "set community_id = #{communityId},repair_type = #{repairType},repair_name = #{repairName},tel = #{tel}," +
                    "appointment_time = #{appointmentTime},context = #{context},status_cd = #{statusCd}, " +
                    "send_orders_id = #{sendOrdersId},address = #{address}" ,
            "where repair_id = #{repairId}",
            "</script>"
    })
    @Results({
            @Result(property = "repairId",column = "repair_id",javaType = String.class),
            @Result(property = "communityId",column = "community_id",javaType = String.class),
            @Result(property = "repairType",column = "repair_type",javaType = Integer.class),
            @Result(property = "repairName",column = "repair_name",javaType = String.class),
            @Result(property = "tel",column = "tel",javaType = String.class),
            @Result(property = "appointmentTime",column = "appointment_time",javaType = String.class),
            @Result(property = "context",column = "context",javaType = String.class),
            @Result(property = "statusCd",column = "status_cd",javaType = String.class),
            @Result(property = "sendOrdersId",column = "send_orders_id",javaType = Timestamp.class),
            @Result(property = "repairName",column = "repair_name",javaType = String.class),
            @Result(property = "address",column = "address",javaType = String.class),
    })
    public Integer updateByRepairId(RepairPool repairPool);

    @Delete({
            "delete FROM tb_repair_pool WHERE repair_id=#{repairId}"
    })
    public Integer deleteByRepairId(@Param("repairId") String repairId);
}
