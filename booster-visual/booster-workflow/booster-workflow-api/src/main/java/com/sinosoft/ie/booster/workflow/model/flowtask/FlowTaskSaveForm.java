package com.sinosoft.ie.booster.workflow.model.flowtask;

import lombok.Data;

/**
 * 保存草稿任务表单
 *
 * @author haocy
 * @since 2022/1/7
 */
@Data
public class FlowTaskSaveForm {

	/**
	 * 任务id
	 */
	private String id;

	/**
	 * 流程id
	 */
	private String flowId;

	/**
	 * 实例Id
	 */
	private String processId;

	/**
	 * 流程标题
	 */
	private String flowTitle;

	/**
	 * 紧急程度
	 */
	private int flowUrgent;

	/**
	 * 流水号
	 */
	private String billNo;
}
