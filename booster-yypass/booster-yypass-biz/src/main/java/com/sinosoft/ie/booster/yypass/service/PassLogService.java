package com.sinosoft.ie.booster.yypass.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.yypass.entity.PassLogEntity;

import java.util.List;

/**
 *
 * aps_system
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-08-08 14:06:39
 */
public interface PassLogService extends IService<PassLogEntity> {

	List<PassLogEntity> getList(PassLogEntity passLogEntity);

	PassLogEntity getInfo(String id);

	void delete(PassLogEntity entity);

	void create(PassLogEntity entity);

	boolean update(String id, PassLogEntity entity);

	List<PassLogEntity> selectParmer(String passBasicInfoId);

	String getRoleCode(String state);

	List<String> getTopState(String passBasicInfoId);

	List<String> getRepeatList(String list);
}
