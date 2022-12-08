package com.sinosoft.ie.booster.yypass.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 *
 * 
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-08-08 14:06:39
 */
@Data
@TableName("pass_log_no_power")
@ApiModel(value = "")
public class PassLogNoPowerEntity {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

	@ApiModelProperty(value = "申请表ID")
	private String passBasicInfoId;

	@ApiModelProperty(value = "状态")
	private String state;

	@ApiModelProperty(value = "办理人ID")
	private String personId;

	@ApiModelProperty(value = "办理人姓名")
	private String personName;

	@ApiModelProperty(value = "添加时间")
	private Date addTime;

	@ApiModelProperty(value = "备注")
	private String descs;

	@ApiModelProperty(value = "是否删除")
	private String isDelete;

	@ApiModelProperty(value = "移交人ID")
	private String transferPersonId;

	@ApiModelProperty(value = "移交人姓名")
	private String transferPersonName;

	@ApiModelProperty(value = "移交时间")
	private Date transferTime;

	private String unitId;

}
