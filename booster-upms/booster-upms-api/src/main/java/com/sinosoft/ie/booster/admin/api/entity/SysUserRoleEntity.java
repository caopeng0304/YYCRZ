package com.sinosoft.ie.booster.admin.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 用户角色表
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@Data
@TableName("sys_user_role")
@EqualsAndHashCode()
public class SysUserRoleEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@ApiModelProperty(value = "用户id")
	private Long userId;

	/**
	 * 角色ID
	 */
	@ApiModelProperty(value = "角色id")
	private Long roleId;

}
