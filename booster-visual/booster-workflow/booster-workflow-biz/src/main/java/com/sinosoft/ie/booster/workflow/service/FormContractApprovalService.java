package com.sinosoft.ie.booster.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.workflow.entity.FormContractApprovalEntity;

/**
 * 合同审批
 *
 * @author booster code generator
 * @since 2021-09-26
 */
public interface FormContractApprovalService extends IService<FormContractApprovalEntity> {

	/**
	 * 信息
	 *
	 * @param id 主键值
	 * @return
	 */
	FormContractApprovalEntity getInfo(Long id);

	/**
	 * 保存
	 *
	 * @param id     主键值
	 * @param entity 实体对象
	 * @throws WorkFlowException 异常
	 */
	void save(Long id, FormContractApprovalEntity entity) throws WorkFlowException;

	/**
	 * 提交
	 *
	 * @param id                 主键值
	 * @param entity             实体对象
	 * @param freeApprover 指定审批人
	 * @throws WorkFlowException 异常
	 */
	void submit(Long id, FormContractApprovalEntity entity, String freeApprover) throws WorkFlowException;

	/**
	 * 更改数据
	 *  @param id   主键值
	 * @param data 实体对象
	 */
	void data(Long id, String data);
}
