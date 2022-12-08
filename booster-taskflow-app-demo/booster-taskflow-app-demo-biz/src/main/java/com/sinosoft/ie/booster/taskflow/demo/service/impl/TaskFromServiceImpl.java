package com.sinosoft.ie.booster.taskflow.demo.service.impl;

import cn.hutool.core.util.StrUtil;
import com.sinosoft.ie.booster.taskflow.demo.entity.TaskFromEntity;
import com.sinosoft.ie.booster.taskflow.demo.mapper.TaskFromMapper;
import com.sinosoft.ie.booster.taskflow.demo.service.TaskFromService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.taskflow.demo.model.taskfrom.TaskFromPagination;
import com.sinosoft.ie.booster.visualdev.util.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.*;
/**
 *
 * task_from
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-11-01
 */
@Service
public class TaskFromServiceImpl extends ServiceImpl<TaskFromMapper, TaskFromEntity> implements TaskFromService {


    @Override
    public List<TaskFromEntity> getlist(TaskFromPagination pagination){
        QueryWrapper<TaskFromEntity> queryWrapper = new QueryWrapper<>();
        if(StrUtil.isNotEmpty(pagination.getKeyword())){
            queryWrapper.lambda().and(
                t->t.like(TaskFromEntity::getManageUnit,pagination.getKeyword())
                .or().like(TaskFromEntity::getTaskType,pagination.getKeyword())
                .or().like(TaskFromEntity::getProjectName,pagination.getKeyword())
                .or().like(TaskFromEntity::getSignStatus,pagination.getKeyword())
                .or().like(TaskFromEntity::getTaskNumber,pagination.getKeyword())
                .or().like(TaskFromEntity::getRoadCode,pagination.getKeyword())
                .or().like(TaskFromEntity::getMaintainUnit,pagination.getKeyword())
                .or().like(TaskFromEntity::getTaskRequire,pagination.getKeyword())
            );
        }
        //排序
        if (StrUtil.isEmpty(pagination.getSidx())) {
            queryWrapper.lambda().orderByDesc(TaskFromEntity::getManageUnit);
        } else {
            queryWrapper = "asc".equals(pagination.getSort().toLowerCase()) ? queryWrapper.orderByAsc(pagination.getSidx()) : queryWrapper.orderByDesc(pagination.getSidx());
        }
        Page<TaskFromEntity> page = new Page<>(pagination.getCurrentPage(), pagination.getPageSize());
        IPage<TaskFromEntity> userIPage = this.page(page, queryWrapper);
        return pagination.setData(userIPage.getRecords(), page.getTotal());
    }

    @Override
    public TaskFromEntity getInfo(String id){
        QueryWrapper<TaskFromEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TaskFromEntity::getId, id);
        return this.getOne(queryWrapper);
    }

    @Override
    @Transactional
    public void create(TaskFromEntity entity){
        this.save(entity);
    }

    @Override
    @Transactional
    public void update(String id, TaskFromEntity entity){
        entity.setId(Long.valueOf(id));
        this.updateById(entity);
    }

    @Override
    public void delete(TaskFromEntity entity) {
        if (entity != null) {
            this.removeById(entity.getId());
        }
    }
}
