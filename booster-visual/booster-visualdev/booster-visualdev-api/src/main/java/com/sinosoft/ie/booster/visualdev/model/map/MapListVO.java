package com.sinosoft.ie.booster.visualdev.model.map;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MapListVO {
	@ApiModelProperty(value = "主键")
	private String id;
	@ApiModelProperty(value = "地图名称")
	private String fullName;
	@ApiModelProperty(value = "地图编码")
	private String enCode;
	@ApiModelProperty(value = "添加时间")
	private long createTime;
	@ApiModelProperty(value = "添加者")
	private String createBy;
	@ApiModelProperty(value = "排序")
	private long sort;
	@ApiModelProperty(value = "状态")
	private String enabledFlag;
}
