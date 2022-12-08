package com.sinosoft.ie.booster.mqroute.service;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.mqroute.entity.ConsumerEntity;
import com.sinosoft.ie.booster.mqroute.entity.TopicEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.mqroute.model.consumer.ConsumerPagination;
import java.util.*;

/**
 *
 * consumer
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-03-16 15:50:58
 */
public interface ConsumerService extends IService<ConsumerEntity> {

    List<ConsumerEntity> getList(ConsumerPagination consumerPagination);

    List<ConsumerEntity> getTypeList(ConsumerPagination consumerPagination,String dataType);

    List<ConsumerEntity> getListByQuery(QueryWrapper<ConsumerEntity> queryWrapper);

    ConsumerEntity getInfo(Long id);

    void delete(ConsumerEntity entity);

    R create(ConsumerEntity entity);

    boolean update( Long id, ConsumerEntity entity);
    
    
}
