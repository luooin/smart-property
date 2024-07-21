package com.es.estatemanagement.mapper;

import com.es.estatemanagement.domain.RepairSetting;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.sql.Timestamp;


public interface RepairSettingMapper extends Mapper<RepairSetting> {
    @Select({
            "<script>",
            "select setting_id,send_orders_type,repair_type,send_orders_id,community_id,remark,create_time,area_id,pay_fee_flag,price_scope,return_visit_id,repair_setting_type_id ",
            "from tb_repair_setting " ,
            "where setting_id = #{settingId}",
            "</script>"
    })
    @Results({
         @Result(property = "settingId",column = "setting_id",javaType = String.class),
         @Result(property = "sendOrdersType",column = "send_orders_type",javaType = String.class),
         @Result(property = "repairType",column = "repair_type",javaType = String.class),
         @Result(property = "sendOrdersId",column = "send_orders_id",javaType = String.class),
         @Result(property = "communityId",column = "community_id",javaType = Integer.class),
         @Result(property = "remark",column = "remark",javaType = String.class),
         @Result(property = "createTime",column = "create_time",javaType = Timestamp.class),
         @Result(property = "areaId",column = "area_id",javaType = String.class),
         @Result(property = "payFeeFlag",column = "pay_fee_flag",javaType = String.class),
         @Result(property = "priceScope",column = "price_scope",javaType = String.class),
         @Result(property = "returnVisitId",column = "return_visit_id",javaType = String.class),
         @Result(property = "repairSettingTypeId",column = "repair_setting_type_id",javaType = String.class),
    })
    public RepairSetting findById(@Param("settingId")String settingId);

    @Update({
            "update tb_repair_setting ",
            "set setting_id = #{settingId},send_orders_type = #{sendOrdersType},repair_type = #{repairType},community_id = #{communityId}," +
                    "repair_setting_type_id = #{repairSettingTypeId},send_orders_id = #{sendOrdersId},area_id = #{areaId}," +
                    "return_visit_id = #{returnVisitId},remark = #{remark} ",
            "where setting_id = #{settingId}",
    })
    @Results({
            @Result(property = "settingId",column = "setting_id",javaType = String.class),
            @Result(property = "sendOrdersType",column = "send_orders_type",javaType = String.class),
            @Result(property = "repairType",column = "repair_type",javaType = String.class),
            @Result(property = "communityId",column = "community_id",javaType = Integer.class),
            @Result(property = "repairSettingTypeId",column = "repair_setting_type_id",javaType = String.class),
            @Result(property = "sendOrdersId",column = "send_orders_id",javaType = String.class),
            @Result(property = "areaId",column = "area_id",javaType = String.class),
            @Result(property = "returnVisitId",column = "return_visit_id",javaType = String.class),
            @Result(property = "remark",column = "remark",javaType = String.class),
    })
    public Integer updateBySettingId(RepairSetting repairSetting);

    @Delete({
            "delete FROM tb_repair_setting WHERE setting_id=#{settingId}"
    })
    public Integer deleteBySettingId(String settingId);
}
