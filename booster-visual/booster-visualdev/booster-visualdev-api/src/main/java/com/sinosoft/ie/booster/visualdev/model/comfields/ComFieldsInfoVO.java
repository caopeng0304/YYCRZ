package com.sinosoft.ie.booster.visualdev.model.comfields;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class ComFieldsInfoVO {
	private String id;
	private String fieldComment;
	private String dataType;
	@NotBlank(message = "必填")
	private String fieldName;
	private String dataLength;
	private Integer allowNull;
	private long createTime;
}
