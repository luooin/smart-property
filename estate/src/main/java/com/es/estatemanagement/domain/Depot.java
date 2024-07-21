package com.es.estatemanagement.domain;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 停车场管理
 */
@Table(name = "tb_depot")
public class Depot implements Serializable {

    private Integer id;   //停车场ID
    private String depotCode;  //停车场编号
    private String depotName;  //停车场名称
    private Long depotType;    //停车场类型（1：地上停车场 2：地下停车场）
    private String outsideCode;  //外部编码
    private Date createTime;   //创建时间
//    private Integer depotStatus;   //停车场状态
    private Integer communityId;    //所属小区ID
    private String communityName;  //所属小区
    private Integer userId;  //操作者id
    private String userName; //操作者




    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

//    public Integer getDepotStatus() {
//        return depotStatus;
//    }
//
//    public void setDepotStatus(Integer depotStatus) {
//        this.depotStatus = depotStatus;
//    }

    public Long getDepotType() {
        return depotType;
    }

    public void setDepotType(Long depotType) {
        this.depotType = depotType;
    }

    public String getOutsideCode() {
        return outsideCode;
    }

    public void setOutsideCode(String outsideCode) {
        this.outsideCode = outsideCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepotCode() {
        return depotCode;
    }

    public void setDepotCode(String depotCode) {
        this.depotCode = depotCode;
    }

    public String getDepotName() {
        return depotName;
    }

    public void setDepotName(String depotName) {
        this.depotName = depotName;
    }
}
