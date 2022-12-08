package com.sinosoft.ie.booster.taskflow.demo.service;

import com.sinosoft.ie.booster.taskflow.demo.entity.TaskFromDetailEntity;
import com.sinosoft.ie.booster.taskflow.demo.service.TaskFromDetailService;
import com.sinosoft.ie.booster.taskflow.demo.entity.TaskFromEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.taskflow.demo.model.taskfrom.TaskFromPagination;
import java.util.*;

/**
 *
 * task_from
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-09-10 14:47:39
 */
public interface TaskFromService extends IService<TaskFromEntity> {

    List<TaskFromEntity> getList(TaskFromPagination taskFromPagination);

    List<TaskFromEntity> getTypeList(TaskFromPagination taskFromPagination,String dataType);



    TaskFromEntity getInfo(Long id);

    void delete(TaskFromEntity entity);

    void create(TaskFromEntity entity);

    boolean update( Long id, TaskFromEntity entity);
    
//  子表方法
    List<TaskFromDetailEntity> getTaskFromDetailEntityList() ;

}
