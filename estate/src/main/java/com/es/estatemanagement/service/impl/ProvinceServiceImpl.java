package com.es.estatemanagement.service.impl;

import com.es.estatemanagement.domain.Building;
import com.es.estatemanagement.domain.Province;
import com.es.estatemanagement.mapper.AddressCityMapper;
import com.es.estatemanagement.mapper.AddressProvinceMapper;
import com.es.estatemanagement.mapper.AddressTownMapper;
import com.es.estatemanagement.mapper.ProvinceMapper;
import com.es.estatemanagement.service.ProvinceService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;
import java.util.Map;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    ProvinceMapper provinceMapper;

    @Autowired
    AddressProvinceMapper addressProvinceMapper;

    @Autowired
    AddressCityMapper addressCityMapper;

    @Autowired
    AddressTownMapper addressTownMapper;


    @Override
    public List<Province> findAll() {
        List<Province> provinces = provinceMapper.selectAll();
        return provinces;
    }

    @Override
    public Page<Province> search(Map searchMap) {
        //通用Mapper多条件搜索，标准写法
        Example example = new Example(Province.class);//指定查询的表tb_community
        //1.初始化分页条件
        int pageNum = 1;
        int pageSize = 3;
        if (searchMap != null) {
            Example.Criteria criteria = example.createCriteria();//创建查询条件
            //时间区间
            if (StringUtil.isNotEmpty((String) searchMap.get("startTime"))) {
                criteria.andGreaterThanOrEqualTo("returnTime", searchMap.get("startTime"));
            }
            if (StringUtil.isNotEmpty((String) searchMap.get("endTime"))) {
                criteria.andLessThanOrEqualTo("returnTime", searchMap.get("endTime"));
                criteria.andLessThanOrEqualTo("returnTime", searchMap.get("endTime"));
            }
            String provinces = (String) searchMap.get("area");
            if(StringUtil.isNotEmpty(provinces)){
                String[] province_str = provinces.split(",");
                String province = String.join(",", addressProvinceMapper.findByCode(province_str[0]),
                        addressCityMapper.findByCode(province_str[1]), addressTownMapper.findByCode(province_str[2]));
                if(StringUtil.isNotEmpty(province)){
                    criteria.andEqualTo("province",province);
                }
            }
            //名称模糊搜索
            if (StringUtil.isNotEmpty((String) searchMap.get("name"))) {
                criteria.andLike("name", "%" + (String) searchMap.get("name") + "%");
            }
            if ((Integer) searchMap.get("pageNum") != null) {
                pageNum = (Integer) searchMap.get("pageNum");
            }
            if ((Integer) searchMap.get("pageSize") != null) {
                pageSize = (Integer) searchMap.get("pageSize");
            }
        }
        PageHelper.startPage(pageNum, pageSize);//使用PageHelper插件完成分页
        Page<Province> provincesList = (Page<Province>) provinceMapper.selectByExample(example);
        return provincesList;
    }

    @Override
    public Boolean add(Province province) {
        int row = provinceMapper.insert(province);
        if (row > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Province findById(Integer id) {
        return provinceMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean update(Province province) {
        int row = provinceMapper.updateByPrimaryKeySelective(province);
        if (row > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            provinceMapper.deleteByPrimaryKey(id);
        }
        return true;
    }
}
