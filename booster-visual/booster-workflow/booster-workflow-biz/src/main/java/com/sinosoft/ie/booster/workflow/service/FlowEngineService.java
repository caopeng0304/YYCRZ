package com.sinosoft.ie.booster.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.workflow.entity.FlowEngineEntity;
import com.sinosoft.ie.booster.workflow.entity.FlowEngineVisibleEntity;
import com.sinosoft.ie.booster.workflow.entity.FlowTaskNodeEntity;
import com.sinosoft.ie.booster.workflow.model.flowengine.PaginationFlowEngine;

import java.util.List;

/**
 * 流程引擎
 *
 * @author booster code generator
 * @since 2021-09-23
 */
public interface FlowEngineService extends IService<FlowEngineEntity> {

	/**
	 * 列表
	 *
	 * @param pagination 分页
	 * @return
	 */
	List<FlowEngineEntity> getList(PaginationFlowEngine pagination);

	/**
	 * 列表
	 *
	 * @return
	 */
	List<FlowEngineEntity> getList();

	/**
	 * 列表
	 *
	 * @return
	 */
	List<FlowEngineEntity> getFlowFormList();

	/**
	 * 信息
	 *
	 * @param id 主键值
	 * @return
	 * @throws WorkFlowException 异常
	 */
	FlowEngineEntity getInfo(Long id) throws WorkFlowException;

	/**
	 * 信息
	 *
	 * @param enCode 流程编码
	 * @return
	 * @throws WorkFlowException 异常
	 */
	FlowEngineEntity getInfoByEnCode(String enCode) throws WorkFlowException;

	/**
	 * 验证名称
	 *
	 * @param fullName 名称
	 * @param id       主键值
	 * @return
	 */
	boolean isExistByFullName(String fullName, Long id);

	/**
	 * 验证编码
	 *
	 * @param enCode 编码
	 * @param id     主键值
	 * @return
	 */
	boolean isExistByEnCode(String enCode, Long id);

	/**
	 * 删除
	 *
	 * @param entity 实体对象
	 */
	void delete(FlowEngineEntity entity);

	/**
	 * 创建
	 *
	 * @param entity      实体对象
	 * @param visibleList 可见范围
	 */
	void create(FlowEngineEntity entity, List<FlowEngineVisibleEntity> visibleList);

	/**
	 * 更新
	 *
	 * @param id          主键值
	 * @param entity      实体对象
	 * @param visibleList 可见范围
	 * @return
	 */
	boolean update(Long id, FlowEngineEntity entity, List<FlowEngineVisibleEntity> visibleList);

	/**
	 * 更新
	 *  @param id     主键值
	 * @param entity 实体对象
	 */
	void update(Long id, FlowEngineEntity entity);

	/**
	 * 上移
	 *
	 * @param id 主键值
	 * @return
	 */
	boolean first(Long id);

	/**
	 * 下移
	 *
	 * @param id 主键值
	 * @return
	 */
	boolean next(Long id);

	/**
	 * 获取流程节点
	 *
	 * @param stepId           当前节点
	 * @param flowTaskNodeList 全部节点
	 * @return
	 */
	long getFlowNodeList(String stepId, List<FlowTaskNodeEntity> flowTaskNodeList);
}
