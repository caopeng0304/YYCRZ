package com.sinosoft.ie.booster.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.workflow.entity.FlowTaskOperatorRecordEntity;

import java.util.List;

/**
 * 流程经办记录
 *
 * @author booster code generator
 * @since 2021-09-23
 */
public interface FlowTaskOperatorRecordService extends IService<FlowTaskOperatorRecordEntity> {

	/**
	 * 列表
	 *
	 * @param taskId 流程实例Id
	 * @return
	 */
	List<FlowTaskOperatorRecordEntity> getList(Long taskId);

	/**
	 * 信息
	 *
	 * @param id 主键值
	 * @return
	 */
	FlowTaskOperatorRecordEntity getInfo(Long id);

	/**
	 * 删除
	 *
	 * @param entity 实体对象
	 * @return
	 */
	void delete(FlowTaskOperatorRecordEntity entity);

	/**
	 * 创建
	 *
	 * @param entity 实体对象
	 * @return
	 */
	void create(FlowTaskOperatorRecordEntity entity);

	/**
	 * 更新
	 *  @param id     主键值
	 * @param entity 实体对象
	 */
	void update(Long id, FlowTaskOperatorRecordEntity entity);
}
