package com.sinosoft.ie.booster.visualdev.model.base;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class DownloadCodeForm {

	/**
	 * 所属包名
	 */
	@NotBlank(message = "packageName必填" )
	private String packageName;

	/**
	 * 所属模块
	 */
	@NotBlank(message = "moduleName必填" )
	private String moduleName;

	/**
	 * 主功能备注
	 */
	private String description;

	/**
	 * 数据源id
	 */
	private String dataSourceId;
}
