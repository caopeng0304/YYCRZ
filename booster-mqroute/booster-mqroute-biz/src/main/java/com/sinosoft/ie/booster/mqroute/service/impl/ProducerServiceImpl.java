package com.sinosoft.ie.booster.mqroute.service.impl;

import com.sinosoft.ie.booster.mqroute.entity.ProducerEntity;
import com.sinosoft.ie.booster.mqroute.mapper.ProducerMapper;
import com.sinosoft.ie.booster.mqroute.service.ProducerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.visualdev.util.*;
import com.sinosoft.ie.booster.mqroute.model.producer.ProducerCrForm;
import com.sinosoft.ie.booster.mqroute.model.producer.ProducerPagination;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.hutool.core.util.StrUtil;
import java.util.*;

/**
 *
 * producer
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-03-16 15:53:23
 */
@Service
public class ProducerServiceImpl extends ServiceImpl<ProducerMapper, ProducerEntity> implements ProducerService {


    @Override
    public List<ProducerEntity> getList(ProducerPagination producerPagination){
        QueryWrapper<ProducerEntity> queryWrapper=new QueryWrapper<>();
        //关键字（账户、姓名、手机）
        if(!"null".equals(String.valueOf(producerPagination.getId()))){
            queryWrapper.lambda().and(t->t.like(ProducerEntity::getId,producerPagination.getId()));
        }

        if(!"null".equals(String.valueOf(producerPagination.getProducename()))){
            queryWrapper.lambda().and(t->t.like(ProducerEntity::getProducename,producerPagination.getProducename()));
        }

        //排序
        if(StrUtil.isEmpty(producerPagination.getSidx())){
            queryWrapper.lambda().orderByDesc(ProducerEntity::getId);
        }else{
            queryWrapper="asc".equals(producerPagination.getSort().toLowerCase())?queryWrapper.orderByAsc(producerPagination.getSidx()):queryWrapper.orderByDesc(producerPagination.getSidx());
        }
        Page<ProducerEntity> page=new Page<>(producerPagination.getCurrentPage(), producerPagination.getPageSize());
        IPage<ProducerEntity> userIPage=this.page(page,queryWrapper);
        return producerPagination.setData(userIPage.getRecords(),userIPage.getTotal());
    }
    @Override
    public List<ProducerEntity> getTypeList(ProducerPagination producerPagination,String dataType){
        QueryWrapper<ProducerEntity> queryWrapper=new QueryWrapper<>();
        //关键字（账户、姓名、手机）
        if(!"null".equals(String.valueOf(producerPagination.getId()))){
            queryWrapper.lambda().and(t->t.like(ProducerEntity::getId,producerPagination.getId()));
        }

        if(!"null".equals(String.valueOf(producerPagination))){
            queryWrapper.lambda().and(t->t.like(ProducerEntity::getProducename,producerPagination.getProducename()));
        }

        //排序
        if(StrUtil.isEmpty(producerPagination.getSidx())){
            queryWrapper.lambda().orderByDesc(ProducerEntity::getId);
        }else{
            queryWrapper="asc".equals(producerPagination.getSort().toLowerCase())?queryWrapper.orderByAsc(producerPagination.getSidx()):queryWrapper.orderByDesc(producerPagination.getSidx());
        }
        if("0".equals(dataType)){
            Page<ProducerEntity> page=new Page<>(producerPagination.getCurrentPage(), producerPagination.getPageSize());
            IPage<ProducerEntity> userIPage=this.page(page,queryWrapper);
            return producerPagination.setData(userIPage.getRecords(),userIPage.getTotal());
        }else{
            return this.list(queryWrapper);
        }
    }

    @Override
    public ProducerEntity getInfo(Long id){
        QueryWrapper<ProducerEntity> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(ProducerEntity::getId,id);
        return this.getOne(queryWrapper);
    }

    @Override
    public void create(ProducerEntity entity){
    	this.save(entity);
    }

    @Override
    public boolean update(Long id, ProducerEntity entity){
        entity.setId(id);
        return this.updateById(entity);
    }
    @Override
    public void delete(ProducerEntity entity){
        if(entity!=null){
            this.removeById(entity.getId());
        }
    }
    //子表方法
}