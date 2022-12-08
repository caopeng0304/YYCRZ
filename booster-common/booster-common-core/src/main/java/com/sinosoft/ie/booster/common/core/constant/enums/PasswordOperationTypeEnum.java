package com.sinosoft.ie.booster.common.core.constant.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 密码操作类型
 *
 * @author haocy
 * @since 2021-12-01
 */
@Getter
@RequiredArgsConstructor
public enum PasswordOperationTypeEnum {

	/**
	 * 用户修改
	 */
	USER("0", "用户修改"),

	/**
	 * 系统重置
	 */
	SYSTEM("1", "系统重置");

	/**
	 * 类型
	 */
	private final String type;

	/**
	 * 描述
	 */
	private final String description;

}
