package com.es.estatemanagement.controller.community;

import com.es.estatemanagement.common.MessageConstant;
import com.es.estatemanagement.common.PageResult;
import com.es.estatemanagement.common.Result;
import com.es.estatemanagement.common.StatusCode;
import com.es.estatemanagement.common.annotation.Log;
import com.es.estatemanagement.common.enums.BusinessType;
import com.es.estatemanagement.domain.Community;
import com.es.estatemanagement.exception.GlobalExceptionHandler;
import com.es.estatemanagement.service.CommunityService;
import com.github.pagehelper.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 小区管理控制层:只负责接收前端浏览器发送的请求和请求参数，调用service层获取业务逻辑加工处理后的数据
 * </p>
 * @author yang
 * @date 2024/5/20
 */
@RestController
@RequestMapping("/community")
@Api(tags = "小区管理控制层",description = "只负责接收前端浏览器发送的请求和请求参数，调用service层获取业务逻辑加工处理后的数据",value = "CommunityController类")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @ApiOperation("小区管理不分页查询")
    @RequestMapping("/find")
    public Result find(){
        List<Community> all = communityService.findAll();
        return new Result(false,200,"请求成功",all);
    }
    @ApiOperation("小区管理分页查询")
    @RequestMapping("/search")
    public PageResult search(@RequestBody Map searchMap){
        Page<Community> page = communityService.search(searchMap);
        System.out.println(page.getResult()+"\n"+page.getTotal());
        return new PageResult(true, StatusCode.OK, MessageConstant.COMMUNITY_SEARCH_SUCCESS,page.getResult(),page.getTotal());
    }

    @RequestMapping("/add")
    @Log(title = "小区新增", businessType = BusinessType.INSERT)
    public Result add(@RequestBody Community community){
        Boolean add = communityService.add(community);
        return new Result(true,StatusCode.OK,MessageConstant.COMMUNITY_ADD_SUCCESS);
    }
    @RequestMapping("/findById")
    public Result findById(Integer id){
        Community community = communityService.findById(id);
        return new Result(true,StatusCode.OK,MessageConstant.COMMUNITY_FIND_BY_ID_SUCCESS,community);
    }

    @RequestMapping("/update")
    @Log(title = "小区修改", businessType = BusinessType.UPDATE)
    public Result update(@RequestBody Community community){
        Boolean add = communityService.update(community);
        return new Result(true,StatusCode.OK,MessageConstant.COMMUNITY_UPDATE_SUCCESS);
    }
    // /community/updateStatus/0/1
    @ApiOperation(value = "根据id修改是否启用")
    @RequestMapping("/updateStatus/{status}")
    public Result updateStatus(@ApiParam(value = "状态值",required = false) @PathVariable("status") String status,
                               @ApiParam(value = "选中的小区id",required = false) @RequestParam("id") String id){
        Boolean flag = communityService.updateStatus(status,Integer.parseInt(id));
        return new Result(true,StatusCode.OK,MessageConstant.COMMUNITY_UPDATE_STATUS_SUCCESS);
    }
    @RequestMapping("/del")
    @Log(title = "小区删除", businessType = BusinessType.DELETE)
    public Result del(@RequestBody List<Integer> ids){
        Boolean flag = communityService.del(ids);
        return new Result(true,StatusCode.OK,MessageConstant.COMMUNITY_DELETE_SUCCESS);
    }
}
