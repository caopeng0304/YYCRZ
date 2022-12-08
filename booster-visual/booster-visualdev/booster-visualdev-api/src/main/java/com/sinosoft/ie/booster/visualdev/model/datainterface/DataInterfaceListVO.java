package com.sinosoft.ie.booster.visualdev.model.datainterface;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DataInterfaceListVO {
	@ApiModelProperty(value = "主键Id")
	private String id;
	@ApiModelProperty(value = "接口名称")
	private String fullName;
	@ApiModelProperty(value = "接口类型")
	private Integer dataType;
	@ApiModelProperty(value = "编码")
	private String enCode;
	@ApiModelProperty(value = "排序")
	private Long sort;
	@ApiModelProperty(value = "状态(0-默认，禁用，1-启用)")
	private String enabledFlag;
	@ApiModelProperty(value = "创建人")
	private String createBy;
	@ApiModelProperty(value = "创建时间")
	private Long createTime;

}
