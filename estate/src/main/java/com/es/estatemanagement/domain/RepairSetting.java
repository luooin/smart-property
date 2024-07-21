package com.es.estatemanagement.domain;

import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 维修工单表
 * */
@Table(name = "tb_repair_setting")
public class RepairSetting {
    private String settingId;         //工单设置ID
    private String sendOrdersType;    //派单类型
    private String repairType;        //报修类型
    private String sendOrdersId;       //派单方式 tb_send_orders 1:抢单   2：派单  3：指派
    private Integer communityId;      //小区分片ID
    private String remark;           //备注
    private Timestamp createTime;    //创建时间
    private String areaId;       //是否为公共区域  T为是 F为否
    private String payFeeFlag;       //是否收费 T为是 F为否
    private String priceScope;       //收费范围，30元至200元
    private String returnVisitId;  //回访标识 001 都不回访 002 已评价不回访 003 都回访
    private String repairSettingTypeId; //报修设置类型编号

    public String getSettingId() {
        return settingId;
    }

    public void setSettingId(String settingId) {
        this.settingId = settingId;
    }

    public String getSendOrdersType() {
        return sendOrdersType;
    }

    public void setSendOrdersType(String sendOrdersType) {
        this.sendOrdersType = sendOrdersType;
    }

    public String getRepairType() {
        return repairType;
    }

    public void setRepairType(String repairType) {
        this.repairType = repairType;
    }

    public String getSendOrdersId() {
        return sendOrdersId;
    }

    public void setSendOrdersId(String sendOrdersId) {
        this.sendOrdersId = sendOrdersId;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getPayFeeFlag() {
        return payFeeFlag;
    }

    public void setPayFeeFlag(String payFeeFlag) {
        this.payFeeFlag = payFeeFlag;
    }

    public String getPriceScope() {
        return priceScope;
    }

    public void setPriceScope(String priceScope) {
        this.priceScope = priceScope;
    }

    public String getReturnVisitId() {
        return returnVisitId;
    }

    public void setReturnVisitId(String returnVisitId) {
        this.returnVisitId = returnVisitId;
    }

    public String getRepairSettingTypeId() {
        return repairSettingTypeId;
    }

    public void setRepairSettingTypeId(String repairSettingTypeId) {
        this.repairSettingTypeId = repairSettingTypeId;
    }
}
