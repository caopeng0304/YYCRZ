package com.sinosoft.ie.booster.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.workflow.entity.FormSalesOrderEntity;
import com.sinosoft.ie.booster.workflow.entity.FormSalesOrderEntryEntity;

import java.util.List;

/**
 * 销售订单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
public interface FormSalesOrderService extends IService<FormSalesOrderEntity> {

	/**
	 * 列表
	 *
	 * @param id 主键值
	 * @return
	 */
	List<FormSalesOrderEntryEntity> getSalesEntryList(Long id);

	/**
	 * 信息
	 *
	 * @param id 主键值
	 * @return
	 */
	FormSalesOrderEntity getInfo(Long id);

	/**
	 * 保存
	 *
	 * @param id                        主键值
	 * @param entity                    实体对象
	 * @param salesOrderEntryEntityList 子表
	 * @throws WorkFlowException 异常
	 */
	void save(Long id, FormSalesOrderEntity entity, List<FormSalesOrderEntryEntity> salesOrderEntryEntityList) throws WorkFlowException;

	/**
	 * 提交
	 *
	 * @param id                        主键值
	 * @param entity                    实体对象
	 * @param salesOrderEntryEntityList 子表
	 * @throws WorkFlowException 异常
	 */
	void submit(Long id, FormSalesOrderEntity entity, List<FormSalesOrderEntryEntity> salesOrderEntryEntityList) throws WorkFlowException;

	/**
	 * 更改数据
	 *  @param id   主键值
	 * @param data 实体对象
	 */
	void data(Long id, String data);
}
