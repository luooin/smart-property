package com.es.estatemanagement.controller.car.parkingapplication;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.es.estatemanagement.common.MessageConstant;
import com.es.estatemanagement.common.PageResult;
import com.es.estatemanagement.common.Result;
import com.es.estatemanagement.common.StatusCode;
import com.es.estatemanagement.domain.Car;
import com.es.estatemanagement.domain.CarType;
import com.es.estatemanagement.domain.Parking;
import com.es.estatemanagement.domain.ParkingApplication;
import com.es.estatemanagement.mapper.ParkingApplicationMapper;
import com.es.estatemanagement.service.CarService;
import com.es.estatemanagement.service.ParkingApplicationService;
import com.es.estatemanagement.util.JWTUtils;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 申请车位管理
 */
@RestController
@RequestMapping("/parkingapplication")
@Api(tags = "申请车位管理列表", description = "只负责接收前端浏览器发送的请求和请求参数，调用service层获取业务逻辑加工处理后的数据", value = "ParkingApplicationController")
public class ParkingApplicationController{

    @Resource
    ParkingApplicationService parkingApplicationService;
    @Resource
    CarService carService;
    @Resource
    ParkingApplicationMapper parkingApplicationMapper;

    @RequestMapping("/findOwnerName")
    public Result find(HttpServletRequest request){
        //获取请求头中的令牌
        String token=request.getHeader("token");
        DecodedJWT verify= JWTUtils.verify(token);
//        List<Owner> all = ownerService.findAll(verify.getClaim("communityName").asString());
        return new Result(false,200,"请求成功");
    }


    @RequestMapping("/carTypeFind")
    public Result carTypeFind() {
        List<CarType> carTypeList = carService.carTypeListBiz();
        return new Result(false, 200, "请求成功！！！！", carTypeList);
    }

    @RequestMapping("/find")
    public Result find() {
        List<ParkingApplication> parkingApplicationList = parkingApplicationService.findAll();
        return new Result(false, 200, "请求成功！！！！", parkingApplicationList);
    }



    @RequestMapping("/search")
    public PageResult search(@RequestBody Map searchMap) {
        Page<ParkingApplication> page = parkingApplicationService.search(searchMap);
        return new PageResult(true, StatusCode.OK, MessageConstant.PARKINGPPLICATION_SEARCH_SUCCESS, page.getResult(), page.getTotal());
    }

    @RequestMapping("/add")
    public Result add(@RequestBody ParkingApplication parkingApplication) {
        Boolean add = parkingApplicationService.add(parkingApplication);
        return new Result(true, StatusCode.OK, MessageConstant.PARKINGPPLICATION_ADD_SUCCESS);
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        ParkingApplication parkingApplication = parkingApplicationService.findById(id);

        return new Result(true, StatusCode.OK, MessageConstant.PARKINGPPLICATION_BY_ID_SUCCESS, parkingApplication);
    }


    @RequestMapping("/update")
    public Result update(@RequestBody ParkingApplication parkingApplication) {
        Boolean bool = parkingApplicationService.update(parkingApplication);
//        if(bool){
//            Car car=new Car();
//            int a=parkingApplication.getCarId();
//            String b=parkingApplication.getCarParkingName();
//            car.setId(a);
//            car.setPsName(b);
//            int carPsName=parkingApplicationMapper.addCarPsName(car);
//            Parking parking=new Parking();
//            Integer c=parkingApplication.getParkingId();
//            Integer d=parkingApplication.getParkingState();
//            parking.setId(c);
//            parking.setStatus(d);
//            int state=parkingApplicationMapper.updateStatus(parking);
//        }
                return new Result(true, StatusCode.OK, MessageConstant.PARKINGPPLICATION_UPDATE_SUCCESS);
    }

    @RequestMapping("/del")
    public Result del(@RequestBody List<Long> ids) {
        Boolean flag = parkingApplicationService.del(ids);
        return new Result(true, StatusCode.OK, MessageConstant.PARKINGPPLICATION_DELETE_SUCCESS);
    }

    @RequestMapping("/carOwnerId")
    public Result carOwnerId(String carNumber){
        Car car=parkingApplicationService.carOwnerIdBiz(carNumber);
        return new Result(true, StatusCode.OK, MessageConstant.PARKINGPPLICATION_DELETE_SUCCESS,car);
    }
    @RequestMapping("/parkingName")
    public Result parkingName(String name){
        Parking parking=parkingApplicationService.parkingNameBiz(name);
        return new Result(true, StatusCode.OK, MessageConstant.PARKINGPPLICATION_DELETE_SUCCESS,parking);
    }
}