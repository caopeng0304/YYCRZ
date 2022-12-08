package com.sinosoft.ie.booster.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.workflow.entity.FormWarehouseReceiptEntity;
import com.sinosoft.ie.booster.workflow.entity.FormWarehouseReceiptEntryEntity;

import java.util.List;

/**
 * 入库申请单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
public interface FormWarehouseReceiptService extends IService<FormWarehouseReceiptEntity> {

	/**
	 * 列表
	 *
	 * @param id 主键值
	 * @return
	 */
	List<FormWarehouseReceiptEntryEntity> getWarehouseEntryList(Long id);

	/**
	 * 信息
	 *
	 * @param id 主键值
	 * @return
	 */
	FormWarehouseReceiptEntity getInfo(Long id);

	/**
	 * 保存
	 *
	 * @param id                       主键值
	 * @param entity                   实体对象
	 * @param warehouseEntryEntityList 子表
	 * @throws WorkFlowException 异常
	 */
	void save(Long id, FormWarehouseReceiptEntity entity, List<FormWarehouseReceiptEntryEntity> warehouseEntryEntityList) throws WorkFlowException;

	/**
	 * 提交
	 *
	 * @param id                       主键值
	 * @param entity                   实体对象
	 * @param warehouseEntryEntityList 子表
	 * @throws WorkFlowException 异常
	 */
	void submit(Long id, FormWarehouseReceiptEntity entity, List<FormWarehouseReceiptEntryEntity> warehouseEntryEntityList) throws WorkFlowException;

	/**
	 * 更改数据
	 *  @param id   主键值
	 * @param data 实体对象
	 */
	void data(Long id, String data);
}
