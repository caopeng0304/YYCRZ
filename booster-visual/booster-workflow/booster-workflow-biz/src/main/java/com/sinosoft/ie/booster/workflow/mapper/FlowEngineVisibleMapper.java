package com.sinosoft.ie.booster.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinosoft.ie.booster.workflow.entity.FlowEngineVisibleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 流程可见
 *
 * @author booster code generator
 * @since 2021-09-23
 */
@Mapper
public interface FlowEngineVisibleMapper extends BaseMapper<FlowEngineVisibleEntity> {

	/**
	 * 部分看见(岗位和用户)
	 * @param sql sql语句
	 * @return
	 */
	List<FlowEngineVisibleEntity> getVisibleFlowList(@Param("sql") String sql);
}
