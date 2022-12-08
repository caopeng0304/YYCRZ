package com.sinosoft.ie.booster.admin.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.admin.api.entity.SysDeptEntity;

import java.util.List;

/**
 * <p>
 * 部门管理 服务类
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
public interface SysDeptService extends IService<SysDeptEntity> {

	/**
	 * 查询部门树菜单
	 * @return 树
	 */
	List<Tree<Long>> listDeptTrees();

	/**
	 * 查询用户部门树
	 * @return
	 */
	List<Tree<Long>> listCurrentUserDeptTrees();

	/**
	 * 添加信息部门
	 * @param sysDept
	 * @return
	 */
	Boolean saveDept(SysDeptEntity sysDept);

	/**
	 * 删除部门
	 * @param id 部门 ID
	 * @return 成功、失败
	 */
	Boolean removeDeptById(Long id);

	/**
	 * 更新部门
	 * @param sysDept 部门信息
	 * @return 成功、失败
	 */
	Boolean updateDeptById(SysDeptEntity sysDept);

}
