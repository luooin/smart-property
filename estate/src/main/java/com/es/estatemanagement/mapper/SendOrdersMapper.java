package com.es.estatemanagement.mapper;

import com.es.estatemanagement.domain.SendOrders;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SendOrdersMapper extends Mapper<SendOrders> {

    @Select("select send_orders_id from tb_send_orders where send_orders_name=#{sendOrdersName}")
    public List<SendOrders> findByOrdersName(String sendOrdersName);

    @Select("select send_orders_id,send_orders_name from tb_send_orders")
    public List<SendOrders> findBySendOrdersId();

}
