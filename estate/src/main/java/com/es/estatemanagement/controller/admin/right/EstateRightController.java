package com.es.estatemanagement.controller.admin.right;


import com.es.estatemanagement.common.MessageConstant;
import com.es.estatemanagement.common.PageResult;
import com.es.estatemanagement.common.Result;
import com.es.estatemanagement.common.StatusCode;
import com.es.estatemanagement.common.annotation.Log;
import com.es.estatemanagement.common.enums.BusinessType;
import com.es.estatemanagement.domain.EstateRight;
import com.es.estatemanagement.service.EstateRightService;
import com.es.estatemanagement.vo.AssginMenuVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *
 * </p>
 * @author yang
 * @date 2024/5/20
 */
@RestController
@RequestMapping("/right")
@Api(tags = "物业菜单控制层", description = "只负责接收前端浏览器发送的请求和请求参数，调用service层获取业务逻辑加工处理后的数据", value = "EstateRightController类")
public class EstateRightController {

    @Autowired
    EstateRightService estateRightService;

    //根据角色获取菜单
    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("/toAssign")
    public Result toAssign(Integer roleId) {
        List<EstateRight> list = estateRightService.findSysMenuByRoleId(roleId);
        return new Result(true, StatusCode.OK, MessageConstant.RIGHT_SEARCH_SUCCESS, list);
    }

    //给角色分配权限
    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    @Log(title = "角色管理", businessType = BusinessType.ASSGIN)
    public Result doAssign(@RequestBody AssginMenuVo assginMenuVo) {
        estateRightService.doAssign(assginMenuVo);
        return new Result(true, StatusCode.OK, MessageConstant.RIGHT_ROLE_DOASSIGN);
    }

    //菜单列表 （树型）
    @ApiOperation(value = "获取菜单")
    @GetMapping("findNodes")
    public Result findNodes() {
        List<EstateRight> list = estateRightService.findNodes();
        return new Result(true, StatusCode.OK, MessageConstant.RIGHT_SEARCH_SUCCESS, list);
    }

    //添加菜单
    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    public Result save(@RequestBody EstateRight permission) {
        estateRightService.save(permission);
        return new Result(true, StatusCode.OK, MessageConstant.RIGHT_ADD_SUCCESS);
    }

    //根据id查询菜单
    @ApiOperation("根据id查询菜单")
    @GetMapping("findNode")
    public Result findNode(Integer id) {
        EstateRight estateRight = estateRightService.getById(id);
        return new Result(true, StatusCode.OK, MessageConstant.RIGHT_FIND_BY_ID_SUCCESS, estateRight);
    }

    //修改菜单
    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    public Result updateById(@RequestBody EstateRight permission) {
        estateRightService.updateById(permission);
        return new Result(true, StatusCode.OK, MessageConstant.RIGHT_UPDATE_SUCCESS);
    }

    //删除菜单
    @ApiOperation(value = "删除菜单")
    @DeleteMapping("remove")
    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    public Result remove(String rightCode) {
        boolean bool = estateRightService.removeMenuById(rightCode);
        return new Result(bool, bool ? StatusCode.OK : StatusCode.ERROR, MessageConstant.RIGHT_DELETE_SUCCESS);
    }

    @ApiOperation(value = "更新状态")
    @GetMapping("updateStatus")
    @Log(title = "菜单管理", businessType = BusinessType.STATUS)
    public Result updateStatus(Integer id, Integer status) {
        estateRightService.updateStatus(id, status);
        return new Result(true, StatusCode.OK, MessageConstant.RIGHT_ASSGINROLEVO_SUCCESS);
    }

}
