package com.sinosoft.ie.booster.workflow.service;

import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.workflow.entity.FlowTaskEntity;
import com.sinosoft.ie.booster.workflow.model.flowtask.FlowTaskInfoVO;

import java.sql.SQLException;
import java.util.Map;

/**
 * 在线开发工作流
 *
 * @author booster开发平台组
 * @since 2021/3/15 9:19
 */
public interface FlowDynamicService {

	/**
	 * 表单信息
	 *
	 * @param entity 流程任务对象
	 * @return
	 * @throws WorkFlowException 异常
	 * @throws DataException     异常
	 * @throws SQLException      异常
	 */
	FlowTaskInfoVO info(FlowTaskEntity entity) throws WorkFlowException, DataException, SQLException;

	/**
	 * 保存
	 *
	 * @param id     主键值
	 * @param flowId 引擎id
	 * @param entity 实体对象
	 * @throws WorkFlowException 异常
	 * @throws DataException     异常
	 * @throws SQLException      异常
	 */
	void save(Long id, Long flowId, String entity) throws WorkFlowException, DataException, SQLException;

	/**
	 * 提交
	 *
	 * @param id         主键值
	 * @param flowId     引擎id
	 * @param entity     实体对象
	 * @param freeUser 指定审批用户
	 * @throws WorkFlowException 异常
	 * @throws DataException     异常
	 * @throws SQLException      异常
	 */
	void submit(Long id, Long flowId, String entity, String freeUser) throws WorkFlowException, DataException, SQLException;

	/**
	 * 关联表单
	 *
	 * @param flowId 引擎id
	 * @param id     数据id
	 * @return
	 * @throws WorkFlowException 异常
	 * @throws SQLException      异常
	 * @throws DataException     异常
	 */
	Map<String, Object> getData(Long flowId, Long id) throws WorkFlowException, SQLException, DataException;
}
