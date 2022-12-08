package com.sinosoft.ie.booster.visualdev.model.datascreen;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class DataCrForm {
	private String enabledFlag;
	private String password;
	@NotBlank(message = "必填")
	private String categoryId;
	@NotBlank(message = "必填")
	private String fullName;
	private String detail;
	private String component;
	private String screenShot;
}
