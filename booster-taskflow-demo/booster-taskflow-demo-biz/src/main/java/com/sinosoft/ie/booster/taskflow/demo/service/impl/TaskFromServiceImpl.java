package com.sinosoft.ie.booster.taskflow.demo.service.impl;

import com.sinosoft.ie.booster.taskflow.demo.entity.TaskFromEntity;
import com.sinosoft.ie.booster.taskflow.demo.mapper.TaskFromMapper;
import com.sinosoft.ie.booster.taskflow.demo.service.TaskFromService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.visualdev.util.*;
import com.sinosoft.ie.booster.taskflow.demo.model.taskfrom.TaskFromPagination;
import com.sinosoft.ie.booster.taskflow.demo.entity.TaskFromDetailEntity;
import com.sinosoft.ie.booster.taskflow.demo.service.TaskFromDetailService;
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
 * task_from
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-09-10 14:47:39
 */
@Service
public class TaskFromServiceImpl extends ServiceImpl<TaskFromMapper, TaskFromEntity> implements TaskFromService {

    @Autowired
    private TaskFromDetailService taskFromDetailService;


    @Override
    public List<TaskFromEntity> getList(TaskFromPagination taskFromPagination){
        QueryWrapper<TaskFromEntity> queryWrapper=new QueryWrapper<>();
        //关键字（账户、姓名、手机）
        if(!"null".equals(String.valueOf(taskFromPagination.getTaskNumber()))){
            queryWrapper.lambda().and(t->t.like(TaskFromEntity::getTaskNumber,taskFromPagination.getTaskNumber()));
        }

        if(!"null".equals(String.valueOf(taskFromPagination.getProjectName()))){
            queryWrapper.lambda().and(t->t.like(TaskFromEntity::getProjectName,taskFromPagination.getProjectName()));
        }

        if(!"null".equals(String.valueOf(taskFromPagination.getProjectPosition()))){
            queryWrapper.lambda().and(t->t.like(TaskFromEntity::getProjectPosition,taskFromPagination.getProjectPosition()));
        }

        if(!"null".equals(String.valueOf(taskFromPagination.getTaskSource()))){
            queryWrapper.lambda().and(t->t.like(TaskFromEntity::getTaskSource,taskFromPagination.getTaskSource()));
        }

        //排序
        if(StrUtil.isEmpty(taskFromPagination.getSidx())){
            queryWrapper.lambda().orderByDesc(TaskFromEntity::getId);
        }else{
            queryWrapper="asc".equals(taskFromPagination.getSort().toLowerCase())?queryWrapper.orderByAsc(taskFromPagination.getSidx()):queryWrapper.orderByDesc(taskFromPagination.getSidx());
        }
        Page<TaskFromEntity> page=new Page<>(taskFromPagination.getCurrentPage(), taskFromPagination.getPageSize());
        IPage<TaskFromEntity> userIPage=this.page(page,queryWrapper);
        return taskFromPagination.setData(userIPage.getRecords(),userIPage.getTotal());
    }
    @Override
    public List<TaskFromEntity> getTypeList(TaskFromPagination taskFromPagination,String dataType){
        QueryWrapper<TaskFromEntity> queryWrapper=new QueryWrapper<>();
        //关键字（账户、姓名、手机）
        if(!"null".equals(String.valueOf(taskFromPagination.getTaskNumber()))){
            queryWrapper.lambda().and(t->t.like(TaskFromEntity::getTaskNumber,taskFromPagination.getTaskNumber()));
        }

        if(!"null".equals(String.valueOf(taskFromPagination.getProjectName()))){
            queryWrapper.lambda().and(t->t.like(TaskFromEntity::getProjectName,taskFromPagination.getProjectName()));
        }

        if(!"null".equals(String.valueOf(taskFromPagination.getProjectPosition()))){
            queryWrapper.lambda().and(t->t.like(TaskFromEntity::getProjectPosition,taskFromPagination.getProjectPosition()));
        }

        if(!"null".equals(String.valueOf(taskFromPagination.getTaskSource()))){
            queryWrapper.lambda().and(t->t.like(TaskFromEntity::getTaskSource,taskFromPagination.getTaskSource()));
        }

        //排序
        if(StrUtil.isEmpty(taskFromPagination.getSidx())){
            queryWrapper.lambda().orderByDesc(TaskFromEntity::getId);
        }else{
            queryWrapper="asc".equals(taskFromPagination.getSort().toLowerCase())?queryWrapper.orderByAsc(taskFromPagination.getSidx()):queryWrapper.orderByDesc(taskFromPagination.getSidx());
        }
        if("0".equals(dataType)){
            Page<TaskFromEntity> page=new Page<>(taskFromPagination.getCurrentPage(), taskFromPagination.getPageSize());
            IPage<TaskFromEntity> userIPage=this.page(page,queryWrapper);
            return taskFromPagination.setData(userIPage.getRecords(),userIPage.getTotal());
        }else{
            return this.list(queryWrapper);
        }
    }

    @Override
    public TaskFromEntity getInfo(Long id){
        QueryWrapper<TaskFromEntity> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(TaskFromEntity::getId,id);
        return this.getOne(queryWrapper);
    }

    @Override
    public void create(TaskFromEntity entity){
        this.save(entity);
    }

    @Override
    public boolean update(Long id, TaskFromEntity entity){
        entity.setId(id);
        return this.updateById(entity);
    }
    @Override
    public void delete(TaskFromEntity entity){
        if(entity!=null){
            this.removeById(entity.getId());
        }
    }
    //子表方法
    @Override
    public List<TaskFromDetailEntity> getTaskFromDetailEntityList(){
        QueryWrapper<TaskFromDetailEntity> queryWrapper=new QueryWrapper<>();
        return taskFromDetailService.list(queryWrapper);
    }
}