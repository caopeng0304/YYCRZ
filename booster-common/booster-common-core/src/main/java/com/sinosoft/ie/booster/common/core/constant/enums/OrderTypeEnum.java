package com.sinosoft.ie.booster.common.core.constant.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 排序类型
 *
 * @author haocy
 * @since 2021-11-30
 */
@Getter
@RequiredArgsConstructor
public enum OrderTypeEnum {

	/**
	 * 正序
	 */
	ASC("0", "正序"),

	/**
	 * 倒序
	 */
	DESC("1", "倒序"),

	/**
	 * 正序或倒序
	 */
	ASC_OR_DESC("3", "正序或倒序");

	/**
	 * 类型
	 */
	private final String type;

	/**
	 * 描述
	 */
	private final String description;

}
