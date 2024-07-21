package com.es.estatemanagement.mapper;

import com.es.estatemanagement.domain.Dict;
import com.es.estatemanagement.domain.SendOrders;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DictMapper extends Mapper<SendOrders> {

    @Select("select status_cd from tb_dict where status_name=#{statusName}")
    public List<Dict> findByStatusName(String statusName);

    @Select("select status_cd,status_name from tb_dict")
    public List<Dict> findByStatusCd();

}
