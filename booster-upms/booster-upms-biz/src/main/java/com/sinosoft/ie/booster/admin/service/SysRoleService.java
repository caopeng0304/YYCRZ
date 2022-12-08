package com.sinosoft.ie.booster.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinosoft.ie.booster.admin.api.entity.SysRoleEntity;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
public interface SysRoleService extends IService<SysRoleEntity> {

	/**
	 * 通过用户ID，查询角色信息
	 *
	 * @param userId
	 * @return
	 */
	List<SysRoleEntity> findRolesByUserId(Long userId);

	/**
	 * 通过角色ID，删除角色
	 *
	 * @param id
	 * @return
	 */
	Boolean removeRoleById(Long id);

	/**
	 * 保存角色信息
	 *
	 * @param roleEntity 角色信息
	 * @return success/fail
	 */
	Boolean saveRole(SysRoleEntity roleEntity);

	/**
	 * 更新指定角色信息
	 *
	 * @param roleEntity 角色信息
	 * @return success/fail
	 */
	Boolean updateRole(SysRoleEntity roleEntity);

}
