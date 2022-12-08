package com.sinosoft.ie.booster.workflow.model.flowtask;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 提交任务表单
 *
 * @author haocy
 * @since 2022/1/7
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FlowTaskSubmitForm extends FlowTaskSaveForm {
	/**
	 * 表单对象
	 */
	private Object formEntity;

	/**
	 * 指定审批人
	 */
	private String freeApprover;
}
