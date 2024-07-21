package com.es.estatemanagement.controller.home;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.es.estatemanagement.common.MessageConstant;
import com.es.estatemanagement.common.PageResult;
import com.es.estatemanagement.common.Result;
import com.es.estatemanagement.common.StatusCode;
import com.es.estatemanagement.common.annotation.Log;
import com.es.estatemanagement.common.enums.BusinessType;
import com.es.estatemanagement.domain.House;
import com.es.estatemanagement.service.CommunityService;
import com.es.estatemanagement.service.HouseService;
import com.es.estatemanagement.service.impl.BuildingServiceImpl;
import com.es.estatemanagement.service.impl.OwnerServiceImpl;
import com.es.estatemanagement.util.JWTUtils;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 房产管理
 */
@RestController
@RequestMapping("/home")
@Api(tags = "房产管理", description = "只负责接收前端浏览器发送的请求和请求参数，调用service层获取业务逻辑加工处理后的数据", value = "HomeController类")
public class HomeController {

    @Autowired
    private HouseService houseService;

    @Autowired
    private CommunityService communityService;

    @Autowired
    private OwnerServiceImpl ownerService;

    @Autowired
    private BuildingServiceImpl buildingService;

    @RequestMapping("/find")
    public Result find() {
        List<House> all = houseService.findAll();
        return new Result(false, 200, "请求成功", all);
    }

    @RequestMapping("/search")
    public PageResult search(@RequestBody Map searchMap,HttpServletRequest request) {
        //获取请求头中的令牌
        String token=request.getHeader("token");
        DecodedJWT verify= JWTUtils.verify(token);
        searchMap.put("communityName",verify.getClaim("communityName").asString());
        Page<House> page = houseService.search(searchMap);
        return new PageResult(true, StatusCode.OK, MessageConstant.HOME_SEARCH_SUCCESS, page.getResult(), page.getTotal());
    }

    @RequestMapping("/addById")
    public Result addById(@RequestParam(defaultValue = "", required = false) String communityName
            , @RequestParam(defaultValue = "", required = false) String buildingName
            , @RequestParam(defaultValue = "", required = false) String ownerName) {
        Integer id = 0;
        if (buildingName != "" && buildingName.length() > 0 && communityName != "" && communityName.length() > 0) {
            id = buildingService.findByName(communityName, buildingName);
        } else if (communityName != "" && communityName.length() > 0) {
            id = communityService.findByName(communityName);
        } else if (ownerName != "" && ownerName.length() > 0) {
            id = ownerService.findByName(ownerName);
        }
        return new Result(true, StatusCode.OK, MessageConstant.HOME_ADD_SUCCESS, id);
    }

    @RequestMapping("/add")
    @Log(title = "房子新增", businessType = BusinessType.INSERT)
    public Result add(@RequestBody House house) {
        Boolean add = houseService.add(house);
        return new Result(true, StatusCode.OK, MessageConstant.HOME_ADD_SUCCESS);
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        House house = houseService.findById(id);
        return new Result(true, StatusCode.OK, MessageConstant.HOME_FIND_BY_ID_SUCCESS, house);
    }

    @RequestMapping("/update")
    @Log(title = "房子修改", businessType = BusinessType.UPDATE)
    public Result update(@RequestBody House house) {
        Boolean add = houseService.update(house);
        return new Result(true, StatusCode.OK, MessageConstant.HOME_UPDATE_SUCCESS);
    }

    @RequestMapping("/del")
    @Log(title = "房子删除", businessType = BusinessType.DELETE)
    public Result del(@RequestBody List<Integer> ids) {
        Boolean flag = houseService.del(ids);
        return new Result(true, StatusCode.OK, MessageConstant.HOME_DELETE_SUCCESS);
    }


}
