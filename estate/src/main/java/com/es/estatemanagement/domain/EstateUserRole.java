package com.es.estatemanagement.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.es.estatemanagement.base.BaseEntity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author atguigu
 * @since 2022-12-10
 */
@TableName("tb_estate_user_role")
public class EstateUserRole extends BaseEntity implements Serializable {

    /**
     * 角色id
     */
    @TableField("role_id")
    private String roleId;

    /**
     * 用户id
     */
    @TableField("user_id")
    private String userId;


    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
