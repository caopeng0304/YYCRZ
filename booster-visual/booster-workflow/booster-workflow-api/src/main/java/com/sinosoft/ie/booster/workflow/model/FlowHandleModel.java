package com.sinosoft.ie.booster.workflow.model;

import lombok.Data;

/**
 * @author booster开发平台组
 * @since 2021/3/15 9:17
 */
@Data
public class FlowHandleModel {
	/**
	 * 意见
	 **/
	private String handleOpinion;
	/**
	 * 指定审批人
	 **/
	private String freeApprover;
	/**
	 * 表单数据
	 **/
	private Object formData;
	/**
	 * 编码
	 **/
	private String encode;
}
