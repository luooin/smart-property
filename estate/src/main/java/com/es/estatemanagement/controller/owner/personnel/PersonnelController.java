package com.es.estatemanagement.controller.owner.personnel;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.es.estatemanagement.common.MessageConstant;
import com.es.estatemanagement.common.PageResult;
import com.es.estatemanagement.common.Result;
import com.es.estatemanagement.common.StatusCode;
import com.es.estatemanagement.common.annotation.Log;
import com.es.estatemanagement.common.enums.BusinessType;
import com.es.estatemanagement.domain.Owner;
import com.es.estatemanagement.service.impl.OwnerServiceImpl;
import com.es.estatemanagement.util.JWTUtils;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 人员管理
 */
@RestController
@RequestMapping("/owner")
@Api(tags = "人员管理",description = "只负责接收前端浏览器发送的请求和请求参数，调用service层获取业务逻辑加工处理后的数据",value = "PersonnelController类")
public class PersonnelController {
    @Autowired
    private OwnerServiceImpl ownerService;

    @RequestMapping("/find")
    public Result find(HttpServletRequest request){
        //获取请求头中的令牌
        String token=request.getHeader("token");
        DecodedJWT verify= JWTUtils.verify(token);
        List<Owner> all = ownerService.findAll(verify.getClaim("communityName").asString());
        return new Result(false,200,"请求成功",all);
    }

    @RequestMapping("/search")
    public PageResult search(@RequestBody Map searchMap,HttpServletRequest request){
        //获取请求头中的令牌
        String token=request.getHeader("token");
        DecodedJWT verify= JWTUtils.verify(token);
        searchMap.put("communityName",verify.getClaim("communityName").asString());
        Page<Owner> page = ownerService.search(searchMap);
        System.out.println(page.getResult()+"\n"+page.getTotal());
        return new PageResult(true, StatusCode.OK, MessageConstant.OWNER_SEARCH_SUCCESS,page.getResult(),page.getTotal());
    }

    @RequestMapping("/add")
    @Log(title = "人员新增", businessType = BusinessType.INSERT)
    public Result add(@RequestBody Owner owner){
        Boolean add = ownerService.add(owner);
        return new Result(true,StatusCode.OK,MessageConstant.OWNER_ADD_SUCCESS);
    }
    @RequestMapping("/findById")
    public Result findById(Integer id){
        Owner community = ownerService.findById(id);
        return new Result(true,StatusCode.OK,MessageConstant.OWNER_FIND_BY_ID_SUCCESS,community);
    }

    @RequestMapping("/update")
    @Log(title = "人员修改", businessType = BusinessType.UPDATE)
    public Result update(@RequestBody Owner owner){
        Boolean add = ownerService.update(owner);
        return new Result(true,StatusCode.OK,MessageConstant.OWNER_UPDATE_SUCCESS);
    }

    @RequestMapping("/del")
    @Log(title = "人员删除", businessType = BusinessType.DELETE)
    public Result del(@RequestBody List<Integer> ids){
        Boolean flag = ownerService.del(ids);
        return new Result(true,StatusCode.OK,MessageConstant.OWNER_DELETE_SUCCESS);
    }
}
