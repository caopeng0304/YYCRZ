package com.sinosoft.ie.booster.infection.service.impl;

import com.sinosoft.ie.booster.infection.entity.InfcRptGzEntity;
import com.sinosoft.ie.booster.infection.mapper.InfcRptGzMapper;
import com.sinosoft.ie.booster.infection.service.InfcRptGzService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.infection.model.infcrptgz.InfcRptGzPagination;
import com.sinosoft.ie.booster.infection.entity.FlupEntity;
import com.sinosoft.ie.booster.infection.service.FlupService;
import com.sinosoft.ie.booster.infection.entity.TestEntity;
import com.sinosoft.ie.booster.infection.service.TestService;
import com.sinosoft.ie.booster.infection.entity.TracEntity;
import com.sinosoft.ie.booster.infection.service.TracService;
import com.sinosoft.ie.booster.infection.entity.TrtEntity;
import com.sinosoft.ie.booster.infection.service.TrtService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.hutool.core.util.StrUtil;
import java.util.*;

/**
 *
 * 传染病个案报告卡
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-01-06 16:52:51
 */
@Service
public class InfcRptGzServiceImpl extends ServiceImpl<InfcRptGzMapper, InfcRptGzEntity> implements InfcRptGzService {

    @Autowired
    private FlupService flupService;

    @Autowired
    private TestService testService;

    @Autowired
    private TracService tracService;

    @Autowired
    private TrtService trtService;


    @Override
    public List<InfcRptGzEntity> getList(InfcRptGzPagination infcRptGzPagination){
        QueryWrapper<InfcRptGzEntity> queryWrapper=new QueryWrapper<>();
        //关键字（账户、姓名、手机）
        if(!"null".equals(String.valueOf(infcRptGzPagination.getDiagDiseCode()))){
            queryWrapper.lambda().and(t->t.like(InfcRptGzEntity::getDiagDiseCode,infcRptGzPagination.getDiagDiseCode()));
        }

        if(!"null".equals(String.valueOf(infcRptGzPagination.getPatientName()))){
            queryWrapper.lambda().and(t->t.like(InfcRptGzEntity::getPatientName,infcRptGzPagination.getPatientName()));
        }

        //排序
        if(StrUtil.isEmpty(infcRptGzPagination.getSidx())){
            queryWrapper.lambda().orderByDesc(InfcRptGzEntity::getId);
        }else{
            queryWrapper="asc".equals(infcRptGzPagination.getSort().toLowerCase())?queryWrapper.orderByAsc(infcRptGzPagination.getSidx()):queryWrapper.orderByDesc(infcRptGzPagination.getSidx());
        }
        Page<InfcRptGzEntity> page=new Page<>(infcRptGzPagination.getCurrentPage(), infcRptGzPagination.getPageSize());
        IPage<InfcRptGzEntity> userIPage=this.page(page,queryWrapper);
        return infcRptGzPagination.setData(userIPage.getRecords(),userIPage.getTotal());
    }
    @Override
    public List<InfcRptGzEntity> getTypeList(InfcRptGzPagination infcRptGzPagination,String dataType){
        QueryWrapper<InfcRptGzEntity> queryWrapper=new QueryWrapper<>();
        //关键字（账户、姓名、手机）
        if(!"null".equals(String.valueOf(infcRptGzPagination.getDiagDiseCode()))){
            queryWrapper.lambda().and(t->t.like(InfcRptGzEntity::getDiagDiseCode,infcRptGzPagination.getDiagDiseCode()));
        }

        if(!"null".equals(String.valueOf(infcRptGzPagination.getPatientName()))){
            queryWrapper.lambda().and(t->t.like(InfcRptGzEntity::getPatientName,infcRptGzPagination.getPatientName()));
        }

        //排序
        if(StrUtil.isEmpty(infcRptGzPagination.getSidx())){
            queryWrapper.lambda().orderByDesc(InfcRptGzEntity::getId);
        }else{
            queryWrapper="asc".equals(infcRptGzPagination.getSort().toLowerCase())?queryWrapper.orderByAsc(infcRptGzPagination.getSidx()):queryWrapper.orderByDesc(infcRptGzPagination.getSidx());
        }
        if("0".equals(dataType)){
            Page<InfcRptGzEntity> page=new Page<>(infcRptGzPagination.getCurrentPage(), infcRptGzPagination.getPageSize());
            IPage<InfcRptGzEntity> userIPage=this.page(page,queryWrapper);
            return infcRptGzPagination.setData(userIPage.getRecords(),userIPage.getTotal());
        }else{
            return this.list(queryWrapper);
        }
    }

    @Override
    public InfcRptGzEntity getInfo(Long id){
        QueryWrapper<InfcRptGzEntity> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(InfcRptGzEntity::getId,id);
        return this.getOne(queryWrapper);
    }

    @Override
    public void create(InfcRptGzEntity entity){
        this.save(entity);
    }

    @Override
    public boolean update(Long id, InfcRptGzEntity entity){
        entity.setId(id);
        return this.updateById(entity);
    }
    @Override
    public void delete(InfcRptGzEntity entity){
        if(entity!=null){
            this.removeById(entity.getId());
        }
    }
    //子表方法
    @Override
    public List<FlupEntity> getFlupEntityList(){
        QueryWrapper<FlupEntity> queryWrapper=new QueryWrapper<>();
        return flupService.list(queryWrapper);
    }
    @Override
    public List<TestEntity> getTestEntityList(){
        QueryWrapper<TestEntity> queryWrapper=new QueryWrapper<>();
        return testService.list(queryWrapper);
    }
    @Override
    public List<TracEntity> getTracEntityList(){
        QueryWrapper<TracEntity> queryWrapper=new QueryWrapper<>();
        return tracService.list(queryWrapper);
    }
    @Override
    public List<TrtEntity> getTrtEntityList(){
        QueryWrapper<TrtEntity> queryWrapper=new QueryWrapper<>();
        return trtService.list(queryWrapper);
    }
}