package com.es.estatemanagement.domain;

import javax.persistence.Table;

/**
 * 公共区域表
 * */
@Table(name = "tb_repair_area")
public class Area {
    private String areaId;   //公共区域编号
    private String areaFlag; //公共区域范围

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaFlag() {
        return areaFlag;
    }

    public void setAreaFlag(String areaFlag) {
        this.areaFlag = areaFlag;
    }
}
