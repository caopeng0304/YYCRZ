package com.sinosoft.ie.booster.admin.api.model;

import com.sinosoft.ie.booster.admin.api.entity.SysDeptEntity;
import com.sinosoft.ie.booster.admin.api.entity.SysUserEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息大全，包括用户基本信息、所属部门信息、权限标识集合、角色集合等
 *
 * @author haocy
 * @since 2021/12/03
 */
@Data
public class UserInfoModel implements Serializable {

	/**
	 * 用户基本信息
	 */
	private SysUserEntity sysUser;

	/**
	 * 所属部门信息
	 */
	private SysDeptEntity sysDept;

	/**
	 * 权限标识集合
	 */
	private String[] permissions;

	/**
	 * 角色集合
	 */
	private Long[] roles;

	/**
	 * 岗位集合
	 */
	private Long[] positions;

	/**
	 * 密码更新周期（天）
	 */
	private Integer passwordUpdateCycle;

	private Object rolesList;
}
