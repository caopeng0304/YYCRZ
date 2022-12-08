package com.sinosoft.ie.booster.visualdev.model.comfields;

import lombok.Data;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
public class ComFieldsCrForm {
	@NotBlank(message = "必填")
	private String fieldComment;
	@NotBlank(message = "必填")
	private String fieldName;
	@NotBlank(message = "必填")
	private String dataType;
	@NotBlank(message = "必填")
	private String dataLength;
	@NotNull(message = "必填")
	private Integer allowNull;
}
