package com.es.estatemanagement.service.impl;

import com.es.estatemanagement.domain.SendOrders;
import com.es.estatemanagement.mapper.SendOrdersMapper;
import com.es.estatemanagement.service.SendOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SendOrdersServiceImpl  implements SendOrdersService {

    @Autowired
    SendOrdersMapper sendOrdersMapper;

    @Override
    public List<SendOrders> findByOrdersName(String sendOrdersName) {
        return sendOrdersMapper.findByOrdersName(sendOrdersName);
    }

    @Override
    public List<SendOrders> findBySendOrdersId(){
        return sendOrdersMapper.findBySendOrdersId();
    }
}
