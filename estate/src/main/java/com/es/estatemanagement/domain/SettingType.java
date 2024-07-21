package com.es.estatemanagement.domain;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 报修设置类型
 * */
@Table(name = "tb_setting_type")
public class SettingType {
    @Id
    private String repairSettingTypeId;    //报修设置类型编号

    private String repairSettingTypeName;  //报修设置类型名称

    public String getRepairSettingTypeId() {
        return repairSettingTypeId;
    }

    public void setRepairSettingTypeId(String repairSettingTypeId) {
        this.repairSettingTypeId = repairSettingTypeId;
    }

    public String getRepairSettingTypeName() {
        return repairSettingTypeName;
    }

    public void setRepairSettingTypeName(String repairSettingTypeName) {
        this.repairSettingTypeName = repairSettingTypeName;
    }
}
