package com.es.estatemanagement.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.es.estatemanagement.domain.EstateRole;
import com.es.estatemanagement.domain.LoginLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2022-12-16
 */

@Repository
public interface LoginLogMapper extends BaseMapper<LoginLog> {

    @Select("select COUNT(1) from tb_login_log where msg='登入成功' and status=0")
    public Integer loginLogCount();

    IPage<LoginLog> selectPosts(Page pageParam,@Param(Constants.WRAPPER) QueryWrapper<EstateRole> ew);
}
