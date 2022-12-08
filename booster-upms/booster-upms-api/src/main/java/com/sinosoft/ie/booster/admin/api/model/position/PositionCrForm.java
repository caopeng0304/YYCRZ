package com.sinosoft.ie.booster.admin.api.model.position;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * SysPosition模型
 *
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-09-14 15:10:15
 */
@Data
@ApiModel
public class PositionCrForm {

	@NotBlank(message = "必填")
	@ApiModelProperty(value = "岗位编码")
	private String enCode;
	@NotBlank(message = "必填")
	@ApiModelProperty(value = "所属部门(id)")
	private String deptId;
	@NotNull(message = "必填")
	@ApiModelProperty(value = "岗位状态")
	private String enabledFlag;
	@NotBlank(message = "必填")
	@ApiModelProperty(value = "岗位名称")
	private String fullName;

	private String description;
	@NotNull(message = "必填")
	@ApiModelProperty(value = "岗位类型(id)")
	private Integer type;
	@ApiModelProperty(value = "排序")
	private Long sort;
}