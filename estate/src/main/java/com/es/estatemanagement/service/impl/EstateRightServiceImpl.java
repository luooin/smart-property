package com.es.estatemanagement.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.es.estatemanagement.domain.EstateRight;
import com.es.estatemanagement.domain.EstateRoleRight;
import com.es.estatemanagement.mapper.EstateRightMapper;
import com.es.estatemanagement.mapper.EstateRoleRightMapper;
import com.es.estatemanagement.service.EstateRightService;
import com.es.estatemanagement.util.MenuHelper;
import com.es.estatemanagement.vo.AssginMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EstateRightServiceImpl extends ServiceImpl<EstateRightMapper, EstateRight> implements EstateRightService {

    @Autowired
    EstateRoleRightMapper estateRoleRightMapper;

    @Override
    public List<EstateRight> getEstateManager(Map map) {
        return baseMapper.findAllByEstateRight(map);
    }

    //根据角色获取菜单
    @Override
    public List<EstateRight> findSysMenuByRoleId(Integer roleId) {
        //获取所有菜单 status=1 状态是要启用的
        QueryWrapper<EstateRight> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1);
        List<EstateRight> list = baseMapper.selectList(wrapper);

        //根据角色id查询 角色分配过的菜单列表
        QueryWrapper<EstateRoleRight> wrapperROleRight = new QueryWrapper<>();
        wrapperROleRight.eq("rf_role_id", roleId);
        List<EstateRoleRight> estateUserRoles = estateRoleRightMapper.selectList(wrapperROleRight);
        //查询列表中，获取角色分配的所有菜单rf_right_code
        List<String> roleMenuIds = new ArrayList<>();
        for (EstateRoleRight estateRoleRight : estateUserRoles) {
            roleMenuIds.add(estateRoleRight.getRfRightCode());
        }
        //数据处理：isSelect 如果菜单被选中true，否则为false
        //拿着分配菜单的rf_right_code 和 所有菜单比对，有相同的，让isSelect值为true
        for (EstateRight estateRight : list) {
            //contains是否包含
            if (roleMenuIds.contains(estateRight.getRightCode())) {
                estateRight.setSelect(true);
            } else {
                estateRight.setSelect(false);
            }
        }
        //转换为树形结构最终显示，MenuHelper方法实现
        List<EstateRight> estateRights = MenuHelper.bulidTree(list);
        return estateRights;
    }

    //给角色分配权限
    @Override
    public void doAssign(AssginMenuVo assginMenuVo) {
        //根据角色rf_role_id删除菜单权限
        QueryWrapper<EstateRoleRight> wrapper = new QueryWrapper<>();
        wrapper.eq("rf_role_id", assginMenuVo.getRoleId());
        estateRoleRightMapper.delete(wrapper);
        String roleId = assginMenuVo.getRoleId();
        //遍历菜单id列表，一个一个的进行添加
        List<String> menuIdList = assginMenuVo.getMenuIdList();
        for (String rfRightCode : menuIdList) {
            EstateRoleRight estateRoleRight = new EstateRoleRight();
            estateRoleRight.setRfRoleId(Long.parseLong(roleId));
            estateRoleRight.setRfRightCode(rfRightCode);
            estateRoleRightMapper.insert(estateRoleRight);
        }

    }

    //菜单列表 （树型）
    @Override
    public List<EstateRight> findNodes() {
        //获取所有菜单
        List<EstateRight> list = baseMapper.selectList(null);
        //通过工具类无限套娃
        List<EstateRight> estateRights = MenuHelper.bulidTree(list);
        return estateRights;
    }

    //删除菜单
    @Override
    public boolean removeMenuById(String rightCode) {
        if (rightCode.equals("")) {
            return false;
        }
        //查询当前删除菜单下面是否有子菜单(有就不能删，没有就可以删)
        //根据rightCode=rightParentCode
        QueryWrapper<EstateRight> wrapper = new QueryWrapper<>();
        wrapper.eq("right_parent_code", rightCode);
        Integer integer = baseMapper.selectCount(wrapper);
        if (integer > 0) { //有子菜单
            return false;
        }
        QueryWrapper<EstateRight> delete = new QueryWrapper<>();
        delete.eq("right_code", rightCode);
        //调用删除
        baseMapper.delete(delete);
        return true;
    }

    //更新状态
    @Override
    public void updateStatus(Integer id, Integer status) {
        EstateRight estateRight = baseMapper.selectById(id);
        estateRight.setStatus(status);
        baseMapper.updateById(estateRight);
    }

}
