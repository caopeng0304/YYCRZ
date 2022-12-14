package com.sinosoft.ie.booster.visualdev.constant.type;

/**
 * 请求方法枚举类
 *
 * @author booster开发平台组
 * @since 2021-03-23
 */
public enum MethodType {
	/**
	 * GET请求
	 */
	GET("GET"),
	/**
	 * POST 请求
	 */
	POST("POST"),
	/**
	 * PUT 请求
	 */
	PUT("PUT"),
	;
	private String method;

	MethodType(String method) {
		this.method = method;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}
