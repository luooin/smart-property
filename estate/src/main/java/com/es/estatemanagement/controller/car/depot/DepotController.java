package com.es.estatemanagement.controller.car.depot;

import com.es.estatemanagement.common.MessageConstant;
import com.es.estatemanagement.common.PageResult;
import com.es.estatemanagement.common.Result;
import com.es.estatemanagement.common.StatusCode;
import com.es.estatemanagement.common.annotation.Log;
import com.es.estatemanagement.common.enums.BusinessType;
import com.es.estatemanagement.domain.Depot;
import com.es.estatemanagement.domain.Parking;
import com.es.estatemanagement.service.CommunityService;
import com.es.estatemanagement.service.ParkingService;
import com.es.estatemanagement.service.impl.DepotServiceImpl;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 停车场管理
 * */
@RestController
@RequestMapping("/depot")
@Api(tags = "停车场管理列表",description = "只负责接收前端浏览器发送的请求和请求参数，调用service层获取业务逻辑加工处理后的数据",value = "DepotController")
public class DepotController {


    @Autowired
    DepotServiceImpl depotService;


    @Autowired
    private ParkingService parkingService;

    @Autowired
    private CommunityService communityService;

    @RequestMapping("/quantity")
    public Result quantityBiz(String communityName){
        List<Parking> all = parkingService.quantityBiz(communityName);
        return new Result(false,200,"请求成功！！！！",all);
    }

    @RequestMapping("/find")
    public Result find(){
        List<Depot> depotList = depotService.findAll();
        return new Result(false,200,"请求成功！！！！",depotList);
    }

    @RequestMapping("/search")
    public PageResult search(@RequestBody Map searchMap){
        Page<Depot> page = depotService.search(searchMap);
        return new PageResult(true, StatusCode.OK, MessageConstant.DEPOT_SEARCH_SUCCESS,page.getResult(),page.getTotal());
    }

    @RequestMapping("/add")
    @Log(title = "停车场新增", businessType = BusinessType.INSERT)
    public Result add(@RequestBody Depot depot){
        Boolean add = depotService.add(depot);
        return new Result(true, StatusCode.OK, MessageConstant.DEPOT_ADD_SUCCESS);
    }


    @RequestMapping("/findById")
    public Result findById(Integer id){
        Depot depot = depotService.myDepotIdBiz(id);
        return new Result(true, StatusCode.OK, MessageConstant.DEPOT_FIND_BY_ID_SUCCESS,depot);
    }


    @RequestMapping("/update")
    @Log(title = "停车场修改", businessType = BusinessType.UPDATE)
    public Result update(@RequestBody Depot depot){
        Boolean add = depotService.update(depot);
        return new Result(true, StatusCode.OK, MessageConstant.DEPOT_UPDATE_SUCCESS);
    }

    @RequestMapping("/del")
    @Log(title = "停车场修改", businessType = BusinessType.DELETE)
    public Result del(@RequestBody List<Integer> ids){
        Boolean flag = depotService.del(ids);
        return new Result(true, StatusCode.OK, MessageConstant.DEPOT_DELETE_SUCCESS);
    }


}
