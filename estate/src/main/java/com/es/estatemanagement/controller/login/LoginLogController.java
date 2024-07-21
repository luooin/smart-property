package com.es.estatemanagement.controller.login;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.es.estatemanagement.common.MessageConstant;
import com.es.estatemanagement.common.PageResult;
import com.es.estatemanagement.common.StatusCode;
import com.es.estatemanagement.domain.LoginLog;
import com.es.estatemanagement.service.LoginLogService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/loginlog")
@Api(tags = "登入日志管理", description = "只负责接收前端浏览器发送的请求和请求参数，调用service层获取业务逻辑加工处理后的数据", value = "LoginLogController类")
public class LoginLogController {
    @Autowired
    LoginLogService loginLogService;

    @RequestMapping("/search")
    public PageResult index(@RequestBody Map searchMap) {
        Page pageParam = new Page<>(Long.valueOf(Integer.toString((Integer) searchMap.get("page"))),
                Long.valueOf(Integer.toString((Integer) searchMap.get("limit"))));
        IPage<LoginLog> pageModel = loginLogService.selectPage(pageParam, searchMap);
        return new PageResult(true, StatusCode.OK, MessageConstant.ROLE_SEARCH_SUCCESS, pageModel, 0L);
    }


}
