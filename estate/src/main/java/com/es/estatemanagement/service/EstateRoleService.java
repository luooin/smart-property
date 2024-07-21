package com.es.estatemanagement.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.es.estatemanagement.domain.EstateRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.es.estatemanagement.vo.AssginRoleVo;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-12-11
 */
public interface EstateRoleService extends IService<EstateRole> {

    IPage<EstateRole> selectPage(Page pageParam, Map searchMap);

    void updateStatus(Integer id, Integer status);

    Map<String, Object> getRolesByUserId(Integer userId);

    void doAssign(AssginRoleVo assginRoleVo);
}
