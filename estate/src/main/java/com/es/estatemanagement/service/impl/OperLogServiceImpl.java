package com.es.estatemanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.es.estatemanagement.domain.EstateRole;
import com.es.estatemanagement.domain.LoginLog;
import com.es.estatemanagement.domain.OperLog;
import com.es.estatemanagement.mapper.OperLogMapper;
import com.es.estatemanagement.service.OperLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.util.StringUtil;

import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-12-23
 */
@Service
public class OperLogServiceImpl extends ServiceImpl<OperLogMapper, OperLog> implements OperLogService {

    @Override
    public void saveSysLog(OperLog operLog) {
        baseMapper.insert(operLog);
    }

    @Override
    public IPage<OperLog> selectPage(Page pageParam, Map searchMap) {
        QueryWrapper<OperLog> wrapper = new QueryWrapper();
        if (StringUtil.isNotEmpty((String) searchMap.get("createTimeBegin"))) {
            wrapper.ge("create_time", searchMap.get("createTimeBegin"));
        }
        if (StringUtil.isNotEmpty((String) searchMap.get("createTimeEnd"))) {
            wrapper.le("create_time", searchMap.get("createTimeEnd"));
        }
        if (searchMap.get("keyword") != null) {
            wrapper.like("title", searchMap.get("keyword")).or()
                    .like("oper_name", searchMap.get("keyword")).or()
                    .like("request_method", searchMap.get("keyword"));
        }
        wrapper.eq("is_deleted", 0);
        return baseMapper.selectPosts(pageParam, wrapper);
    }

}
