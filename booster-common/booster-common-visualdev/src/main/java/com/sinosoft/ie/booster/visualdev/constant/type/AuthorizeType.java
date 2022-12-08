package com.sinosoft.ie.booster.visualdev.constant.type;

import lombok.Data;

/**
 * 权限类型常量表
 *
 * @author booster开发平台组
 * @since 2021-03-23
 */
@Data
public class AuthorizeType {
	/**
	 * 用户权限
	 */
	public static final String USER = "User";
	/**
	 * 岗位权限
	 */
	public static final String POSITION = "Position";
	/**
	 * 角色权限
	 */
	public static final String ROLE = "Role";
	/**
	 * 按钮权限
	 */
	public static final String BUTTON = "button";
	/**
	 * 菜单权限
	 */
	public static final String MODULE = "module";
	/**
	 * 列表权限
	 */
	public static final String COLUMN = "column";
	/**
	 * 数据权限
	 */
	public static final String RESOURCE = "resource";
}
