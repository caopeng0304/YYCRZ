package com.sinosoft.ie.booster.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.workflow.entity.FormPayDistributionEntity;

/**
 * 薪酬发放
 *
 * @author booster code generator
 * @since 2021-09-26
 */
public interface FormPayDistributionService extends IService<FormPayDistributionEntity> {

	/**
	 * 信息
	 *
	 * @param id 主键值
	 * @return
	 */
	FormPayDistributionEntity getInfo(Long id);

	/**
	 * 保存
	 *
	 * @param id     主键值
	 * @param entity 实体对象
	 * @throws WorkFlowException 异常
	 */
	void save(Long id, FormPayDistributionEntity entity) throws WorkFlowException;

	/**
	 * 提交
	 *
	 * @param id     主键值
	 * @param entity 实体对象
	 * @throws WorkFlowException 异常
	 */
	void submit(Long id, FormPayDistributionEntity entity) throws WorkFlowException;

	/**
	 * 更改数据
	 *  @param id   主键值
	 * @param data 实体对象
	 */
	void data(Long id, String data);
}
