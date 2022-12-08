package com.sinosoft.ie.booster.visualdev.model.dblink;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DbLinkListVO {
	@ApiModelProperty(value = "连接名称")
	private String fullName;
	@ApiModelProperty(value = "连接驱动")
	private String dbType;
	@ApiModelProperty(value = "主机名称")
	private String host;
	@ApiModelProperty(value = "端口")
	private String port;
	@ApiModelProperty(value = "创建时间", example = "1")
	private long createTime;
	@ApiModelProperty(value = "创建人")
	private String createBy;
	@ApiModelProperty(value = "主键")
	private String id;
	@ApiModelProperty(value = "修改时间")
	private long updateTime;
	@ApiModelProperty(value = "修改用户")
	private String updateBy;
	@ApiModelProperty(value = "有效标志")
	private String enabledFlag;
	@ApiModelProperty(value = "排序码")
	private long sort;


}
