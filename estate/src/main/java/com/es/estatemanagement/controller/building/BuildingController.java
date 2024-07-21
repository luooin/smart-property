package com.es.estatemanagement.controller.building;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.es.estatemanagement.common.MessageConstant;
import com.es.estatemanagement.common.PageResult;
import com.es.estatemanagement.common.Result;
import com.es.estatemanagement.common.StatusCode;
import com.es.estatemanagement.common.annotation.Log;
import com.es.estatemanagement.common.enums.BusinessType;
import com.es.estatemanagement.domain.Building;
import com.es.estatemanagement.domain.Community;
import com.es.estatemanagement.service.BuildingService;
import com.es.estatemanagement.service.CommunityService;
import com.es.estatemanagement.util.JWTUtils;
import com.github.pagehelper.Page;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 * @author yang
 * @date 2024/5/20
 */
@RestController
@RequestMapping("/building")
@Api(tags = "栋数管理",description = "只负责接收前端浏览器发送的请求和请求参数，调用service层获取业务逻辑加工处理后的数据",value = "BuildingController类")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @RequestMapping("/find")
    public Result find(){
        List<Building> all = buildingService.findAll();
        return new Result(false,200,"请求成功",all);
    }

    @RequestMapping("/findByCommunityName")
    public Result findByCommunityName(){
        List<Building> all = buildingService.findByCommunityName();
        return new Result(true,200,"请求成功",all);
    }

    @RequestMapping("/findByName")
    public Result findByName(String communityName){
        List<Building> all = buildingService.findByName(communityName);
        return new Result(true,200,"请求成功",all);
    }

    @RequestMapping("/search")
    public PageResult search(@RequestBody Map searchMap, HttpServletRequest request){
        //获取请求头中的令牌
        String token=request.getHeader("token");
        DecodedJWT verify= JWTUtils.verify(token);
        searchMap.put("communityName",verify.getClaim("communityName").asString());
        IPage<Building> page = buildingService.search(searchMap);
//        System.out.println("总记录数 ---- --> " + page.getTotal());
//        System.out.println("总页数 ------ --> " + page.getPages());
//        System.out.println("当前页数 ----- -> " + page.getCurrent());
//        System.out.println("每页记录数 -----> " + page.getSize());
//        System.out.println("当前记录： -----> "+page.getRecords());
        return new PageResult(true, StatusCode.OK, MessageConstant.BUILDING_SEARCH_SUCCESS,page,page.getTotal());
    }

    @RequestMapping("/add")
    @Log(title = "栋数添加", businessType = BusinessType.INSERT)
    public Result add(@RequestBody Building community){
        Boolean add = buildingService.add(community);
        return new Result(true,StatusCode.OK,MessageConstant.BUILDING_ADD_SUCCESS);
    }
    @RequestMapping("/findById")
    public Result findById(Integer id){
        Building community = buildingService.findById(id);
        return new Result(true,StatusCode.OK,MessageConstant.BUILDING_FIND_BY_ID_SUCCESS,community);
    }

    @RequestMapping("/update")
    @Log(title = "栋数修改", businessType = BusinessType.UPDATE)
    public Result update(@RequestBody Building community){
        Boolean add = buildingService.update(community);
        return new Result(true,StatusCode.OK,MessageConstant.BUILDING_UPDATE_SUCCESS);
    }

    @RequestMapping("/del")
    @Log(title = "栋数删除", businessType = BusinessType.DELETE)
    public Result del(@RequestBody List<Integer> ids){
        Boolean flag = buildingService.del(ids);
        return new Result(true,StatusCode.OK,MessageConstant.BUILDING_DELETE_SUCCESS);
    }


}
