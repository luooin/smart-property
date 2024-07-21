package com.es.estatemanagement.service.impl;

import com.es.estatemanagement.config.SnowFlakeIdWorker;
import com.es.estatemanagement.domain.RepairPool;
import com.es.estatemanagement.mapper.RepairPoolMapper;
import com.es.estatemanagement.service.OrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    RepairPoolMapper repairPoolMapper;    //电话报修


    @Override
    public List<RepairPool> findAll() {
        List<RepairPool> poolList = repairPoolMapper.selectAll();
        return poolList;
    }

    @Override
    public Page<RepairPool> search(Map searchMap) {
        //通用Mapper多条件查询
        Example example = new Example(RepairPool.class);
        //初始化分页条件
        int pageNum = 1;
        int pageSize = 5;
        if(searchMap != null){
            Example.Criteria criteria = example.createCriteria();
            //根据报修ID进行查询
            if (StringUtil.isNotEmpty((String) searchMap.get("repairId"))) {
                criteria.andEqualTo("repairId", (String)searchMap.get("repairId"));
            }
            //根据报修人进行模糊查询
            if (StringUtil.isNotEmpty((String) searchMap.get("repairName"))) {
                criteria.andLike("repairName", "%" + (String) searchMap.get("repairName") + "%");
            }
            //根据报修电话查询
            if (StringUtil.isNotEmpty((String) searchMap.get("tel"))) {
                criteria.andEqualTo("tel", (String)searchMap.get("tel"));
            }
            //分页
            if((Integer)searchMap.get("pageNum") != null){
                pageNum = (Integer)searchMap.get("pageNum");
            }
            if((Integer)searchMap.get("pageSize") != null){
                pageSize = (Integer)searchMap.get("pageSize");
            }
        }
        PageHelper.startPage(pageNum,pageSize);
        Page<RepairPool> poolList = (Page<RepairPool>) repairPoolMapper.selectByExample(example);
        return poolList;
    }

    @Override
    public Boolean add(RepairPool repairPool) {
        // 初始化 雪花算法生成报修设置编号
        SnowFlakeIdWorker idWorker = new SnowFlakeIdWorker(1, 0);
        SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String repairId = String.valueOf(Long.valueOf(idWorker.nextId()));
        repairPool.setRepairId(repairId);
        repairPool.setAppointmentTime(formatTime.format(new Date()));
        repairPool.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        repairPool.setRepairChannel(null);
        repairPool.setRepairMaterials(null);
        repairPool.setRepairFee(null);
        repairPool.setPayType(null);
        int row = repairPoolMapper.insert(repairPool);
        if(row > 0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public RepairPool findByRepairId(String repairId) {
        return repairPoolMapper.findById(repairId);
    }


    @Override
    public Boolean update(RepairPool repairPool) {
        SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        repairPool.setAppointmentTime(formatTime.format(new Date()));
        repairPool.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        repairPool.setRepairChannel(null);
        repairPool.setRepairMaterials(null);
        repairPool.setRepairFee(null);
        repairPool.setPayType(null);
        int row = repairPoolMapper.updateByRepairId(repairPool);
        if(row > 0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean del(List<String> poolIds) {
        for (String id : poolIds) {
            repairPoolMapper.deleteByRepairId(id);
        }
        return true;
    }
}
