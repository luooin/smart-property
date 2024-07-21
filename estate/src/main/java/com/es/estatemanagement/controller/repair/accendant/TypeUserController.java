package com.es.estatemanagement.controller.repair.accendant;

import com.es.estatemanagement.common.MessageConstant;
import com.es.estatemanagement.common.PageResult;
import com.es.estatemanagement.common.Result;
import com.es.estatemanagement.common.StatusCode;
import com.es.estatemanagement.domain.Online;
import com.es.estatemanagement.domain.RepairTypeUser;
import com.es.estatemanagement.service.OnlineService;
import com.es.estatemanagement.service.RepairTypeUserService;
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
@RequestMapping("/typeUser")
@Api(tags = "绑定维修师傅",description = "只负责接收前端浏览器发送的请求和请求参数，调用service层获取业务逻辑加工处理后的数据",value = "TypeUserController类")
public class TypeUserController {

    @Autowired
    RepairTypeUserService repairTypeUserService;

    //报修状态
    @Autowired
    OnlineService onlineService;

    @RequestMapping("/find")
    public Result find(){
        List<RepairTypeUser> all = repairTypeUserService.findAll();
        return new Result(false,200,    "请求成功",all);
    }
    @RequestMapping("/search")
    public PageResult search(@RequestBody Map searchMap, HttpServletRequest request){
        Page<RepairTypeUser> page = repairTypeUserService.search(searchMap);
        return new PageResult(true, StatusCode.OK, MessageConstant.REPAIRTYPEUSER_SEARCH_SUCCESS, page.getResult(), page.getTotal());
    }

    @RequestMapping("/findByLineId")
    public Result findByStatusCd(){
        List<Online> byStatusCd = onlineService.findByLineId();
        return new Result(true, StatusCode.OK, MessageConstant.REPAIRPOOL_SEARCH_STATUS,byStatusCd);
    }

    @RequestMapping("/findByRepairId")
    public Result findByTypeUserId(String typeUserId){
        RepairTypeUser typeUser = repairTypeUserService.findByTypeUserId(typeUserId);
        return new Result(true, StatusCode.OK, MessageConstant.REPAIRTYPEUSER_FIND_BY_ID_SUCCESS,typeUser);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody RepairTypeUser repairTypeUser){
        RepairTypeUser repairTypeUserRow = repairTypeUserService.findByTypeUserId(repairTypeUser.getTypeUserId());
        Boolean add = repairTypeUserService.update(repairTypeUser);
        return new Result(true, StatusCode.OK, MessageConstant.REPAIRPOOL_UPDATE_SUCCESS,add);
    }
    
    @RequestMapping("/del")
    public Result del(@RequestBody List<String> typeUserId){
        Boolean flag = repairTypeUserService.del(typeUserId);
        return new Result(flag, StatusCode.OK, MessageConstant.REPAIRTYPEUSER_DELETE_SUCCESS);
    }
}
