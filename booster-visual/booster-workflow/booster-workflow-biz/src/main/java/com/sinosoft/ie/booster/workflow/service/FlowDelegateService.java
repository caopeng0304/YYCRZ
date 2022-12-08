package com.sinosoft.ie.booster.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.common.core.model.Pagination;
import com.sinosoft.ie.booster.workflow.entity.FlowDelegateEntity;

import java.util.List;

/**
 * 流程委托
 *
 * @author booster code generator
 * @since 2021-09-23
 */
public interface FlowDelegateService extends IService<FlowDelegateEntity> {

	/**
	 * 列表
	 *
	 * @param pagination 请求参数
	 * @return
	 */
	List<FlowDelegateEntity> getList(Pagination pagination);

	/**
	 * 列表
	 *
	 * @return
	 */
	List<FlowDelegateEntity> getList();



	/**
	 * 信息
	 *
	 * @param id 主键值
	 * @return
	 */
	FlowDelegateEntity getInfo(Long id);

	/**
	 * 删除
	 *
	 * @param entity 实体对象
	 */
	void delete(FlowDelegateEntity entity);

	/**
	 * 创建
	 *
	 * @param entity 实体对象
	 */
	void create(FlowDelegateEntity entity);

	/**
	 * 获取委托的表单
	 *
	 * @param userName 主键值
	 * @return
	 */
	List<FlowDelegateEntity> getUser(String userName);

	/**
	 * 更新
	 *
	 * @param id     主键值
	 * @param entity 实体对象
	 * @return
	 */
	boolean update(Long id, FlowDelegateEntity entity);

}
