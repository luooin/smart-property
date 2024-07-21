package com.es.estatemanagement.controller.owner.vehicle;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.es.estatemanagement.common.MessageConstant;
import com.es.estatemanagement.common.PageResult;
import com.es.estatemanagement.common.Result;
import com.es.estatemanagement.common.StatusCode;
import com.es.estatemanagement.domain.Car;
import com.es.estatemanagement.service.impl.CarServiceImpl;
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
@RequestMapping("/car")
@Api(tags = "车辆管理",description = "只负责接收前端浏览器发送的请求和请求参数，调用service层获取业务逻辑加工处理后的数据",value = "VehicleController类")
public class VehicleController {
    @Autowired
    CarServiceImpl carService;

    @RequestMapping("/find")
    public Result find(){
        List<Car> all = carService.findAll();
        return new Result(false,200,    "请求成功",all);
    }


    @RequestMapping("/search")
    public PageResult search(@RequestBody Map searchMap, HttpServletRequest request){
        //获取请求头中的令牌
        String token=request.getHeader("token");
        DecodedJWT verify= JWTUtils.verify(token);
        searchMap.put("communityName",verify.getClaim("communityName").asString());
        Page<Car> page = carService.search(searchMap);
        System.out.println(page.getResult()+"\n"+page.getTotal());
        return new PageResult(true, StatusCode.OK, MessageConstant.CAR_SEARCH_SUCCESS,page.getResult(),page.getTotal());
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Car car){
        Boolean add = carService.add(car);
        return new Result(true, StatusCode.OK, MessageConstant.CAR_ADD_SUCCESS);
    }
    @RequestMapping("/findById")
    public Result findById(Integer id){
        Car car = carService.findById(id);
        return new Result(true, StatusCode.OK, MessageConstant.CAR_FIND_BY_ID_SUCCESS,car);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody Car car){
        Boolean add = carService.update(car);
        return new Result(true, StatusCode.OK, MessageConstant.CAR_UPDATE_SUCCESS);
    }

    @RequestMapping("/del")
    public Result del(@RequestBody List<Integer> ids){
        Boolean flag = carService.del(ids);
        return new Result(true, StatusCode.OK, MessageConstant.CAR_DELETE_SUCCESS);
    }
}
