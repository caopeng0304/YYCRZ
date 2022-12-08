package com.sinosoft.ie.booster.common.core.constant.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author haocy
 * @since 2021-08-17
 * <p>
 * 系统变量类型
 */
@Getter
@RequiredArgsConstructor
public enum SystemVariableEnum {
	DEPT_ID("dept_id"),
	DEPT_CODE("dept_code"),
	USER_ID("user_id"),
	USER_NAME("user_name");

	private final String value;

	public static SystemVariableEnum toEnum(String value) {
		value = value.toLowerCase();
		switch (value) {
			case "dept_id":
				return DEPT_ID;
			case "dept_code":
				return DEPT_CODE;
			case "user_id":
				return USER_ID;
			case "user_name":
				return USER_NAME;
			default:
				return null;
		}
	}
}
