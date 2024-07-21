package com.es.estatemanagement.controller.car.backlist;

import com.es.estatemanagement.common.MessageConstant;
import com.es.estatemanagement.common.PageResult;
import com.es.estatemanagement.common.Result;
import com.es.estatemanagement.common.StatusCode;
import com.es.estatemanagement.common.annotation.Log;
import com.es.estatemanagement.domain.Backlist;
import com.es.estatemanagement.service.BacklistService;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 黑白名单管理列表
 * */
@RestController
@RequestMapping("/back")
@Api(tags = "黑白名单",description = "只负责接收前端浏览器发送的请求和请求参数，调用service层获取业务逻辑加工处理后的数据",value = "BacklistController")
public class BacklistController {

    @Resource
    BacklistService backlistService;



    @RequestMapping("/search")
    public PageResult search(@RequestBody Map searchMap){
        Page<Backlist> page = backlistService.search(searchMap);
        return new PageResult(true, StatusCode.OK, MessageConstant.BACKLIST_SEARCH_SUCCESS,page.getResult(),page.getTotal());
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Backlist backlist){
        Boolean add = backlistService.add(backlist);
        return new Result(true, StatusCode.OK, MessageConstant.BACKLIST_ADD_SUCCESS);
    }


    @RequestMapping("/findById")
    public Result findById(Integer id){
        Backlist backlist = backlistService.findById(id);
        return new Result(true, StatusCode.OK, MessageConstant.BACKLIST_FIND_BY_ID_SUCCESS,backlist);
    }


    @RequestMapping("/update")
    public Result update(@RequestBody Backlist backlist){
        Boolean add = backlistService.update(backlist);
        return new Result(true, StatusCode.OK, MessageConstant.BACKLIST_UPDATE_SUCCESS);
    }

    @RequestMapping("/del")
    public Result del(@RequestBody List<Long> ids){
        Boolean flag = backlistService.del(ids);
        return new Result(true, StatusCode.OK, MessageConstant.BACKLIST_DELETE_SUCCESS);
    }
}
