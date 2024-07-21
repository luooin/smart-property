package com.es.estatemanagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.es.estatemanagement.domain.EstateRight;
import com.es.estatemanagement.vo.AssginMenuVo;

import java.util.List;
import java.util.Map;

public interface EstateRightService extends IService<EstateRight> {

    public List<EstateRight> getEstateManager(Map map);


    List<EstateRight> findSysMenuByRoleId(Integer roleId);

    void doAssign(AssginMenuVo assginMenuVo);

    List<EstateRight> findNodes();

    boolean removeMenuById(String rightCode);

    void updateStatus(Integer id, Integer status);
}
