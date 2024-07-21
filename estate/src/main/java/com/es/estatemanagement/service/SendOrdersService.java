package com.es.estatemanagement.service;

import com.es.estatemanagement.domain.SendOrders;

import java.util.List;

public interface SendOrdersService {
    public List<SendOrders> findByOrdersName(String sendOrdersName);

    public List<SendOrders> findBySendOrdersId();
}
