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

/**
 * <p>
 * 部门管理
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@Data
@TableName("sys_dept")
@EqualsAndHashCode(callSuper = true)
public class SysDeptEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableId(value = "dept_id", type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "部门id")
	private Long deptId;

	/**
	 * 部门编号
	 */
	@NotBlank(message = "部门编号不能为空")
	@ApiModelProperty(value = "部门编号")
	private String code;

	/**
	 * 部门名称
	 */
	@NotBlank(message = "部门名称不能为空")
	@ApiModelProperty(value = "部门名称")
	private String name;

	/**
	 * 部门主管
	 */
	@NotBlank(message = "部门主管不能为空")
	@ApiModelProperty(value = "部门主管")
	private String manager;

	/**
	 * 层级
	 */
	@ApiModelProperty(value = "层级值")
	private Integer levelCode;

	/**
	 * 区域编号
	 */
	@NotBlank(message = "区域编号不能为空")
	@ApiModelProperty(value = "区域编号")
	private String areaCode;

	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序值")
	private Integer sort;

	/**
	 * 父级部门id
	 */
	@ApiModelProperty(value = "父级部门id")
	private Long parentId;

	/**
	 * 是否删除 -1：已删除 0：正常
	 */
	@TableLogic
	private String delFlag;

}
