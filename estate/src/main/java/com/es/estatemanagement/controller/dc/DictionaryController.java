package com.es.estatemanagement.controller.dc;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.es.estatemanagement.common.MessageConstant;
import com.es.estatemanagement.common.PageResult;
import com.es.estatemanagement.common.Result;
import com.es.estatemanagement.common.StatusCode;
import com.es.estatemanagement.domain.Dictionary;
import com.es.estatemanagement.service.DictionaryService;
import com.es.estatemanagement.util.JWTUtils;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 字典管理
 */
@RestController
@RequestMapping("/dictionary")
@Api(tags = "字典管理", description = "只负责接收前端浏览器发送的请求和请求参数，调用service层获取业务逻辑加工处理后的数据", value = "DictionaryController")
public class DictionaryController {

    @Resource
    DictionaryService dictionaryService;

    @RequestMapping("/find")
    public Result find(){
        List<Dictionary> dictionaryList = dictionaryService.findDictionaryBiz();
        return new Result(false,200,"请求成功！！！！",dictionaryList);
    }

    @RequestMapping("/search")
    public PageResult search(@RequestBody Map searchMap){
        Page<Dictionary> page = dictionaryService.search(searchMap);
        return new PageResult(true, StatusCode.OK, MessageConstant.DICTIONARY_SEARCH_SUCCESS,page.getResult(),page.getTotal());
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Dictionary dictionary, HttpSession session){
        Object ob = session.getAttribute("estateManager");
        String token = ob.toString();
        DecodedJWT verify = JWTUtils.verify(token);
        String id = verify.getClaim("id").asString();
        dictionary.setCreatedBy(new Integer(id));
        dictionary.setCreationTime(new Date());
        dictionary.setParentId("1");
        Boolean add = dictionaryService.add(dictionary);
        return new Result(true, StatusCode.OK, MessageConstant.DICTIONARY_ADD_SUCCESS);
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
        Dictionary dictionary = dictionaryService.findById(id);
        return new Result(true, StatusCode.OK, MessageConstant.DICTIONARY_FIND_BY_ID_SUCCESS,dictionary);
    }

    public Result update(@RequestBody Dictionary dictionary, HttpSession session){
        Object ob = session.getAttribute("estateManager");
        String token = ob.toString();
        DecodedJWT verify = JWTUtils.verify(token);
        String id = verify.getClaim("id").asString();
        dictionary.setModifyBy(new Integer(id));
        dictionary.setModifyDate(new Date());
        dictionary.setParentId("1");
        Boolean add = dictionaryService.updateBiz(dictionary);
        return new Result(true, StatusCode.OK, MessageConstant.DICTIONARY_UPDATE_SUCCESS);
    }

    @RequestMapping("/del")
    public Result del(@RequestBody List<Long> ids){
        Boolean flag = dictionaryService.delBiz(ids);
        return new Result(true, StatusCode.OK, MessageConstant.DICTIONARY_DELETE_SUCCESS);
    }

    @RequestMapping("/dictionarydata")
    public Result dictionaryData(String dictionaryType){
        List<Dictionary> dictionaryList=dictionaryService.findByParentIdBiz(dictionaryType);
        return new Result(true, StatusCode.OK, MessageConstant.DICTIONARYDATA_SEARCH_SUCCESS,dictionaryList);
    }

}
