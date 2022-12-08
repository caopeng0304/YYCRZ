package com.sinosoft.ie.booster.visualdev.model.comfields;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class ComFieldsListVO {
	private String id;
	private String fieldComment;
	private String dataType;
	private String dataLength;
	private Integer allowNull;
	@NotBlank(message = "必填")
	private String fieldName;
	private long createTime;
}
