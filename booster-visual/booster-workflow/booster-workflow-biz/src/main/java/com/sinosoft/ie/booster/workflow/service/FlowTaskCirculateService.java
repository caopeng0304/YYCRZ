package com.sinosoft.ie.booster.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.workflow.entity.FlowTaskCirculateEntity;

import java.util.List;

/**
 * 流程传阅
 *
 * @author booster code generator
 * @since 2021-09-23
 */
public interface FlowTaskCirculateService extends IService<FlowTaskCirculateEntity> {

	/**
	 * 删除（根据实例Id）
	 *
	 * @param taskId 任务主键
	 * @return
	 */
	void deleteByTaskId(Long taskId);

	/**
	 * 删除
	 *
	 * @param nodeId 节点主键
	 * @return
	 */
	void deleteByNodeId(Long nodeId);

	/**
	 * 创建
	 *
	 * @param entitys 实体对象
	 * @return
	 */
	void create(List<FlowTaskCirculateEntity> entitys);
}
