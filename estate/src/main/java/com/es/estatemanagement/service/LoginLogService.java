package com.es.estatemanagement.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.es.estatemanagement.domain.LoginLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-12-16
 */
public interface LoginLogService extends IService<LoginLog> {

    public IPage<LoginLog> selectPage(Page pageParam, Map searchMap);
    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status 状态
     * @param ipaddr ip
     * @param message 消息内容
     * @return
     */
    public void recordLoginLog(String username, Integer status, String ipaddr,String province,String message);


    public Integer loginLogCount();
}
