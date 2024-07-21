package com.es.estatemanagement.domain;


import com.baomidou.mybatisplus.annotation.*;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@TableName("tb_estate_role")
public class EstateRole implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer roleId;         //角色id

    private String roleName;        //角色名称
    private String roleDesc;        //拥有权限规则
    private String roleDescribe;    //角色描述

    @TableField("status")
    private Integer status;         //状态（1：正常 0：停用）

    @TableLogic                     //逻辑删除 默认效果 0 没有删除 1 已经删除
    @TableField("is_deleted")
    private Integer isDeleted;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public String getRoleDescribe() {
        return roleDescribe;
    }

    public void setRoleDescribe(String roleDescribe) {
        this.roleDescribe = roleDescribe;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
