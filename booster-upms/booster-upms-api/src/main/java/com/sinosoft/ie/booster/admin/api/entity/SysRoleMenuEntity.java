package com.sinosoft.ie.booster.admin.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 角色菜单表
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@Data
@TableName("sys_role_menu")
@EqualsAndHashCode()
public class SysRoleMenuEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 角色ID
	 */
	@ApiModelProperty(value = "角色id")
	private Long roleId;

	/**
	 * 菜单ID
	 */
	@ApiModelProperty(value = "菜单id")
	private Long menuId;

}
