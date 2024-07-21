package com.es.estatemanagement.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

/**
 * <p>
 *  菜单角色关联表
 * </p>
 *
 * @author atguigu
 * @since 2022-12-12
 */
@TableName("tb_estate_role_right")
public class EstateRoleRight implements Serializable {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long rfId;

    /**
     * 角色id
     */
    private Long rfRoleId;

    /**
     * 菜单right_code
     */
    private String rfRightCode;

    /**
     * 状态(0:禁止,1:正常)
     */
    private Integer status;

    /**
     * 删除标记（0:可用 1:已删除）
     */
    @TableLogic  //逻辑删除 默认效果 0 没有删除 1 已经删除
    @TableField("is_deleted")
    private Integer isDeleted;

    public Long getRfId() {
        return rfId;
    }

    public void setRfId(Long rfId) {
        this.rfId = rfId;
    }

    public Long getRfRoleId() {
        return rfRoleId;
    }

    public void setRfRoleId(Long rfRoleId) {
        this.rfRoleId = rfRoleId;
    }

    public String getRfRightCode() {
        return rfRightCode;
    }

    public void setRfRightCode(String rfRightCode) {
        this.rfRightCode = rfRightCode;
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
