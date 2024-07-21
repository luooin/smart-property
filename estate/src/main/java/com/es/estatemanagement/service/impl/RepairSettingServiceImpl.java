package com.es.estatemanagement.service.impl;

import com.es.estatemanagement.domain.RepairSetting;
import com.es.estatemanagement.mapper.RepairSettingMapper;
import com.es.estatemanagement.mapper.SendOrdersMapper;
import com.es.estatemanagement.service.RepairSettingService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


/**
 * 报修设置实现类
 */
@Service
public class RepairSettingServiceImpl implements RepairSettingService {

    @Autowired
    RepairSettingMapper repairSettingMapper;

    @Autowired
    SendOrdersMapper sendOrdersMapper;

    @Override
    public List<RepairSetting> findAll() {
        List<RepairSetting> settingList = repairSettingMapper.selectAll();
        return settingList;
    }

    @Override
    public Page<RepairSetting> search(Map searchMap) {
        //通用Mapper多条件搜索，标准写法
        Example example = new Example(RepairSetting.class);
        //初始化分页条件
        int pageNum = 1;
        int pageSize = 5;
        if (searchMap != null) {
            Example.Criteria criteria = example.createCriteria();   //创建查询条件
            //根据类型名称模糊搜索
            if (StringUtil.isNotEmpty((String) searchMap.get("repairType"))) {
                criteria.andLike("repairType", "%" + (String) searchMap.get("repairType") + "%");
            }

            //根据派单方式编号查询
            if (StringUtil.isNotEmpty((String) searchMap.get("sendOrdersName"))) {
                criteria.andEqualTo("sendOrdersId", sendOrdersMapper.findByOrdersName((String) searchMap.get("sendOrdersName")));
            }
            //根据派单类型查询
            if (StringUtil.isNotEmpty((String) searchMap.get("sendOrdersType"))) {
                criteria.andEqualTo("sendOrdersType", (String) searchMap.get("sendOrdersType"));
            }
            //分页
            if ((Integer) searchMap.get("pageNum") != null) {
                pageNum = (Integer) searchMap.get("pageNum");
            }

            if ((Integer) searchMap.get("pageSize") != null) {
                pageSize = (Integer) searchMap.get("pageSize");
            }
        }
        PageHelper.startPage(pageNum, pageSize);
        Page<RepairSetting> repairSettingList = (Page<RepairSetting>) repairSettingMapper.selectByExample(example);
        return repairSettingList;
    }

    @Override
    public Boolean add(RepairSetting repairSetting) {
        repairSetting.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        repairSetting.setPayFeeFlag("F");
        int row = repairSettingMapper.insert(repairSetting);
        if (row > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public RepairSetting findBySettingId(String settingId) {
        return repairSettingMapper.findById(settingId);
    }

    @Override
    public Boolean update(RepairSetting repairSetting) {
        int row = repairSettingMapper.updateBySettingId(repairSetting);
        if (row > 0) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public Boolean del(List<String> settingIds) {
        for (String id : settingIds) {
            repairSettingMapper.deleteBySettingId(id);
        }
        return true;
    }
}
