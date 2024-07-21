package com.es.estatemanagement.domain;

import com.baomidou.mybatisplus.annotation.TableField;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


@Table(name="tb_car")
public class Car implements Serializable {
    @Id
    private Integer id;        //车辆id
    private String picture;    //车辆照片
    private Integer ownerId;   //所属成员ID
    @TableField(exist = false)
    private String ownerName;  //成员名称
    private String color;      //车辆颜色
    private String carNumber;  //车牌号
    private String remark;     //备注
    private Date createTime;   //创建时间
    private Date updateTime;   //修改时间
    private String psName;     //停车位名称
    private Integer psId;     //停车位Id
    private Integer psState;     //停车位状态
    private String carBrand;   //车辆品牌
    private String typeName;   //车辆类型





    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getPsName() {
        return psName;
    }

    public void setPsName(String psName) {
        this.psName = psName;
    }

    public Integer getPsId() {
        return psId;
    }

    public void setPsId(Integer psId) {
        this.psId = psId;
    }

    public Integer getPsState() {
        return psState;
    }

    public void setPsState(Integer psState) {
        this.psState = psState;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
