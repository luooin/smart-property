package com.es.estatemanagement.mapper;

import com.es.estatemanagement.domain.ReturnVisit;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ReturnVisitMapper extends Mapper<ReturnVisit> {

    //#查询回访设置
    @Select("select return_visit_id,return_visit_flag from tb_return_visit")
    public List<ReturnVisit> findByVisitId();

}
