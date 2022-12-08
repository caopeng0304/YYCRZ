package com.sinosoft.ie.booster.mqroute.service;

import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.mqroute.entity.TopicEntity;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.mqroute.model.topic.TopicPagination;
import com.sinosoft.ie.booster.mqroute.model.topic.TopicUpForm;

import java.util.*;

/**
 *
 * 消息队列组件
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-03-15 15:12:51
 */
public interface TopicService extends IService<TopicEntity> {

    List<TopicEntity> getList(TopicPagination topicPagination);

    List<TopicEntity> getTypeList(TopicPagination topicPagination,String dataType);


    TopicEntity getInfo(Long id);

    void delete(TopicEntity entity);

    R create(TopicEntity entity);
    
    R updateSatae(TopicUpForm topicUpForm);

    boolean update( Long id, TopicEntity entity);

    
}
