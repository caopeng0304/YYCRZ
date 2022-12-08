package com.sinosoft.ie.booster.workflow.model.formstaffovertime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 员工加班申请表
 *
 * @author booster开发平台组
 * @since 2021/3/15 8:46
 */
@Data
public class StaffOvertimeInfoVO {
	@ApiModelProperty(value = "主键id")
	private String id;
	@ApiModelProperty(value = "申请人")
	private String applyUser;
	@ApiModelProperty(value = "流程标题")
	private String flowTitle;
	@ApiModelProperty(value = "紧急程度")
	private Integer flowUrgent;
	@ApiModelProperty(value = "总计时间")
	private String totleTime;
	@ApiModelProperty(value = "加班事由")
	private String cause;
	@ApiModelProperty(value = "开始时间")
	private Long startTime;
	@ApiModelProperty(value = "结束时间")
	private Long endTime;
	@ApiModelProperty(value = "申请日期")
	private Long applyDate;
	@ApiModelProperty(value = "申请部门")
	private String department;
	@ApiModelProperty(value = "记入类别")
	private String category;
	@ApiModelProperty(value = "流程主键")
	private String flowId;
	@ApiModelProperty(value = "流程单据")
	private String billNo;

}
