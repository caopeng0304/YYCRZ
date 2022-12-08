package com.sinosoft.ie.booster.workflow.model.flowtask;

import lombok.Data;

/**
 * @author booster开发平台组
 * @since 2021/3/15 9:17
 */
@Data
public class FlowTaskForm {
	/**
	 * 引擎id
	 **/
	private String flowId;
	/**
	 * 界面数据
	 **/
	private String data;
	/**
	 * 0.提交 1.保存
	 **/
	private String status;
	/**
	 * 指定审批人
	 **/
	private String freeApprover;
}
