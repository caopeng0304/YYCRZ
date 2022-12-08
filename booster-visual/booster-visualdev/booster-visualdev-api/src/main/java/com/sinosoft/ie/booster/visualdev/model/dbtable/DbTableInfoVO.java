package com.sinosoft.ie.booster.visualdev.model.dbtable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class DbTableInfoVO {
	@NotBlank(message = "必填")
	@ApiModelProperty(value = "表名")
	private String table;
	@NotBlank(message = "必填")
	@ApiModelProperty(value = "表说明")
	private String tableName;
	private String newTable;
}
