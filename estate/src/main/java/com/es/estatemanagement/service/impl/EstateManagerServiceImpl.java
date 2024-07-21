package com.es.estatemanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.es.estatemanagement.domain.EstateRight;
import com.es.estatemanagement.mapper.EstateManagerMapper;
import com.es.estatemanagement.domain.EstateManager;
import com.es.estatemanagement.mapper.EstateRightMapper;
import com.es.estatemanagement.service.EstateManagerService;
import com.es.estatemanagement.service.LoginLogService;
import com.es.estatemanagement.util.GetIp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class EstateManagerServiceImpl extends ServiceImpl<EstateManagerMapper, EstateManager> implements EstateManagerService {

    @Autowired
    EstateRightMapper estateRightMapper;

    @Autowired
    LoginLogService loginLogService;


    @Override
    public List<EstateManager> getEstateManager() {
        return baseMapper.selectList(null);
    }

    @Override
//    @Cacheable(value = "EstateManager", key = "#loginName")
    public EstateManager login(String loginName) {
        return baseMapper.findByLoginNameAndPassword(loginName);
    }

    @Override
    public IPage<EstateManager> selectPage(Page pageParam, Map searchMap) {
        QueryWrapper<EstateManager> wrapper = new QueryWrapper();
        if (StringUtil.isNotEmpty((String) searchMap.get("createTimeBegin"))) {
            wrapper.ge("create_time", searchMap.get("createTimeBegin"));
        }
        if (StringUtil.isNotEmpty((String) searchMap.get("createTimeEnd"))) {
            wrapper.le("create_time", searchMap.get("createTimeEnd"));
        }
        if (searchMap.get("keyword") != null) {
            wrapper.like("login_name", searchMap.get("keyword"))
                    .or().like("name", searchMap.get("keyword"))
                    .or().like("telephone", searchMap.get("keyword"));
        }
        wrapper.eq("is_deleted", 0);
        return baseMapper.selectPosts(pageParam, wrapper);
    }

    @Override
    public void updateStatus(Integer id, Integer status) {
        EstateManager estateManager = baseMapper.selectById(id);
        estateManager.setStatus(status);
        baseMapper.updateById(estateManager);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws BadCredentialsException {
        EstateManager estateManager = baseMapper.findByLoginName(username);
        if (estateManager == null) {
            try {
                loginLogService.recordLoginLog(username, 1, GetIp.getV4OrV6IP(),GetIp.getCityInfo(GetIp.getV4OrV6IP()), "账户不存在");
            } catch (Exception e) {
                e.printStackTrace();
            }
            throw new BadCredentialsException("账户不存在！");
        }
        if (estateManager.getStatus() == 0) {
            try {
                loginLogService.recordLoginLog(username, 1, GetIp.getV4OrV6IP(),GetIp.getCityInfo(GetIp.getV4OrV6IP()), "权限不足,请联系管理员");
            } catch (Exception e) {
                e.printStackTrace();
            }
            throw new BadCredentialsException("权限不足,请联系管理员!!!");
        }
        Map map = new HashMap();
        map.put("username", username);
        List<EstateRight> estateRights = estateRightMapper.findAllByEstateRight(map);
        List<String> authorities = new ArrayList<>();
        for (EstateRight estateRight : estateRights) {
            authorities.add(estateRight.getRightUrl());
        }
        String str = String.join(",", authorities);
        List<GrantedAuthority> list = AuthorityUtils.commaSeparatedStringToAuthorityList(str);
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(estateManager.getLoginName()).
                password(estateManager.getPassword()).roles(estateManager.getRoleName()).authorities(list).build();
        return userDetails;
    }
}
