package com.sinosoft.ie.booster.workflow.model.flowtask;

import lombok.Data;

import java.util.Date;

/**
 * @author booster开发平台组
 * @since 2021/3/15 9:17
 */
@Data
public class FlowTaskWaitListModel {

	private String id;
	private String processId;
	private String enCode;
	private String fullName;
	private Integer flowUrgent;
	private String flowId;
	private String flowCode;
	private String flowName;
	private String flowCategory;
	private Date startTime;
	private Date endTime;
	private String thisStep;
	private String thisStepId;
	private Integer status;
	private Integer completion;
	private String createBy;
	private Date createTime;
	private String handleId;
	private String updateBy;
	private Date updateTime;
	private String nodePropertyJson;
}
