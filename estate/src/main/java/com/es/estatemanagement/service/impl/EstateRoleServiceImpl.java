package com.es.estatemanagement.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.es.estatemanagement.domain.EstateRole;
import com.es.estatemanagement.domain.EstateUserRole;
import com.es.estatemanagement.mapper.EstateRoleMapper;
import com.es.estatemanagement.mapper.EstateUserRoleMapper;
import com.es.estatemanagement.service.EstateRoleService;
import com.es.estatemanagement.vo.AssginRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-12-11
 */
@Service
public class EstateRoleServiceImpl extends ServiceImpl<EstateRoleMapper, EstateRole> implements EstateRoleService {

    @Autowired
    EstateUserRoleMapper estateUserRoleMapper;

    @Override
    public IPage<EstateRole> selectPage(Page pageParam, Map searchMap) {
        QueryWrapper<EstateRole> wrapper = new QueryWrapper();
        if (searchMap.get("keyword") != null) {
            wrapper.like("role_name", searchMap.get("keyword"))
                    .or().like("role_desc", searchMap.get("keyword"))
                    .or().like("role_describe", searchMap.get("keyword"));
        }
        wrapper.eq("is_deleted", 0);
        return baseMapper.selectPosts(pageParam, wrapper);
    }

    @Override
    public void updateStatus(Integer id, Integer status) {
        EstateRole estateRole = baseMapper.selectById(id);
        estateRole.setStatus(status);
        baseMapper.updateById(estateRole);
    }

    //根据用户获取角色数据
    @Override
    public Map<String, Object> getRolesByUserId(Integer userId) {
        //获取所有的角色
        List<EstateRole> estateRoles = baseMapper.selectList(null);
        //更具用户id进行查询,已经分配角色
        QueryWrapper<EstateUserRole> wrapper = new QueryWrapper();
        wrapper.eq("user_id", userId);
        //从EstateUserRole集合中获取所有的角色id
        List<EstateUserRole> estateUserRoles = estateUserRoleMapper.selectList(wrapper);
        //从EstateUserRole集合中获取所有的角色id
        List<String> userRoleIds = new ArrayList<>();
        for (EstateUserRole estateUserRole : estateUserRoles) {
            userRoleIds.add(String.valueOf(estateUserRole.getRoleId()));
        }
        //封装到map集合
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("allRoles", estateRoles);
        returnMap.put("userRoleIds", userRoleIds);
        return returnMap;
    }

    @Override
    public void doAssign(AssginRoleVo assginRoleVo) {
        //根据用户id删除之前分配的角色
        QueryWrapper<EstateUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", assginRoleVo.getUserId());
        estateUserRoleMapper.delete(wrapper);

        //获取所有的角色id,添加角色用户关系表
        //角色id的列表
        List<String> roleIdList = assginRoleVo.getRoleIdList();
        for (String roleId : roleIdList) {
            EstateUserRole estateUserRole = new EstateUserRole();
            estateUserRole.setUserId(assginRoleVo.getUserId());
            estateUserRole.setRoleId(roleId);
            estateUserRoleMapper.insert(estateUserRole);
        }
    }

}
