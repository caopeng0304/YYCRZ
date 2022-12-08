package com.sinosoft.ie.booster.common.core.constant.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author lengleng
 * @since 2020-02-17
 * <p>
 * 菜单类型
 */
@Getter
@RequiredArgsConstructor
public enum MenuTypeEnum {

	/**
	 * 左侧菜单
	 */
	LEFT_MENU("0", "left"),

	/**
	 * 按钮
	 */
	BUTTON("1", "button"),

	/**
	 * 功能菜单
	 */
	FUNCTION_MENU("2", "FUNCTION_MENU"),

	/**
	 * 大屏菜单
	 */
	SCREEN_MENU("3", "SCREEN_MENU");

	/**
	 * 类型
	 */
	private final String type;

	/**
	 * 描述
	 */
	private final String description;

}
