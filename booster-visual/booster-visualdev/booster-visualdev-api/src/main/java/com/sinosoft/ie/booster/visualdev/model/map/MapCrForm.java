package com.sinosoft.ie.booster.visualdev.model.map;

import lombok.Data;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
public class MapCrForm {
	@NotBlank(message = "必填")
	private String fullName;
	@NotBlank(message = "必填")
	private String enCode;
	@NotBlank(message = "必填")
	private String data;
	private long sort;
	@NotNull(message = "必填")
	private String enabledFlag;
}
