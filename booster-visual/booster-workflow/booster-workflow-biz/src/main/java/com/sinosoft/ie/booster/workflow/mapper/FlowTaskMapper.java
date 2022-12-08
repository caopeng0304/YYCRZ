package com.sinosoft.ie.booster.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinosoft.ie.booster.workflow.entity.FlowTaskEntity;
import com.sinosoft.ie.booster.workflow.model.flowtask.FlowTaskWaitListModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 流程任务
 *
 * @author booster code generator
 * @since 2021-09-23
 */
@Mapper
public interface FlowTaskMapper extends BaseMapper<FlowTaskEntity> {

	/**
	 * 获取我的待办
	 *
	 * @param map 参数
	 * @return
	 */
	List<FlowTaskEntity> getTrialList(@Param("map") Map<String, Object> map);

	/**
	 * 抄送事宜
	 *
	 * @param sql 自定义sql语句
	 * @return
	 */
	List<FlowTaskEntity> getCirculateList(@Param("sql") String sql);

	/**
	 * 待办事宜
	 *
	 * @param sql 自定义sql语句
	 * @return
	 */
	List<FlowTaskWaitListModel> getWaitList(@Param("sql") String sql);
}
