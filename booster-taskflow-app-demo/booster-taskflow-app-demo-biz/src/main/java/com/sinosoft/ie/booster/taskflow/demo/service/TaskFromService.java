package com.sinosoft.ie.booster.taskflow.demo.service;

import com.sinosoft.ie.booster.taskflow.demo.entity.TaskFromEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.taskflow.demo.model.taskfrom.TaskFromPagination;

import java.util.*;

/**
 *
 * task_from
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-11-01
 */
public interface TaskFromService extends IService<TaskFromEntity> {

    List<TaskFromEntity> getlist(TaskFromPagination pagination);

    TaskFromEntity getInfo(String id);

    void create(TaskFromEntity entity);

    void update(String id, TaskFromEntity entity);

    void delete(TaskFromEntity entity);
}
