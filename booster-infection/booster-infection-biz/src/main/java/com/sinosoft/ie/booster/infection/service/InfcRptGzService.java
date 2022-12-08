package com.sinosoft.ie.booster.infection.service;

import com.sinosoft.ie.booster.infection.entity.FlupEntity;
import com.sinosoft.ie.booster.infection.entity.TestEntity;
import com.sinosoft.ie.booster.infection.entity.TracEntity;
import com.sinosoft.ie.booster.infection.entity.TrtEntity;
import com.sinosoft.ie.booster.infection.entity.InfcRptGzEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.infection.model.infcrptgz.InfcRptGzPagination;
import java.util.*;

/**
 *
 * 传染病个案报告卡
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-01-06 16:52:51
 */
public interface InfcRptGzService extends IService<InfcRptGzEntity> {

    List<InfcRptGzEntity> getList(InfcRptGzPagination infcRptGzPagination);

    List<InfcRptGzEntity> getTypeList(InfcRptGzPagination infcRptGzPagination,String dataType);



    InfcRptGzEntity getInfo(Long id);

    void delete(InfcRptGzEntity entity);

    void create(InfcRptGzEntity entity);

    boolean update( Long id, InfcRptGzEntity entity);
    
//  子表方法
    List<FlupEntity> getFlupEntityList() ;

    List<TestEntity> getTestEntityList() ;

    List<TracEntity> getTracEntityList() ;

    List<TrtEntity> getTrtEntityList() ;

}
