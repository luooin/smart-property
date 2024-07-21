package com.es.estatemanagement.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Auth: zhuan
 * @Desc: 小区实体类（具体属性含义，可以参考小区表tb_community）
 */
@Table(name="tb_community")
@ApiModel(description = "返回小区实体信息")
public class Community implements Serializable {

    @Id
    @ApiModelProperty(value = "小区id",required = true)
    private Integer id;                 //小区id
    @ApiModelProperty(value = "小区编号",required = true)
    private String code;                //小区编号
    @ApiModelProperty(value = "小区名称",required = true)
    private String name;                //小区名称
    @ApiModelProperty(value = "坐落地址",required = true)
    private String address;             //坐落地址
    @ApiModelProperty(value = "占地面积（m2）",required = true)
    private Double area;                //占地面积（m2）
    @ApiModelProperty(value = "总栋数",required = true)
    private Integer totalBuildings;     //总栋数
    @ApiModelProperty(value = "总户数",required = true)
    private Integer totalHouseholds;    //总户数
    @ApiModelProperty(value = "绿化率（%）",required = true)
    private Integer greeningRate;       //绿化率（%）
    @ApiModelProperty(value = "缩略图",required = true)
    private String thumbnail;           //缩略图
    @ApiModelProperty(value = "开发商名称",required = true)
    private String developer;           //开发商名称
    @ApiModelProperty(value = "物业公司名称",required = true)
    private String estateCompany;       //物业公司名称
    @ApiModelProperty(value = "创建时间",required = true)
    private Date createTime;            //创建时间
    @ApiModelProperty(value = "更新时间",required = true)
    private Date updateTime;            //更新时间
    @ApiModelProperty(value = "状态:0-启用（默认），1-不启用",required = true)
    private String status;              //状态:0-启用（默认），1-不启用

    //getter/setter生成快捷键 [Alt+Shift+S]

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Integer getTotalBuildings() {
        return totalBuildings;
    }

    public void setTotalBuildings(Integer totalBuildings) {
        this.totalBuildings = totalBuildings;
    }

    public Integer getTotalHouseholds() {
        return totalHouseholds;
    }

    public void setTotalHouseholds(Integer totalHouseholds) {
        this.totalHouseholds = totalHouseholds;
    }

    public Integer getGreeningRate() {
        return greeningRate;
    }

    public void setGreeningRate(Integer greeningRate) {
        this.greeningRate = greeningRate;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getEstateCompany() {
        return estateCompany;
    }

    public void setEstateCompany(String estateCompany) {
        this.estateCompany = estateCompany;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
