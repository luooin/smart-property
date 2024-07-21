package com.es.estatemanagement.domain;

/**
 * 部门表
 * */
public class Dept {

    private String deptId;     //部门编号

    private String deptName;   //部门名称

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
