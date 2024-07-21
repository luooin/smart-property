package com.es.estatemanagement.domain;


import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 黑名单实体类
 */
@Table(name="tb_blacklist")
public class Backlist implements Serializable {

    private Integer id;   //黑名单id
    private String code;  //编号
    private Integer depotId;  //停车场id
    private String depotName;  //停车场名称
    private String carCode;  //车牌号
    private Date startTime;  //开始时间



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getDepotId() {
        return depotId;
    }

    public void setDepotId(Integer depotId) {
        this.depotId = depotId;
    }

    public String getDepotName() {
        return depotName;
    }

    public void setDepotName(String depotName) {
        this.depotName = depotName;
    }

    public String getCarCode() {
        return carCode;
    }

    public void setCarCode(String carCode) {
        this.carCode = carCode;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }


    @Override
    public String toString() {
        return "Backlist{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", depotId=" + depotId +
                ", depotName='" + depotName + '\'' +
                ", carCode='" + carCode + '\'' +
                ", startTime=" + startTime +
                '}';
    }
}
