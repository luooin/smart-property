package com.es.estatemanagement.domain;

import javax.persistence.Table;

/**
 * 报修回访表
 * */
@Table(name = "tb_return_visit")
public class ReturnVisit{
    private String returnVisitId;    //回访编号

    private String returnVisitFlag;  //回访标识 001 都不回访 002 已评价不回访 003 都回访

    public String getReturnVisitId() {
        return returnVisitId;
    }

    public void setReturnVisitId(String returnVisitId) {
        this.returnVisitId = returnVisitId;
    }

    public String getReturnVisitFlag() {
        return returnVisitFlag;
    }

    public void setReturnVisitFlag(String returnVisitFlag) {
        this.returnVisitFlag = returnVisitFlag;
    }
}
