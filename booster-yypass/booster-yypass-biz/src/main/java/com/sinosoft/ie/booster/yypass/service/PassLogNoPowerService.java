package com.sinosoft.ie.booster.yypass.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.yypass.entity.PassLogNoPowerEntity;

import java.util.List;

/**
 *
 * aps_system
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-08-08 14:06:39
 */
public interface PassLogNoPowerService extends IService<PassLogNoPowerEntity> {

	List<PassLogNoPowerEntity> getList(PassLogNoPowerEntity passLogEntity);

	PassLogNoPowerEntity getInfo(String id);

	void delete(PassLogNoPowerEntity entity);

	void create(PassLogNoPowerEntity entity);

	boolean update(String id, PassLogNoPowerEntity entity);



}
