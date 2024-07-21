package com.es.estatemanagement.domain;


import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 车位申请
 */
@Table(name = "tb_parking_application")
public class ParkingApplication implements Serializable {

    @Id
    private Integer id;   //车位申请ID
    private String carCode;  //车牌号
    private String carBrand;  //汽车品牌
    private String typeName;  //汽车类型
    private String carColor;  //汽车颜色
    private Date rentTime;   //起租时间
    private Date matchingTime;   //结组时间
    private Integer proposerId;  //申请人id
    private String proposer;  //申请人
    private String telephone;  //申请人电话
    private String auditResult;  //审核结果
    private String auditOpinion;  //审核意见
    private String remarks;  //备注
    private String communityName;   //所属小区名称
    private Integer communityId;        //所属小区ID
    private String buildingName;    //所属栋数名称
    private Integer buildingId;         //所属栋数ID
    private Integer userId;  //操作者id
    private String userName; //操作者
    private Integer carId;
    private String  carParkingName;
    private Integer parkingId;
    private Integer parkingState;

    public String getCarParkingName() {
        return carParkingName;
    }

    public void setCarParkingName(String carParkingName) {
        this.carParkingName = carParkingName;
    }

    public Integer getParkingId() {
        return parkingId;
    }

    public void setParkingId(Integer parkingId) {
        this.parkingId = parkingId;
    }

    public Integer getParkingState() {
        return parkingState;
    }

    public void setParkingState(Integer parkingState) {
        this.parkingState = parkingState;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

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

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public Integer getProposerId() {
        return proposerId;
    }

    public void setProposerId(Integer proposerId) {
        this.proposerId = proposerId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarCode() {
        return carCode;
    }

    public void setCarCode(String carCode) {
        this.carCode = carCode;
    }

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

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public Date getRentTime() {
        return rentTime;
    }

    public void setRentTime(Date rentTime) {
        this.rentTime = rentTime;
    }

    public Date getMatchingTime() {
        return matchingTime;
    }

    public void setMatchingTime(Date matchingTime) {
        this.matchingTime = matchingTime;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    public String getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult;
    }
}
