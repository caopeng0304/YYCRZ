package com.sinosoft.ie.booster.workflow.model.flowbefore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author booster开发平台组
 * @since 2021/3/15 9:18
 */
@Data
public class FlowTaskOperatorEntityInfoModel {
	@ApiModelProperty(value = "节点经办主键")
	private String id;
	@ApiModelProperty(value = "经办对象")
	private String handleType;
	@ApiModelProperty(value = "经办主键")
	private String handleId;
	@ApiModelProperty(value = "处理状态 0-拒绝、1-同意")
	private Integer handleStatus;
	@ApiModelProperty(value = "处理时间")
	private Long handleTime;
	@ApiModelProperty(value = "节点编码")
	private String nodeCode;
	@ApiModelProperty(value = "节点名称")
	private String nodeName;
	@ApiModelProperty(value = "是否完成")
	private Integer completion;
	@ApiModelProperty(value = "描述")
	private String description;
	@ApiModelProperty(value = "创建时间")
	private Long createTime;
	@ApiModelProperty(value = "节点主键")
	private String taskNodeId;
	@ApiModelProperty(value = "任务主键")
	private String taskId;
}
