package com.es.estatemanagement.domain;

import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 物业工作人员表
 * */
@Table(name = "tb_repair_type_user")
public class RepairTypeUser {
    private String typeUserId;       //工单设置编号
    private String repairType;       //报修类型
    private String staffId;          //员工编号
    private String staffName;        //员工名称
    private Integer communityId;     //小区编号
    private String statusCd;         //状态 9999 在线 8888 离线 tb_dict 表
    private String remark;
    private Timestamp createTime;

    public String getTypeUserId() {
        return typeUserId;
    }

    public void setTypeUserId(String typeUserId) {
        this.typeUserId = typeUserId;
    }

    public String getRepairType() {
        return repairType;
    }

    public void setRepairType(String repairType) {
        this.repairType = repairType;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
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
}
