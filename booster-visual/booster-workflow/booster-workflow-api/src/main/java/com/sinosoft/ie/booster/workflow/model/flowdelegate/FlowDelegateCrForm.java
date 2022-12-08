package com.sinosoft.ie.booster.workflow.model.flowdelegate;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author booster开发平台组
 * @since 2021/3/15 9:18
 */
@Data
public class FlowDelegateCrForm {
	@ApiModelProperty(value = "流程分类")
	@NotBlank(message = "必填")
	private String flowCategory;
	@ApiModelProperty(value = "被委托人")
	@NotBlank(message = "必填")
	private String toUserName;
	@ApiModelProperty(value = "被委托人id")
	@NotBlank(message = "必填")
	private String toUserId;
	@ApiModelProperty(value = "描述")
	private String description;
	@ApiModelProperty(value = "开始日期")
	@NotNull(message = "必填")
	private Long startTime;
	@ApiModelProperty(value = "结束日期")
	@NotNull(message = "必填")
	private Long endTime;
	@ApiModelProperty(value = "委托流程id")
	@NotBlank(message = "必填")
	private String flowId;
	@ApiModelProperty(value = "委托流程名称")
	@NotBlank(message = "必填")
	private String flowName;
}
