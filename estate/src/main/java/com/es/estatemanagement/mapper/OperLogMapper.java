package com.es.estatemanagement.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.es.estatemanagement.domain.EstateRole;
import com.es.estatemanagement.domain.OperLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2022-12-23
 */
@Repository
public interface OperLogMapper extends BaseMapper<OperLog> {

    IPage<OperLog> selectPosts(Page<OperLog> pageParam,@Param(Constants.WRAPPER) QueryWrapper<OperLog> ew);
}
