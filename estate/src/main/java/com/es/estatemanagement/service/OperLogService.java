package com.es.estatemanagement.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.es.estatemanagement.domain.OperLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-12-23
 */
public interface OperLogService extends IService<OperLog> {

    //新增操作日志
    public void saveSysLog(OperLog operLog);

    //操作日志分页查询
    IPage<OperLog> selectPage(Page<OperLog> pageParam,Map searchMap);

}
