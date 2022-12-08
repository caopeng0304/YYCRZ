package com.sinosoft.ie.booster.visualdev.constant.enums;

/**
 * 字典类型枚举类
 *
 * @author haocy
 * @since 2021-09-18
 */
public enum DicTypeEnum {

	/**
	 * 代码生成模块流程表单分类
	 */
	CODEGEN_FLOW_FORM_CATEGORY("codegen_flow_form_category"),
	/**
	 * 代码生成模块移动表单分类
	 */
	CODEGEN_APP_FORM_CATEGORY("codegen_app_form_category"),
	/**
	 * 代码生成模块功能表单分类
	 */
	CODEGEN_WEB_FORM_CATEGORY("codegen_web_form_category"),

	/**
	 * 工作流模块流程引擎类型
	 */
	WORKFLOW_FLOW_ENGINE_TYPE("workflow_flow_engine_type"),

	/**
	 * 工作流模块流程设计分类
	 */
	WORKFLOW_FLOW_DESIGN_CATEGORY("workflow_flow_design_category"),

	/**
	 * 工作流模块流程表单类型
	 */
	WORKFLOW_FLOW_FORM_TYPE("workflow_flow_form_type"),

	/**
	 * 可视化开发模块门户设计分类
	 */
	VISUADEV_PORTAL_DESIGN_CATEGORY("visuadev_portal_design_category"),

	/**
	 * 可视化开发模块移动设计分类
	 */
	VISUADEV_APP_DESIGN_CATEGORY("visuadev_app_design_category"),

	/**
	 * 可视化开发模块报表设计分类
	 */
	VISUADEV_REPORT_DESIGN_CATEGORY("visuadev_report_design_category"),

	/**
	 * 可视化开发模块功能设计分类
	 */
	VISUADEV_WEB_DESIGN_CATEGORY("visuadev_web_design_category"),

	/**
	 * 可视化开发模块大屏设计分类
	 */
	VISUADEV_SCREEN_DESIGN_CATEGORY("visuadev_screen_design_category"),

	/**
	 * 数据接口分类
	 */
	DATA_INTERFACE_CATEGORY("data_interface_category");

	private final String type;

	DicTypeEnum(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
