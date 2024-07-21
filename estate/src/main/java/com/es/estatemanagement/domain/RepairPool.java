package com.es.estatemanagement.domain;

import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 报修表
 * */
@Table(name = "tb_repair_pool")
public class RepairPool {
    @Id
    private String repairId;           //报修ID
    private Integer communityId;       //小区ID
    private String repairType;         //报修类型
    private String repairName;         //报修人姓名
    private String tel;                //手机号
    private String appointmentTime;    //预约时间
    private String context;            //报修内容
    private Timestamp createTime;      //创建时间
    private String statusCd;         //报修状态编号
    private String sendOrdersId;      //派单方式 tb_send_orders 1:抢单   2：派单  3：指派
    private String address;           //地址
    private String maintenanceType;    //维修类型  1001有偿服务  1002无偿服务
    private String repairChannel;      //报修渠道  D员工代客报修  T电话报修 Z业主自主保修
    private String repairMaterials;   //维修物料
    private String repairFee;          //费用明细：单价*数量=总价
    private String payType;            //支付方式1、现金2、微信转账3、微信公众号4支付宝

    public String getRepairId() {
        return repairId;
    }

    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getRepairType() {
        return repairType;
    }

    public void setRepairType(String repairType) {
        this.repairType = repairType;
    }

    public String getRepairName() {
        return repairName;
    }

    public void setRepairName(String repairName) {
        this.repairName = repairName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
    }

    public String getSendOrdersId() {
        return sendOrdersId;
    }

    public void setSendOrdersId(String sendOrdersId) {
        this.sendOrdersId = sendOrdersId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMaintenanceType() {
        return maintenanceType;
    }

    public void setMaintenanceType(String maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    public String getRepairChannel() {
        return repairChannel;
    }

    public void setRepairChannel(String repairChannel) {
        this.repairChannel = repairChannel;
    }

    public String getRepairMaterials() {
        return repairMaterials;
    }

    public void setRepairMaterials(String repairMaterials) {
        this.repairMaterials = repairMaterials;
    }

    public String getRepairFee() {
        return repairFee;
    }

    public void setRepairFee(String repairFee) {
        this.repairFee = repairFee;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}
