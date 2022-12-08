package com.sinosoft.ie.booster.mqroute.service;

import com.sinosoft.ie.booster.mqroute.entity.ProducerEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.mqroute.model.producer.ProducerCrForm;
import com.sinosoft.ie.booster.mqroute.model.producer.ProducerPagination;
import java.util.*;

/**
 *
 * producer
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-03-16 15:53:23
 */
public interface ProducerService extends IService<ProducerEntity> {

    List<ProducerEntity> getList(ProducerPagination producerPagination);

    List<ProducerEntity> getTypeList(ProducerPagination producerPagination,String dataType);



    ProducerEntity getInfo(Long id);

    void delete(ProducerEntity entity);

    void create(ProducerEntity entity);

    boolean update( Long id, ProducerEntity entity);
    
//  子表方法
}
