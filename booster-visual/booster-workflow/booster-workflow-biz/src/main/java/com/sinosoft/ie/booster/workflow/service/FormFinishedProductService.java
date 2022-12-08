package com.sinosoft.ie.booster.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.workflow.entity.FormFinishedProductEntity;
import com.sinosoft.ie.booster.workflow.entity.FormFinishedProductEntryEntity;

import java.util.List;

/**
 * 成品入库单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
public interface FormFinishedProductService extends IService<FormFinishedProductEntity> {

	/**
	 * 列表
	 *
	 * @param id 主键值
	 * @return
	 */
	List<FormFinishedProductEntryEntity> getFinishedEntryList(Long id);

	/**
	 * 信息
	 *
	 * @param id 主键值
	 * @return
	 */
	FormFinishedProductEntity getInfo(Long id);

	/**
	 * 保存
	 *
	 * @param id                             主键值
	 * @param entity                         实体对象
	 * @param finishedProductEntryEntityList 子表
	 * @throws WorkFlowException 异常
	 */
	void save(Long id, FormFinishedProductEntity entity, List<FormFinishedProductEntryEntity> finishedProductEntryEntityList) throws WorkFlowException;

	/**
	 * 提交
	 *
	 * @param id                             主键值
	 * @param entity                         实体对象
	 * @param finishedProductEntryEntityList 子表
	 * @throws WorkFlowException 异常
	 */
	void submit(Long id, FormFinishedProductEntity entity, List<FormFinishedProductEntryEntity> finishedProductEntryEntityList) throws WorkFlowException;

	/**
	 * 更改数据
	 *  @param id   主键值
	 * @param data 实体对象
	 */
	void data(Long id, String data);
}
