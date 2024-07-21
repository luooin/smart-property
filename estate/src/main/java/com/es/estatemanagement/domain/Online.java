package com.es.estatemanagement.domain;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "tb_online")
public class Online implements Serializable {
    @Id
    private String onLineId;    //状态ID
    private String onLineName;  //状态名称

    public String getOnLineId() {
        return onLineId;
    }

    public void setOnLineId(String onLineId) {
        this.onLineId = onLineId;
    }

    public String getOnLineName() {
        return onLineName;
    }

    public void setOnLineName(String onLineName) {
        this.onLineName = onLineName;
    }
}
