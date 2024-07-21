package com.es.estatemanagement.controller.address;

import com.es.estatemanagement.common.Result;
import com.es.estatemanagement.domain.AddressCity;
import com.es.estatemanagement.domain.AddressProvince;
import com.es.estatemanagement.domain.AddressTown;
import com.es.estatemanagement.service.impl.AddressCityServiceImpl;
import com.es.estatemanagement.service.impl.AddressProvinceServiceImpl;
import com.es.estatemanagement.service.impl.AddressTownServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 省市区管理
 */
@RestController
@RequestMapping("/address")
@Api(tags = "省市区管理", description = "只负责接收前端浏览器发送的请求和请求参数，调用service层获取业务逻辑加工处理后的数据", value = "AddressController类")
public class AddressController {
    @Autowired
    AddressProvinceServiceImpl addressProvinceService;
    @Autowired
    AddressCityServiceImpl addressCityService;
    @Autowired
    AddressTownServiceImpl addressTownService;

    @RequestMapping("/find")
    public Result find(@RequestBody Map requestDate) {
        Object all = null;
        if (requestDate.get("type").equals("province")) {
            List<AddressProvince> addressProvinces = addressProvinceService.findAll();
            all = addressProvinces;
        } else if (requestDate.get("type").equals("city")) {
            List<AddressCity> addressCities = addressCityService.findByProvinceCode((String) requestDate.get("province_code"));
            all = addressCities;
        } else if (requestDate.get("type").equals("area")) {
            List<AddressTown> addressTowns = addressTownService.findByCityCode((String) requestDate.get("city_code"));
            all = addressTowns;
        }
        return new Result(true, 200, "请求成功!!!", all);
    }

    @RequestMapping("/findByCode")
    public Result findByCode(@RequestBody Map requestDate) {
        String all = null;
        if (requestDate.get("type").equals("province")) {
            all = addressProvinceService.findByCode((String) requestDate.get("provinceCode"));
        } else if (requestDate.get("type").equals("city")) {
            all = addressCityService.findByCode((String) requestDate.get("cityCode"));
        } else if (requestDate.get("type").equals("area")) {
            all = addressTownService.findByCode((String) requestDate.get("areaCode"));
        }
        return new Result(true, 200, "请求成功!!!",all);
    }
}
