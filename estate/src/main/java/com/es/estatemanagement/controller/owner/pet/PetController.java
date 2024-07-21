package com.es.estatemanagement.controller.owner.pet;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.es.estatemanagement.common.MessageConstant;
import com.es.estatemanagement.common.PageResult;
import com.es.estatemanagement.common.Result;
import com.es.estatemanagement.common.StatusCode;
import com.es.estatemanagement.common.annotation.Log;
import com.es.estatemanagement.common.enums.BusinessType;
import com.es.estatemanagement.domain.Pet;
import com.es.estatemanagement.service.impl.PetServiceImpl;
import com.es.estatemanagement.util.JWTUtils;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pet")
@Api(tags = "宠物管理",description = "只负责接收前端浏览器发送的请求和请求参数，调用service层获取业务逻辑加工处理后的数据",value = "VehicleController类")
public class PetController {

    @Autowired
    PetServiceImpl petService;


    @RequestMapping("/find")
    public Result find(){
        List<Pet> all = petService.findAll();
        return new Result(false,200,    "请求成功",all);
    }


    @RequestMapping("/search")
    public PageResult search(@RequestBody Map searchMap, HttpServletRequest request){
        //获取请求头中的令牌
        String token=request.getHeader("token");
        DecodedJWT verify= JWTUtils.verify(token);
        searchMap.put("communityName",verify.getClaim("communityName").asString());
        Page<Pet> page = petService.search(searchMap);
        System.out.println(page.getResult()+"\n"+page.getTotal());
        return new PageResult(true, StatusCode.OK, MessageConstant.PET_SEARCH_SUCCESS,page.getResult(),page.getTotal());
    }

    @RequestMapping("/add")
    @Log(title = "宠物新增", businessType = BusinessType.INSERT)
    public Result add(@RequestBody Pet pet){
        Boolean add = petService.add(pet);
        return new Result(true,StatusCode.OK,MessageConstant.PET_ADD_SUCCESS);
    }

    @RequestMapping("/findById")

    public Result findById(Integer id){
        Pet pet = petService.findById(id);
        return new Result(true,StatusCode.OK,MessageConstant.PET_FIND_BY_ID_SUCCESS,pet);
    }

    @RequestMapping("/update")
    @Log(title = "宠物修改", businessType = BusinessType.UPDATE)

    public Result update(@RequestBody Pet pet){
        Boolean add = petService.update(pet);
        return new Result(true,StatusCode.OK,MessageConstant.PET_UPDATE_SUCCESS);
    }

    @RequestMapping("/del")
    @Log(title = "宠物删除", businessType = BusinessType.DELETE)
    public Result del(@RequestBody List<Integer> ids){
        Boolean flag = petService.del(ids);
        return new Result(true,StatusCode.OK,MessageConstant.PET_DELETE_SUCCESS);
    }
}
