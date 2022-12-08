package com.sinosoft.ie.booster.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lengleng
 * @since 2018/9/30
 * 流程状态
 */
@Getter
@AllArgsConstructor
public enum ProcessStatusEnum {
	/**
	 * 激活状态
	 */
	ACTIVE("active", "激活状态"),

	/**
	 * 挂起状态
	 */
	SUSPEND("suspend", "挂起状态");

	/**
	 * 类型
	 */
	private final String status;
	/**
	 * 描述
	 */
	private final String description;
}
