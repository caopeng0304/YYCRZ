package com.sinosoft.ie.booster.workflow.model.flowtask;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author booster开发平台组
 * @since 2021/3/15 9:17
 */
@Data
public class FlowTaskInfoVO {
	@ApiModelProperty(value = "主键id")
	private String id;
	@ApiModelProperty(value = "引擎id")
	private String flowId;
	@ApiModelProperty(value = "界面数据")
	private String data;
}
