package com.sinosoft.ie.booster.yypass.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.yypass.entity.NoPassBasicInfoEntity;
import com.sinosoft.ie.booster.yypass.model.passbasicinfo.NoPassBasicInfoPagination;

import java.util.List;

/**
 *
 * aps_system
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-08-08 14:06:39
 */
public interface NoPassBasicInfoService extends IService<NoPassBasicInfoEntity> {

    List<NoPassBasicInfoEntity> getList(NoPassBasicInfoEntity entity);

    List<NoPassBasicInfoEntity> getTypeList(NoPassBasicInfoPagination apsSystemPagination, String dataType);

	NoPassBasicInfoEntity getInfo(String id);

    void delete(NoPassBasicInfoEntity entity);

    void create(NoPassBasicInfoEntity entity);

    boolean update(String id, NoPassBasicInfoEntity entity);

	IPage<NoPassBasicInfoEntity> selectParmer(NoPassBasicInfoPagination passBasicInfoPagination) throws Exception;
    
//  子表方法
}
