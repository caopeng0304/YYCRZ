package com.sinosoft.ie.booster.admin.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sinosoft.ie.booster.common.core.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@Data
@TableName("sys_role")
@EqualsAndHashCode(callSuper = true)
public class SysRoleEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableId(value = "role_id", type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "角色编号")
	private Long roleId;

	@NotBlank(message = "角色名称 不能为空")
	@ApiModelProperty(value = "角色名称")
	private String roleName;

	@NotBlank(message = "角色标识 不能为空")
	@ApiModelProperty(value = "角色标识")
	private String roleCode;

	@NotBlank(message = "角色描述 不能为空")
	@ApiModelProperty(value = "角色描述")
	private String roleDesc;

	@NotNull(message = "数据权限类型不能为空")
	@ApiModelProperty(value = "数据权限类型")
	private Integer dsType;

	@ApiModelProperty(value = "数据权限作用范围")
	private String dsScope;

	@NotNull(message = "密码更新周期不能为空")
	@ApiModelProperty(value = "密码更新周期（天）")
	private Integer passwordUpdateCycle;

	/**
	 * 删除标识（0-正常,1-删除）
	 */
	@TableLogic
	private String delFlag;

}
