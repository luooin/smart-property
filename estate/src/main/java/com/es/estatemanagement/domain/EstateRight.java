package com.es.estatemanagement.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.es.estatemanagement.base.BaseEntity;

import java.util.List;

@TableName("tb_estate_right")
public class EstateRight extends BaseEntity {

    @TableField("right_code")
    private String rightCode; //权限表主键id
    @TableField("right_parent_code")
    private String rightParentCode; //权限表右侧父id
    @TableField("right_type")
    private String rightType; //级联分类
    @TableField("right_text")
    private String rightText; //模块Text
    @TableField("right_url")
    private String rightUrl; //模块路径
    @TableField("right_tip")
    private String rightTip; //模块Tip

    @TableField("icon")
    private String icon; //图标

    @TableField("sort_value")
    private String sortValue; //排序

    @TableField("status")
    private Integer status;         //状态（1：正常 0：停用）


    //无限套娃
    @TableField(exist = false)
    private List<EstateRight> children;

    //是否选中
    @TableField(exist = false)
    private boolean isSelect;

//    @TableField(exist = false)
//    private Integer rfId;     //角色id
//    @TableField(exist = false)
//    private Integer rfRoleId; //tb_estate_role_right.id


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSortValue() {
        return sortValue;
    }

    public void setSortValue(String sortValue) {
        this.sortValue = sortValue;
    }

    public String getRightCode() {
        return rightCode;
    }

    public void setRightCode(String rightCode) {
        this.rightCode = rightCode;
    }

    public String getRightParentCode() {
        return rightParentCode;
    }

    public void setRightParentCode(String rightParentCode) {
        this.rightParentCode = rightParentCode;
    }

    public String getRightType() {
        return rightType;
    }

    public void setRightType(String rightType) {
        this.rightType = rightType;
    }

    public String getRightText() {
        return rightText;
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
    }

    public String getRightUrl() {
        return rightUrl;
    }

    public void setRightUrl(String rightUrl) {
        this.rightUrl = rightUrl;
    }

    public String getRightTip() {
        return rightTip;
    }

    public void setRightTip(String rightTip) {
        this.rightTip = rightTip;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public List<EstateRight> getChildren() {
        return children;
    }

    public void setChildren(List<EstateRight> children) {
        this.children = children;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
