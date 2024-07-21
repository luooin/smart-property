package com.es.estatemanagement.controller.repair.order;

import com.es.estatemanagement.common.MessageConstant;
import com.es.estatemanagement.common.PageResult;
import com.es.estatemanagement.common.Result;
import com.es.estatemanagement.common.StatusCode;
import com.es.estatemanagement.domain.Dict;
import com.es.estatemanagement.domain.RepairPool;
import com.es.estatemanagement.domain.SendOrders;
import com.es.estatemanagement.service.BuildingService;
import com.es.estatemanagement.service.RepairPoolService;
import com.es.estatemanagement.service.impl.DictServiceImpl;
import com.es.estatemanagement.service.impl.SendOrdersServiceImpl;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 工单池管理
 * */
@RestController
@RequestMapping("/orderPool")
@Api(tags = "工单池",description = "只负责接收前端浏览器发送的请求和请求参数，调用service层获取业务逻辑加工处理后的数据",value = "OrderPoolController")
public class OrderPoolController {
    @Autowired
    RepairPoolService poolService;

    //报修楼栋
    @Autowired
    BuildingService buildingService;

    //派单 业务实现类
    @Autowired
    SendOrdersServiceImpl sendOrdersService;

    //报修状态
    @Autowired
    DictServiceImpl dictService;


    @RequestMapping("/find")
    public Result find(){
        List<RepairPool> all = poolService.findAll();
        return new Result(false,200,    "请求成功",all);
    }

    @RequestMapping("/findByStatusCd")
    public Result findByStatusCd(){
        List<Dict> byStatusCd = dictService.findByStatusCd();
        return new Result(true, StatusCode.OK, MessageConstant.REPAIRPOOL_SEARCH_STATUS,byStatusCd);
    }

    @RequestMapping("/findBySendOrdersId")
    public Result findBySendOrdersId(){
        List<SendOrders> bySendOrdersId = sendOrdersService.findBySendOrdersId();
        return new Result(true, StatusCode.OK, MessageConstant.REPAIRSETTING_SEARCH_SENDORDERS,bySendOrdersId);
    }

    @RequestMapping("/search")
    public PageResult search(@RequestBody Map searchMap, HttpServletRequest request){
        Page<RepairPool> page = poolService.search(searchMap);
        return new PageResult(true, StatusCode.OK, MessageConstant.REPAIRPOOL_SEARCH_SUCCESS, page.getResult(), page.getTotal());
    }

    @RequestMapping("/add")
    public Result add(@RequestBody RepairPool repairPool){
        Boolean add = poolService.add(repairPool);
        return new Result(true, StatusCode.OK, MessageConstant.REPAIRPOOL_ADD_SUCCESS);
    }

    @RequestMapping("/findByRepairId")
    public Result findByRepairId(String repairId){
        RepairPool community = poolService.findByRepairId(repairId);
        return new Result(true, StatusCode.OK, MessageConstant.REPAIRPOOL_FIND_BY_ID_SUCCESS,community);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody RepairPool repairPool){
        RepairPool repairPoolRow = poolService.findByRepairId(repairPool.getRepairId());
        Boolean add = poolService.update(repairPool);
        return new Result(true, StatusCode.OK, MessageConstant.REPAIRPOOL_UPDATE_SUCCESS,repairPoolRow);
    }

    @RequestMapping("/del")
    public Result del(@RequestBody List<String> poolIds){
        Boolean flag = poolService.del(poolIds);
        return new Result(flag, StatusCode.OK, MessageConstant.REPAIRPOOL_DELETE_SUCCESS);
    }
}
