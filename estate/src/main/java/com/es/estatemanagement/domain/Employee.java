package com.es.estatemanagement.domain;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 员工表
 * */
@Table(name = "tb_employee")
public class Employee {
    @Id
    private String staffId;    //员工编号

    private String staffName;  //员工姓名

    private String phone;      //手机号码

    private String deptId;     //部门编号

    private String position;   //职位

    private String mailbox;    //邮箱

    private String address;    //地址

    private String gender;     //性别

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
