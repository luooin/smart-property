package com.es.estatemanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.es.estatemanagement.domain.EstateRole;
import com.es.estatemanagement.domain.LoginLog;
import com.es.estatemanagement.mapper.LoginLogMapper;
import com.es.estatemanagement.service.LoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.util.StringUtil;

import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-12-16
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

    @Autowired
    LoginLogMapper loginLogMapper;


    @Override
    public IPage<LoginLog> selectPage(Page pageParam, Map searchMap) {
        QueryWrapper<EstateRole> wrapper = new QueryWrapper();
        if (StringUtil.isNotEmpty((String) searchMap.get("createTimeBegin"))) {
            wrapper.ge("create_time", searchMap.get("createTimeBegin"));
        }
        if (StringUtil.isNotEmpty((String) searchMap.get("createTimeEnd"))) {
            wrapper.le("create_time", searchMap.get("createTimeEnd"));
        }
        if (searchMap.get("keyword") != null) {
            wrapper.like("username", searchMap.get("keyword")).or()
                    .like("ipaddr", searchMap.get("keyword"));
        }
        wrapper.eq("is_deleted", 0);
        return baseMapper.selectPosts(pageParam, wrapper);
    }

    @Override
    public void recordLoginLog(String username, Integer status, String ipaddr,String province,String message) {
        LoginLog loginLog = new LoginLog();
        loginLog.setUsername(username);
        loginLog.setStatus(status);
        loginLog.setIpaddr(ipaddr);
        loginLog.setProvince(province);
        loginLog.setMsg(message);
        loginLog.setCreateTime(new java.util.Date());
        loginLogMapper.insert(loginLog);
    }

    @Override
    public Integer loginLogCount() {
        return loginLogMapper.loginLogCount();
    }


}
