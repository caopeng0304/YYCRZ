package com.sinosoft.ie.booster.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinosoft.ie.booster.workflow.entity.FlowTaskOperatorEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 流程经办
 *
 * @author booster code generator
 * @since 2021-09-23
 */
@Mapper
public interface FlowTaskOperatorMapper extends BaseMapper<FlowTaskOperatorEntity> {

	/**
	 * 更新流程经办审核状态
	 *
	 * @param map 参数
	 */
	void updateFixedapprover(@Param("map") Map<String, Object> map);

	/**
	 * 更新会签委托人的审核状态
	 *
	 * @param taskNodeId 节点id
	 * @param userNames     用户名
	 */
	void updateDelegateUser(@Param("taskNodeId") Long taskNodeId, @Param("userNames") String userNames);

	/**
	 * 更新驳回流程节点
	 *
	 * @param taskId 任务id
	 */
	void updateState(@Param("taskId") Long taskId);

	/**
	 * 驳回的节点之后的节点作废
	 *
	 * @param taskId 任务id
	 * @param nodeId 节点id
	 */
	void updateReject(@Param("taskId") Long taskId, @Param("nodeId") String nodeId);
}
