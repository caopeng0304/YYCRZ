package com.sinosoft.ie.booster.workflow.model.flowdelegate;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author booster开发平台组
 * @since 2021/3/15 9:18
 */
@Data
public class FlowDelegateInfoVO {
	@ApiModelProperty(value = "主键id")
	private String id;
	@ApiModelProperty(value = "流程分类")
	private String flowCategory;
	@ApiModelProperty(value = "被委托人")
	private String toUserName;
	@ApiModelProperty(value = "被委托人")
	private String toUserId;
	@ApiModelProperty(value = "描述")
	private String description;
	@ApiModelProperty(value = "开始日期")
	private Long startTime;
	@ApiModelProperty(value = "结束日期")
	private Long endTime;
	@ApiModelProperty(value = "委托流程id")
	private String flowId;
	@ApiModelProperty(value = "委托流程名称")
	private String flowName;
}
