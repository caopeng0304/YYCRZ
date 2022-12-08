package com.sinosoft.ie.booster.admin.api.model.position;

import com.alibaba.fastjson.annotation.JSONField;
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
public class PositionListVO {

	private String id;
	private String fullName;
	private String enCode;
	private String type;
	private Long creatorTime;
	private String description;
	@JSONField(name = "deptId")
	private String department;
	private String enabledFlag;
	@ApiModelProperty(value = "排序")
	private Long sort;

}