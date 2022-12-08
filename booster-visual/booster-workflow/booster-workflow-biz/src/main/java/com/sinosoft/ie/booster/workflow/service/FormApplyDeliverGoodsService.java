package com.sinosoft.ie.booster.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.workflow.entity.FormApplyDeliverGoodsEntity;
import com.sinosoft.ie.booster.workflow.entity.FormApplyDeliverGoodsEntryEntity;

import java.util.List;

/**
 * 发货申请单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
public interface FormApplyDeliverGoodsService extends IService<FormApplyDeliverGoodsEntity> {

	/**
	 * 列表
	 *
	 * @param id 主键值
	 * @return
	 */
	List<FormApplyDeliverGoodsEntryEntity> getDeliverEntryList(Long id);

	/**
	 * 信息
	 *
	 * @param id 主键值
	 * @return
	 */
	FormApplyDeliverGoodsEntity getInfo(Long id);

	/**
	 * 保存
	 *
	 * @param id                               主键值
	 * @param entity                           实体对象
	 * @param applyDeliverGoodsEntryEntityList 子表
	 * @throws WorkFlowException 异常
	 */
	void save(Long id, FormApplyDeliverGoodsEntity entity, List<FormApplyDeliverGoodsEntryEntity> applyDeliverGoodsEntryEntityList) throws WorkFlowException;

	/**
	 * 提交
	 *
	 * @param id                               主键值
	 * @param entity                           实体对象
	 * @param applyDeliverGoodsEntryEntityList 子表
	 * @throws WorkFlowException 异常
	 */
	void submit(Long id, FormApplyDeliverGoodsEntity entity, List<FormApplyDeliverGoodsEntryEntity> applyDeliverGoodsEntryEntityList) throws WorkFlowException;

	/**
	 * 更改数据
	 *  @param id   主键值
	 * @param data 实体对象
	 */
	void data(Long id, String data);
}
