package com.es.estatemanagement.controller.admin.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.es.estatemanagement.common.MessageConstant;
import com.es.estatemanagement.common.PageResult;
import com.es.estatemanagement.common.Result;
import com.es.estatemanagement.common.StatusCode;
import com.es.estatemanagement.common.annotation.Log;
import com.es.estatemanagement.common.enums.BusinessType;
import com.es.estatemanagement.domain.EstateManager;
import com.es.estatemanagement.service.EstateManagerService;
import com.es.estatemanagement.util.MD5;
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
@RequestMapping("/admin")
@Api(tags = "物业管理人员控制层", description = "只负责接收前端浏览器发送的请求和请求参数，调用service层获取业务逻辑加工处理后的数据", value = "AdminController类")
public class AdminController {

    @Autowired
    EstateManagerService estateManagerService;

    @RequestMapping("/search")
    public PageResult index(@RequestBody Map searchMap) {
        Page pageParam = new Page<>(Long.valueOf(Integer.toString((Integer) searchMap.get("page"))),
                Long.valueOf(Integer.toString((Integer)searchMap.get("limit"))));
        IPage<EstateManager> pageModel = estateManagerService.selectPage(pageParam, searchMap);
        return new PageResult(true, StatusCode.OK, MessageConstant.ADMIN_SEARCH_SUCCESS, pageModel, 0L);
    }

    @RequestMapping("/add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    public Result add(@RequestBody EstateManager estateManager) {
        estateManager.setPassword(MD5.encrypt(estateManager.getPassword()));
        Boolean isSuccess = estateManagerService.save(estateManager);
        return new Result(true, StatusCode.OK, MessageConstant.ADMIN_ADD_SUCCESS);
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        EstateManager estateManager = estateManagerService.getById(id);
        return new Result(true, StatusCode.OK, MessageConstant.ADMIN_FIND_BY_ID_SUCCESS, estateManager);
    }

    @RequestMapping("/update")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    public Result update(@RequestBody EstateManager estateManager) {
        boolean isSuccess = estateManagerService.updateById(estateManager);
        return new Result(true, StatusCode.OK, MessageConstant.ADMIN_UPDATE_SUCCESS);
    }

    @GetMapping("/updateStatus")
    @Log(title = "用户管理", businessType = BusinessType.STATUS)
    public Result updateStatus(Integer id,Integer status) {
        estateManagerService.updateStatus(id, status);
        return new Result(true, StatusCode.OK, MessageConstant.ADMIN_UPDATE_SUCCESS);
    }

    @RequestMapping("/del")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    public Result del(Integer id) {
        boolean isSuccess = estateManagerService.removeById(id);
        return new Result(isSuccess, StatusCode.OK, MessageConstant.ADMIN_DELETE_SUCCESS);
    }


}
