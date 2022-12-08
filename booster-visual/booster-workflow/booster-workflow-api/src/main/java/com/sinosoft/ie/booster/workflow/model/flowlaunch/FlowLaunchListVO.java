package com.sinosoft.ie.booster.workflow.model.flowlaunch;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author booster开发平台组
 * @since 2021/3/15 9:17
 */
@Data
public class FlowLaunchListVO {
	@ApiModelProperty(value = "任务编码")
	private String enCode;
	@ApiModelProperty(value = "发起人员")
	private String createBy;
	@ApiModelProperty(value = "创建时间")
	private Long createTime;
	@ApiModelProperty(value = "当前节点")
	private String thisStep;
	@ApiModelProperty(value = "所属分类")
	private String flowCategory;
	@ApiModelProperty(value = "流程标题")
	private String fullName;
	@ApiModelProperty(value = "所属流程")
	private String flowName;
	@ApiModelProperty(value = "流程状态", example = "1")
	private Integer status;
	@ApiModelProperty(value = "发起时间")
	private Long startTime;
	@ApiModelProperty(value = "主键id")
	private String id;
	@ApiModelProperty(value = "结束时间")
	private Long endTime;
	@ApiModelProperty(value = "完成情况")
	private Integer completion;
	@ApiModelProperty(value = "备注")
	private String description;
	@ApiModelProperty(value = "流程编码")
	private String flowCode;
	@ApiModelProperty(value = "流程主键")
	private String flowId;
	@ApiModelProperty(value = "表单类型 1-系统表单、2-动态表单")
	private Integer formType;
	@ApiModelProperty(value = "表单数据")
	private String formData;
}
