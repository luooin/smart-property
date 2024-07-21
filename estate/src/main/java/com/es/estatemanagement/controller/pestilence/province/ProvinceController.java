package com.es.estatemanagement.controller.pestilence.province;

import com.es.estatemanagement.common.MessageConstant;
import com.es.estatemanagement.common.PageResult;
import com.es.estatemanagement.common.Result;
import com.es.estatemanagement.common.StatusCode;
import com.es.estatemanagement.domain.Province;
import com.es.estatemanagement.service.impl.ProvinceServiceImpl;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 返省上报管理
 * </p>
 * @author yang
 * @date 2024/5/20
 */
@RestController
@RequestMapping("/province")
@Api(tags = "返省上报管理", description = "只负责接收前端浏览器发送的请求和请求参数，调用service层获取业务逻辑加工处理后的数据", value = "ProvinceController类")
public class ProvinceController {

    @Autowired
    ProvinceServiceImpl provinceService;

    @RequestMapping("/find")
    public Result find() {
        List<Province> all = provinceService.findAll();
        return new Result(true, 200, "请求成功", all);
    }

    @RequestMapping("/search")
    public PageResult search(@RequestBody Map searchMap) {
        Page<Province> page = provinceService.search(searchMap);
        return new PageResult(true, StatusCode.OK, MessageConstant.PROVINCE_SEARCH_SUCCESS, page.getResult(), page.getTotal());
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Province province) {
        Boolean add = provinceService.add(province);
        return new Result(true, StatusCode.OK, MessageConstant.PROVINCE_ADD_SUCCESS);
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        Province surveyData = provinceService.findById(id);
        return new Result(true, StatusCode.OK, MessageConstant.PROVINCE_FIND_BY_ID_SUCCESS, surveyData);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody Province province) {
        Boolean add = provinceService.update(province);
        return new Result(true, StatusCode.OK, MessageConstant.PROVINCE_UPDATE_SUCCESS);
    }

    @RequestMapping("/del")
    public Result del(@RequestBody List<Integer> ids) {
        Boolean flag = provinceService.del(ids);
        return new Result(true, StatusCode.OK, MessageConstant.PROVINCE_DELETE_SUCCESS);
    }

}
