package com.sinosoft.ie.booster.yypass.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.yypass.entity.PassDelayEntity;

import java.util.List;

/**
 *
 * aps_system
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-08-08 14:06:39
 */
public interface PassDelayService extends IService<PassDelayEntity> {

	List<PassDelayEntity> getList(PassDelayEntity passDelayEntity);

	PassDelayEntity getInfo(String id);

	void delete(PassDelayEntity entity);

	void create(PassDelayEntity entity);

	boolean update(String id, PassDelayEntity entity);
//  子表方法
}
