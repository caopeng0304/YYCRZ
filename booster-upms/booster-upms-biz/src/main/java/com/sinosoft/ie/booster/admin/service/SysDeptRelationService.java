package com.sinosoft.ie.booster.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.admin.api.entity.SysDeptEntity;
import com.sinosoft.ie.booster.admin.api.entity.SysDeptRelationEntity;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
public interface SysDeptRelationService extends IService<SysDeptRelationEntity> {

	/**
	 * 新建部门关系
	 * @param sysDept 部门
	 */
	void saveDeptRelation(SysDeptEntity sysDept);

	/**
	 * 通过ID删除部门关系
	 * @param id
	 */
	void removeDeptRelationById(Long id);

	/**
	 * 更新部门关系
	 * @param relation
	 */
	void updateDeptRelation(SysDeptRelationEntity relation);

}
