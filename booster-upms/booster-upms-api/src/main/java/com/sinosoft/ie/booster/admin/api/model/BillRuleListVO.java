package com.sinosoft.ie.booster.admin.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BillRuleListVO {
	@ApiModelProperty(value = "id")
	private Long id;
	@ApiModelProperty(value = "业务名称")
	private String fullName;
	@ApiModelProperty(value = "业务编码")
	private Integer digit;
	@ApiModelProperty(value = "流水位数")
	private String enCode;
	@ApiModelProperty(value = "当前流水号")
	private String outputNumber;
	@ApiModelProperty(value = "排序码")
	private long sort;
}
