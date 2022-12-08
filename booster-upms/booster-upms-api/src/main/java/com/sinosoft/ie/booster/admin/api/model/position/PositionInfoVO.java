package com.sinosoft.ie.booster.admin.api.model.position;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * SysPosition模型
 *
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-09-14 15:10:15
 */
@Data
@ApiModel
public class PositionInfoVO {

	@ApiModelProperty(value = "id")
	private String id;
	@ApiModelProperty(value = "上级id")
	private String deptId;
	@ApiModelProperty(value = "岗位名称")
	private String fullName;
	@ApiModelProperty(value = "岗位编码")
	private String enCode;
	@ApiModelProperty(value = "岗位类型")
	private String type;
	@ApiModelProperty(value = "岗位状态")
	private String enabledFlag;
	@ApiModelProperty(value = "岗位说明")
	private String description;
	@ApiModelProperty(value = "排序")
	private Long sort;
}