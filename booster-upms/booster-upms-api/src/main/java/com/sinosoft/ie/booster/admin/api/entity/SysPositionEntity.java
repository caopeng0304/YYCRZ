package com.sinosoft.ie.booster.admin.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sinosoft.ie.booster.common.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 岗位信息
 *
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-09-14 15:10:15
 */
@Data
@TableName("sys_position")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "岗位信息")
public class SysPositionEntity extends BaseEntity {

	/**
	 * 自然主键
	 */
	@ApiModelProperty(value = "自然主键")
	@TableId(type = IdType.ASSIGN_ID)
	private Long id;

	/**
	 * 部门ID
	 */
	@ApiModelProperty(value = "部门ID")
	private Long deptId;

	/**
	 * 岗位名称
	 */
	@ApiModelProperty(value = "岗位名称")
	private String fullName;

	/**
	 * 岗位编号
	 */
	@ApiModelProperty(value = "岗位编号")
	private String encode;

	/**
	 * 岗位类型
	 */
	@ApiModelProperty(value = "岗位类型")
	private String type;

	/**
	 * 扩展属性
	 */
	@ApiModelProperty(value = "扩展属性")
	private String propertyJson;

	/**
	 * 描述
	 */
	@ApiModelProperty(value = "描述")
	private String description;

	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer sort;

	/**
	 * 有效标志
	 */
	@ApiModelProperty(value = "有效标志")
	private String enabledFlag;

	/**
	 * 删除标志
	 */
	@ApiModelProperty(value = "删除标志")
	@TableLogic
	private String delFlag;
}
