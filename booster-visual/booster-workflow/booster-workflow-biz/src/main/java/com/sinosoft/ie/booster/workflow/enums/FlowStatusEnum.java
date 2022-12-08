package com.sinosoft.ie.booster.workflow.enums;

/**
 * 提交状态
 *
 * @author booster开发平台组
 * @since 2021年9月26日 上午9:18
 */
public enum FlowStatusEnum {
	//保存
	save("1"),
	// 提交
	submit("0");

	private String message;

	FlowStatusEnum(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
