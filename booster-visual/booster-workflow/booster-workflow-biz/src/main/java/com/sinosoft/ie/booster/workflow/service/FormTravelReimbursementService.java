package com.sinosoft.ie.booster.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.workflow.entity.FormTravelReimbursementEntity;

/**
 * 差旅报销申请表
 *
 * @author booster code generator
 * @since 2021-09-26
 */
public interface FormTravelReimbursementService extends IService<FormTravelReimbursementEntity> {

	/**
	 * 信息
	 *
	 * @param id 主键值
	 * @return
	 */
	FormTravelReimbursementEntity getInfo(Long id);

	/**
	 * 保存
	 *
	 * @param id     主键值
	 * @param entity 实体对象
	 * @throws WorkFlowException 异常
	 */
	void save(Long id, FormTravelReimbursementEntity entity) throws WorkFlowException;

	/**
	 * 提交
	 *
	 * @param id     主键值
	 * @param entity 实体对象
	 * @throws WorkFlowException 异常
	 */
	void submit(Long id, FormTravelReimbursementEntity entity) throws WorkFlowException;

	/**
	 * 更改数据
	 *  @param id   主键值
	 * @param data 实体对象
	 */
	void data(Long id, String data);
}
