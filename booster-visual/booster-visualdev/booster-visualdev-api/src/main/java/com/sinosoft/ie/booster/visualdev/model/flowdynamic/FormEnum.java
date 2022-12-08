package com.sinosoft.ie.booster.visualdev.model.flowdynamic;

/**
 * 引擎模板
 *
 * @author booster开发平台组
 * @since 2021年9月29日 上午9:18
 */
public enum FormEnum {
	//栅格
	row("row"),
	//子表
	table("table"),
	//主表
	mast("mast"),
	//卡片
	card("card");

	private String message;

	FormEnum(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
