package com.es.estatemanagement.domain;

import javax.persistence.Table;

/**
 * 指派方式
 * */
@Table(name = "tb_send_orders")
public class SendOrders {
    private String sendOrdersId;     //派单方式编号
    private String sendOrdersName;   //派单方式名称

    public String getSendOrdersId() {
        return sendOrdersId;
    }

    public void setSendOrdersId(String sendOrdersId) {
        this.sendOrdersId = sendOrdersId;
    }

    public String getSendOrdersName() {
        return sendOrdersName;
    }

    public void setSendOrdersName(String sendOrdersName) {
        this.sendOrdersName = sendOrdersName;
    }
}
