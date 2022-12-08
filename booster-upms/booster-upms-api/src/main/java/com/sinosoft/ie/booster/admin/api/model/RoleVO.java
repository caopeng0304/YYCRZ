package com.sinosoft.ie.booster.admin.api.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author lengleng
 * @since 2020/2/10
 */
@Data
@ApiModel(value = "前端角色展示对象")
public class RoleVO {

	/**
	 * 角色id
	 */
	private Long roleId;

	/**
	 * 菜单列表
	 */
	private String menuIds;

}
