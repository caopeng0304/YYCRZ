package com.sinosoft.ie.booster.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.workflow.entity.FormMaterialRequisitionEntity;
import com.sinosoft.ie.booster.workflow.entity.FormMaterialRequisitionEntryEntity;

import java.util.List;

/**
 * 领料单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
public interface FormMaterialRequisitionService extends IService<FormMaterialRequisitionEntity> {

	/**
	 * 列表
	 *
	 * @param id 主键值
	 * @return
	 */
	List<FormMaterialRequisitionEntryEntity> getMaterialEntryList(Long id);

	/**
	 * 信息
	 *
	 * @param id 主键值
	 * @return
	 */
	FormMaterialRequisitionEntity getInfo(Long id);

	/**
	 * 保存
	 *
	 * @param id                      主键值
	 * @param entity                  实体对象
	 * @param materialEntryEntityList 子表
	 * @throws WorkFlowException 异常
	 */
	void save(Long id, FormMaterialRequisitionEntity entity, List<FormMaterialRequisitionEntryEntity> materialEntryEntityList) throws WorkFlowException;

	/**
	 * 提交
	 *
	 * @param id                      主键值
	 * @param entity                  实体对象
	 * @param materialEntryEntityList 子表
	 * @throws WorkFlowException 异常
	 */
	void submit(Long id, FormMaterialRequisitionEntity entity, List<FormMaterialRequisitionEntryEntity> materialEntryEntityList) throws WorkFlowException;

	/**
	 * 更改数据
	 *  @param id   主键值
	 * @param data 实体对象
	 */
	void data(Long id, String data);
}
