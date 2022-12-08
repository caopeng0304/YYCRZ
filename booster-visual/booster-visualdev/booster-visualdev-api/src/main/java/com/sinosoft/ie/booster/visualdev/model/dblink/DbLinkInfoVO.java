package com.sinosoft.ie.booster.visualdev.model.dblink;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DbLinkInfoVO extends DbLinkUpForm {
	@ApiModelProperty(value = "主键")
	private String id;
}
