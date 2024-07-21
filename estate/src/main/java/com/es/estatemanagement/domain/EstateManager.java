package com.es.estatemanagement.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.es.estatemanagement.base.BaseEntity;

import java.io.Serializable;

/**
 * 物业管理人员表
 */

@TableName("tb_estate_manager")
public class EstateManager extends BaseEntity implements Serializable {


    @TableField("community_name")
    private String communityName;   //所属小区名称
    @TableField("community_id")
    private Integer communityId;    //所属小区ID
    @TableField("login_name")
    private String loginName;       //登录名:登录时使用的名称
    @TableField("name")
    private String name;            //真实名称
    @TableField("password")
    private String password;        //密码
    @TableField("telephone")
    private String telephone;      //手机
    @TableField("email")
    private String email;           //邮箱
    @TableField("status")
    private Integer status;         //状态（1：正常 0：停用）

    @TableField(exist = false)
    private String roleName;       //角色名称


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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
