package com.es.estatemanagement.domain;

import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

/**
 * 进场记录
 */
@Table(name = "tb_entry")
public class Entry implements Serializable {

    private Integer id;   //进出场ID
    private Integer state;   //车辆状态
    private String carCode;  //车牌号
    private Date createTime;    //创建时间
    private Date updateTime;    //更新时间


    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", state=" + state +
                ", carCode='" + carCode + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCarCode() {
        return carCode;
    }

    public void setCarCode(String carCode) {
        this.carCode = carCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
