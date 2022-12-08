package com.sinosoft.ie.booster.yypass.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.yypass.entity.TransferCPassEntity;

import java.util.List;

/**
 *
 * aps_system
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-08-08 14:06:39
 */
public interface TransferCPassService extends IService<TransferCPassEntity> {

	List<TransferCPassEntity> getList(TransferCPassEntity passLogEntity);

	TransferCPassEntity getInfo(String id);

	void delete(TransferCPassEntity entity);

	void create(TransferCPassEntity entity);

	boolean update(String id, TransferCPassEntity entity);

}
