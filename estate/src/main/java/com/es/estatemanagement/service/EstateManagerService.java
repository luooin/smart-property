package com.es.estatemanagement.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.es.estatemanagement.domain.EstateManager;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface EstateManagerService extends UserDetailsService, IService<EstateManager> {

    public List<EstateManager> getEstateManager();

    public EstateManager login(String loginName);

    IPage<EstateManager> selectPage(Page pageParam, Map searchMap);

    void updateStatus(Integer id, Integer status);
}
