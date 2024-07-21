package com.es.estatemanagement.controller.car.parkingusage;

import com.es.estatemanagement.common.MessageConstant;
import com.es.estatemanagement.common.PageResult;
import com.es.estatemanagement.common.Result;
import com.es.estatemanagement.common.StatusCode;
import com.es.estatemanagement.domain.Parkingusage;
import com.es.estatemanagement.service.ParkingusageService;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 车位使用管理
 */
@RestController
@RequestMapping("/parkingusage")
@Api(tags = "车位使用管理", description = "只负责接收前端浏览器发送的请求和请求参数，调用service层获取业务逻辑加工处理后的数据", value = "ParkingusageController")
public class ParkingusageController {

    @Resource
    ParkingusageService parkingusageService;

    @RequestMapping("/find")
    public Result find(){
        List<Parkingusage> all = parkingusageService.findAll();
        return new Result(false,200,"请求成功！！！！",all);
    }

    @RequestMapping("/search")
    public PageResult search(@RequestBody Map searchMap){
        Page<Parkingusage> page = parkingusageService.search(searchMap);
        return new PageResult(true, StatusCode.OK, MessageConstant.PARKING_SEARCH_SUCCESS,page.getResult(),page.getTotal());
    }

//    @RequestMapping("/findByCommunityName")
//    public Result findByCommunityName(){
//        List<Parkingusage> all = parkingusageService.findByCommunityName();
//        return new Result(true,200,"请求成功",all);
//    }

    @RequestMapping("/add")
    public Result add(@RequestBody Parkingusage parkingusage){
        Boolean add = parkingusageService.add(parkingusage);
        return new Result(true, StatusCode.OK, MessageConstant.PARKING_ADD_SUCCESS);
    }
    @RequestMapping("/findById")
    public Result findById(Integer id){
        Parkingusage parkingusage = parkingusageService.findById(id);
        return new Result(true, StatusCode.OK, MessageConstant.PARKING_FIND_BY_ID_SUCCESS,parkingusage);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody Parkingusage parkingusage ){
        Boolean add = parkingusageService.update(parkingusage);
        return new Result(true, StatusCode.OK, MessageConstant.PARKING_UPDATE_SUCCESS);
    }

    @RequestMapping("/del")
    public Result del(@RequestBody List<Integer> ids){
        Boolean flag = parkingusageService.del(ids);
        return new Result(true, StatusCode.OK, MessageConstant.PARKING_DELETE_SUCCESS);
    }





}
