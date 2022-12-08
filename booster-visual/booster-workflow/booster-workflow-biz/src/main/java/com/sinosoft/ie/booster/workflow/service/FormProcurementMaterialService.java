package com.sinosoft.ie.booster.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.workflow.entity.FormProcurementMaterialEntity;
import com.sinosoft.ie.booster.workflow.entity.FormProcurementMaterialEntryEntity;

import java.util.List;

/**
 * 采购原材料
 *
 * @author booster code generator
 * @since 2021-09-26
 */
public interface FormProcurementMaterialService extends IService<FormProcurementMaterialEntity> {

	/**
	 * 列表
	 *
	 * @param id 主键值
	 * @return
	 */
	List<FormProcurementMaterialEntryEntity> getProcurementEntryList(Long id);

	/**
	 * 信息
	 *
	 * @param id 主键值
	 * @return
	 */
	FormProcurementMaterialEntity getInfo(Long id);

	/**
	 * 保存
	 *
	 * @param id                         主键值
	 * @param entity                     实体对象
	 * @param procurementEntryEntityList 子表
	 * @throws WorkFlowException 异常
	 */
	void save(Long id, FormProcurementMaterialEntity entity, List<FormProcurementMaterialEntryEntity> procurementEntryEntityList) throws WorkFlowException;

	/**
	 * 提交
	 *
	 * @param id                         主键值
	 * @param entity                     实体对象
	 * @param procurementEntryEntityList 子表
	 * @throws WorkFlowException 异常
	 */
	void submit(Long id, FormProcurementMaterialEntity entity, List<FormProcurementMaterialEntryEntity> procurementEntryEntityList) throws WorkFlowException;

	/**
	 * 更改数据
	 *  @param id   主键值
	 * @param data 实体对象
	 */
	void data(Long id, String data);
}
