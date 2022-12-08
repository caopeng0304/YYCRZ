package com.sinosoft.ie.booster.yypass.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.yypass.entity.TransferPassLogNoPowerEntity;

import java.util.List;

/**
 *
 * aps_system
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-08-08 14:06:39
 */
public interface TransferPassLogNoPowerService extends IService<TransferPassLogNoPowerEntity> {

	List<TransferPassLogNoPowerEntity> getList(TransferPassLogNoPowerEntity passLogEntity);

	TransferPassLogNoPowerEntity getInfo(String id);

	void delete(TransferPassLogNoPowerEntity entity);

	void create(TransferPassLogNoPowerEntity entity);

	boolean update(String id, TransferPassLogNoPowerEntity entity);

}
