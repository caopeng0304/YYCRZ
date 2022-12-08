package com.sinosoft.ie.booster.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.workflow.entity.FlowTaskNodeEntity;

import java.util.List;

/**
 * 流程节点
 *
 * @author booster code generator
 * @since 2021-09-23
 */
public interface FlowTaskNodeService extends IService<FlowTaskNodeEntity> {

	/**
	 * 列表
	 *
	 * @return
	 */
	List<FlowTaskNodeEntity> getListAll();

	/**
	 * 列表
	 *
	 * @param taskId 任务主键
	 * @return
	 */
	List<FlowTaskNodeEntity> getList(Long taskId);

	/**
	 * 信息
	 *
	 * @param id 主键值
	 * @return
	 */
	FlowTaskNodeEntity getInfo(Long id);

	/**
	 * 删除（根据实例Id）
	 *
	 * @param taskId 任务主键
	 */
	void deleteByTaskId(Long taskId);

	/**
	 * 创建
	 *
	 * @param entitys 实体对象
	 */
	void create(List<FlowTaskNodeEntity> entitys);

	/**
	 * 更新
	 *
	 * @param entity 实体对象
	 */
	void update(FlowTaskNodeEntity entity);

	/**
	 * 更新驳回开始流程节点
	 *
	 * @param taskId 流程id
	 */
	void update(Long taskId);
}
