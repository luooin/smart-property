package com.es.estatemanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.es.estatemanagement.domain.Online;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface OnlineMapper extends BaseMapper<Online> {

    @Select("select on_line_id,on_line_name from tb_online")
    public List<Online> findByLineId();
}
