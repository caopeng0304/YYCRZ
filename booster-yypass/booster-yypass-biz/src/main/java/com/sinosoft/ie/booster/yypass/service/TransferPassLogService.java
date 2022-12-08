package com.sinosoft.ie.booster.yypass.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.yypass.entity.TransferPassLogEntity;

import java.util.List;

/**
 *
 * aps_system
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-08-08 14:06:39
 */
public interface TransferPassLogService extends IService<TransferPassLogEntity> {

	List<TransferPassLogEntity> getList(TransferPassLogEntity passLogEntity);

	TransferPassLogEntity getInfo(String id);

	void delete(TransferPassLogEntity entity);

	void create(TransferPassLogEntity entity);

	boolean update(String id, TransferPassLogEntity entity);

}
