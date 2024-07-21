package com.es.estatemanagement.controller.car.entry;


import com.es.estatemanagement.common.MessageConstant;
import com.es.estatemanagement.common.PageResult;
import com.es.estatemanagement.common.StatusCode;
import com.es.estatemanagement.domain.Entry;
import com.es.estatemanagement.service.EntryService;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 进场记录
 * */
@RestController
@RequestMapping("/entry")
@Api(tags = "进场记录",description = "只负责接收前端浏览器发送的请求和请求参数，调用service层获取业务逻辑加工处理后的数据",value = "EntryController")
public class EntryController {

    @Resource
    EntryService entryService;

    @RequestMapping("/search")
    public PageResult search(@RequestBody Map searchMap){
        Page<Entry> page = entryService.search(searchMap);
        return new PageResult(true, StatusCode.OK, MessageConstant.ENTRY_SEARCH_SUCCESS,page.getResult(),page.getTotal());
    }


}
