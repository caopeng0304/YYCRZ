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
 * 菜单权限表
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@Data
@TableName("sys_menu")
@EqualsAndHashCode(callSuper = true)
public class SysMenuEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 菜单ID
	 */
	@TableId(value = "menu_id", type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "菜单id")
	private Long menuId;

	/**
	 * 菜单名称
	 */
	@NotBlank(message = "菜单名称不能为空")
	@ApiModelProperty(value = "菜单名称")
	private String name;

	/**
	 * 功能编号
	 */
	@ApiModelProperty(value = "功能编号")
	private String encode;

	/**
	 * 菜单权限标识
	 */
	@ApiModelProperty(value = "菜单权限标识")
	private String permission;

	/**
	 * 父菜单ID
	 */
	@NotNull(message = "菜单父ID不能为空")
	@ApiModelProperty(value = "菜单父id")
	private Long parentId;

	/**
	 * 图标
	 */
	@ApiModelProperty(value = "菜单图标")
	private String icon;

	/**
	 * 前端URL
	 */
	@ApiModelProperty(value = "前端路由标识路径")
	private String path;

	/**
	 * 排序值
	 */
	@ApiModelProperty(value = "排序值")
	private Integer sort;

	/**
	 * 菜单类型 （0菜单 1按钮 2功能 3大屏）
	 */
	@NotNull(message = "菜单类型不能为空")
	private String type;

	/**
	 * 客户端类型 （0Web 1App）
	 */
	@NotNull(message = "客户端类型不能为空")
	private String clientType;

	/**
	 * 功能模块Id
	 */
	@ApiModelProperty(value = "功能模块Id")
	private Long moduleId;

	/**
	 * 路由缓冲
	 */
	@ApiModelProperty(value = "路由缓冲")
	private String keepAlive;

	/**
	 * 0--正常 1--删除
	 */
	@TableLogic
	private String delFlag;

}
