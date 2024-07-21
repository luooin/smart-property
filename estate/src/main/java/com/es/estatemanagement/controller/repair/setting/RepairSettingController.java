package com.es.estatemanagement.controller.repair.setting;

import com.es.estatemanagement.common.MessageConstant;
import com.es.estatemanagement.common.PageResult;
import com.es.estatemanagement.common.Result;
import com.es.estatemanagement.common.StatusCode;
import com.es.estatemanagement.domain.*;
import com.es.estatemanagement.service.RepairSettingService;
import com.es.estatemanagement.service.impl.AreaServiceImpl;
import com.es.estatemanagement.service.impl.ReturnVisitServiceImpl;
import com.es.estatemanagement.service.impl.SendOrdersServiceImpl;
import com.es.estatemanagement.service.impl.SettingTypeServiceImpl;
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
 * 报修设置管理
 * */
@RestController
@RequestMapping("/repairSetting")
@Api(tags = "报修设置",description = "只负责接收前端浏览器发送的请求和请求参数，调用service层获取业务逻辑加工处理后的数据",value = "RepairSettingController类")
public class RepairSettingController{
    @Autowired
    RepairSettingService settingService;

    //派单 业务实现类
    @Autowired
    SendOrdersServiceImpl sendOrdersService;

    //公共区域 业务实现类
    @Autowired
    AreaServiceImpl areaService;

    //回访设置 业务实现类
    @Autowired
    ReturnVisitServiceImpl returnVisitService;

    //报修设置 业务实现类
    @Autowired
    SettingTypeServiceImpl settingTypeService;

    @RequestMapping("/find")
    public Result find(){
        List<RepairSetting> all = settingService.findAll();
        return new Result(false,200,    "请求成功",all);
    }

    @RequestMapping("/findBySendOrdersId")
    public Result findBySendOrdersId(){
        List<SendOrders> bySendOrdersId = sendOrdersService.findBySendOrdersId();
        return new Result(true, StatusCode.OK, MessageConstant.REPAIRSETTING_SEARCH_SENDORDERS,bySendOrdersId);
    }

    @RequestMapping("/findByAreaId")
    public Result findByAreaId(){
        List<Area> byAreaId = areaService.findByAreaId();
        return new Result(true, StatusCode.OK, MessageConstant.REPAIRSETTING_SEARCH_AREAID,byAreaId);
    }

    @RequestMapping("/findBySettingType")
    public Result findBySettingType(){
        List<SettingType> bySettingType = settingTypeService.findBySettingType();
        return new Result(true, StatusCode.OK, MessageConstant.REPAIRSETTING_SEARCH_SETTINGTYPE,bySettingType);
    }

    @RequestMapping("/findByVisitId")
    public Result findByVisitId(){
        List<ReturnVisit> byVisitId = returnVisitService.findByVisitId();
        return new Result(true, StatusCode.OK, MessageConstant.REPAIRSETTING_SEARCH_RETURNVISIT,byVisitId);
    }

    @RequestMapping("/search")
    public PageResult search(@RequestBody Map searchMap, HttpServletRequest request){
        Page<RepairSetting> page = settingService.search(searchMap);
        return new PageResult(true, StatusCode.OK, MessageConstant.REPAIRSETTING_SEARCH_SUCCESS, page.getResult(), page.getTotal());
    }

    @RequestMapping("/add")
    public Result add(@RequestBody RepairSetting repairSetting){
        Boolean add = settingService.add(repairSetting);
        return new Result(true, StatusCode.OK, MessageConstant.REPAIRSETTING_ADD_SUCCESS);
    }

    @RequestMapping("/findBySettingId")
    public Result findBySettingId(String settingId){
        RepairSetting community = settingService.findBySettingId(settingId);
        return new Result(true, StatusCode.OK, MessageConstant.REPAIRSETTING_FIND_BY_ID_SUCCESS,community);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody RepairSetting repairSetting){
        RepairSetting repairSettingRow = settingService.findBySettingId(repairSetting.getSettingId());
        Boolean add = settingService.update(repairSetting);
        return new Result(true, StatusCode.OK, MessageConstant.REPAIRSETTING_UPDATE_SUCCESS,repairSettingRow);
    }

    @RequestMapping("/del")
    public Result del(@RequestBody List<String> settingIds){
        Boolean flag = settingService.del(settingIds);
        return new Result(flag, StatusCode.OK, MessageConstant.REPAIRSETTING_DELETE_SUCCESS);
    }
}
