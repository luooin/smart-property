package com.es.estatemanagement.mapper;

import com.es.estatemanagement.domain.Backlist;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface BacklistMapper   extends Mapper<Backlist> {

    @Select("SELECT * FROM tb_blacklist WHERE id=#{id}")
    public Backlist myBackId(Integer id);
}
