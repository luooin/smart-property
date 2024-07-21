package com.es.estatemanagement.controller.admin.role;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.es.estatemanagement.common.MessageConstant;
import com.es.estatemanagement.common.PageResult;
import com.es.estatemanagement.common.Result;
import com.es.estatemanagement.common.StatusCode;
import com.es.estatemanagement.common.annotation.Log;
import com.es.estatemanagement.common.enums.BusinessType;
import com.es.estatemanagement.domain.EstateRole;
import com.es.estatemanagement.service.EstateRoleService;
import com.es.estatemanagement.vo.AssginRoleVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *
 * </p>
 * @author yang
 * @date 2024/5/20
 */
@RestController
@RequestMapping("/role")
@Api(tags = "物业角色控制层", description = "只负责接收前端浏览器发送的请求和请求参数，调用service层获取业务逻辑加工处理后的数据", value = "EstateRoleController类")
public class EstateRoleController {

    @Autowired
    EstateRoleService estateRoleService;

    @RequestMapping("/search")
    public PageResult index(@RequestBody Map searchMap) {
        Page pageParam = new Page<>(Long.valueOf(Integer.toString((Integer) searchMap.get("page"))),
                Long.valueOf(Integer.toString((Integer) searchMap.get("limit"))));
        IPage<EstateRole> pageModel = estateRoleService.selectPage(pageParam, searchMap);
        return new PageResult(true, StatusCode.OK, MessageConstant.ROLE_SEARCH_SUCCESS, pageModel, 0L);
    }

    //查询用户角色
    @GetMapping("/toAssign")
    public PageResult toAssign(Integer userId) {
        Map<String, Object> roleMap = estateRoleService.getRolesByUserId(userId);
        return new PageResult(true, StatusCode.OK, MessageConstant.ROLE_SEARCH_SUCCESS,roleMap,0L);
    }

    //新增用户角色
    @PostMapping("/doAssign")
    @Log(title = "新增用户角色", businessType = BusinessType.INSERT)
    public Result doAssign(@RequestBody AssginRoleVo assginRoleVo) {
        estateRoleService.doAssign(assginRoleVo);
        return  new Result(true, StatusCode.OK, MessageConstant.ROLE_ASSGINROLEVO_SUCCESS);
    }

    @RequestMapping("/add")
    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    public Result add(@RequestBody EstateRole estateRole) {
        Boolean isSuccess = estateRoleService.save(estateRole);
        return new Result(true, StatusCode.OK, MessageConstant.ROLE_ADD_SUCCESS);
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        EstateRole estateRole = estateRoleService.getById(id);
        return new Result(true, StatusCode.OK, MessageConstant.ROLE_FIND_BY_ID_SUCCESS, estateRole);
    }

    @RequestMapping("/update")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    public Result update(@RequestBody EstateRole estateRole) {
        boolean isSuccess = estateRoleService.updateById(estateRole);
        return new Result(true, StatusCode.OK, MessageConstant.ROLE_UPDATE_SUCCESS);
    }

    @GetMapping("/updateStatus")
    @Log(title = "角色管理", businessType = BusinessType.STATUS)
    public Result updateStatus(Integer id, Integer status) {
        estateRoleService.updateStatus(id,status);
        return new Result(true, StatusCode.OK, MessageConstant.ROLE_UPDATE_SUCCESS);
    }

    @RequestMapping("/del")
    @Log(title = "角色管理", businessType = BusinessType.DELETE)
    public Result del(Integer id) {
        boolean isSuccess = estateRoleService.removeById(id);
        return new Result(true, StatusCode.OK, MessageConstant.ROLE_DELETE_SUCCESS);
    }
}

