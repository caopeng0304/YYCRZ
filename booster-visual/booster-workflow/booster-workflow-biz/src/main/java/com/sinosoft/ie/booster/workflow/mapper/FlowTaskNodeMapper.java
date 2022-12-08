package com.sinosoft.ie.booster.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinosoft.ie.booster.workflow.entity.FlowTaskNodeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 流程节点
 *
 * @author booster code generator
 * @since 2021-09-23
 */
@Mapper
public interface FlowTaskNodeMapper extends BaseMapper<FlowTaskNodeEntity> {

	/**
	 * 更新驳回开始流程节点
	 *
	 * @param taskId 节点id
	 */
	void updateState(@Param("taskId") Long taskId);
}
