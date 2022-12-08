package com.sinosoft.ie.booster.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.workflow.entity.FlowEngineVisibleEntity;

import java.util.List;

/**
 * 流程可见
 *
 * @author booster code generator
 * @since 2021-09-23
 */
public interface FlowEngineVisibleService extends IService<FlowEngineVisibleEntity> {

	/**
	 * 列表
	 *
	 * @param flowId 流程主键
	 * @return
	 */
	List<FlowEngineVisibleEntity> getList(Long flowId);

	/**
	 * 可见流程列表
	 *
	 * @param userName 用户名
	 * @return
	 */
	List<FlowEngineVisibleEntity> getVisibleFlowList(String userName);
}
