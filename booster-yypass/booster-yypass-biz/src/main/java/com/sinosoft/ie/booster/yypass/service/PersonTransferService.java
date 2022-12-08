package com.sinosoft.ie.booster.yypass.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.yypass.entity.PersonTransferEntity;

import java.util.List;

/**
 *
 * aps_system
 * 版本： V1.0.0
 * 作者： booster开发平台组
 * 日期： 2022-08-08 14:06:39
 */
public interface PersonTransferService extends IService<PersonTransferEntity> {

	List<PersonTransferEntity> getList(PersonTransferEntity passLogEntity);

	PersonTransferEntity getInfo(String id);

	void delete(PersonTransferEntity entity);

	void create(PersonTransferEntity entity);

	boolean update(String id, PersonTransferEntity entity);

	//查询交接信息
	List<PersonTransferEntity> getPersonTransfer(String transfer_person_id);




}
