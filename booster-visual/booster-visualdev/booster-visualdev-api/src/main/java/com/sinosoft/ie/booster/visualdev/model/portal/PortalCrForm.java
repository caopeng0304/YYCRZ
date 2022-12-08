package com.sinosoft.ie.booster.visualdev.model.portal;

import lombok.Data;

/**
 * @author booster开发平台组
 * @since 2021-10-21 14:23:30
 */
@Data
public class PortalCrForm {
	private String fullName;
	private String enCode;
	private String enabledFlag;
	private String description;
	private String formData;
	private String category;
	private Long sort;
}
