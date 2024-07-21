package com.es.estatemanagement.controller.car.parking;

import com.es.estatemanagement.common.MessageConstant;
import com.es.estatemanagement.common.PageResult;
import com.es.estatemanagement.common.Result;
import com.es.estatemanagement.common.StatusCode;
import com.es.estatemanagement.common.annotation.Log;
import com.es.estatemanagement.common.enums.BusinessType;
import com.es.estatemanagement.domain.Car;
import com.es.estatemanagement.domain.Parking;
import com.es.estatemanagement.service.CommunityService;
import com.es.estatemanagement.service.ParkingService;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 车位管理
 * */
@RestController
@RequestMapping("/parking")
@Api(tags = "车位管理",description = "只负责接收前端浏览器发送的请求和请求参数，调用service层获取业务逻辑加工处理后的数据",value = "ParkingController类")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @Autowired
    private CommunityService communityService;

    @RequestMapping("/find")
    public Result find(){
        List<Parking> all = parkingService.findAll();
        return new Result(false,200,"请求成功！！！！",all);
    }

    @RequestMapping("/ownerName")
    public Result ownerName(String carNumber){
        Car car=parkingService.carNumberBiz(carNumber);
        return new Result(false,200,"请求成功！！！！",car);
    }

    @RequestMapping("/search")
    public PageResult search(@RequestBody Map searchMap){
        Page<Parking> page = parkingService.search(searchMap);
        return new PageResult(true, StatusCode.OK, MessageConstant.PARKING_SEARCH_SUCCESS,page.getResult(),page.getTotal());
    }

    @RequestMapping("/findByCommunityName")
    public Result findByCommunityName(){
        List<Parking> all = parkingService.findByCommunityName();
        return new Result(true,200,"请求成功",all);
    }

    @RequestMapping("/add")
    @Log(title = "停车场新增", businessType = BusinessType.INSERT)
    public Result add(@RequestBody Parking parking){
        Boolean add = parkingService.add(parking);
        return new Result(true, StatusCode.OK, MessageConstant.PARKING_ADD_SUCCESS);
    }
    @RequestMapping("/findById")
    public Result findById(Integer id){
        Parking parking = parkingService.findById(id);
        return new Result(true, StatusCode.OK, MessageConstant.PARKING_FIND_BY_ID_SUCCESS,parking);
    }

    @RequestMapping("/update")
    @Log(title = "停车场修改", businessType = BusinessType.UPDATE)

    public Result update(@RequestBody Parking parking){
        Boolean add = parkingService.update(parking);
        return new Result(true, StatusCode.OK, MessageConstant.PARKING_UPDATE_SUCCESS);
    }

    @RequestMapping("/del")
    @Log(title = "停车场删除", businessType = BusinessType.DELETE)
    public Result del(@RequestBody List<Integer> ids){
        Boolean flag = parkingService.del(ids);
        return new Result(true, StatusCode.OK, MessageConstant.PARKING_DELETE_SUCCESS);
    }
}
