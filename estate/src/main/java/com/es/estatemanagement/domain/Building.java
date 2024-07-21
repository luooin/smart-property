package com.es.estatemanagement.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.es.estatemanagement.base.BaseEntity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 楼栋表
 */
@TableName("tb_building")
public class Building extends BaseEntity implements Serializable {

    //    @Id
    //    private Integer id;           //楼栋ID
    private String communityName;       //所属小区名称
    private Integer communityId;        //所属小区ID
    private String name;                //栋数名称
    private Integer totalHouseholds;    //总户数
    private String description;         //描述


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalHouseholds() {
        return totalHouseholds;
    }

    public void setTotalHouseholds(Integer totalHouseholds) {
        this.totalHouseholds = totalHouseholds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
