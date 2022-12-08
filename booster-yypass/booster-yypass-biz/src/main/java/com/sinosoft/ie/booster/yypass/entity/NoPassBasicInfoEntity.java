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
@TableName("no_pass_basic_info")
@ApiModel(value = "")
public class NoPassBasicInfoEntity {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

	private String personType;
	private String names;
	private String sex;
	private String phone;
	private String cardType;
	private String cardNumber;
	private Date birthday;
	private String workAddress;
	private Date startTime;
	private Date endTime;
	private String unit;
	private String unitName;
	private String reason;
	private String modes;
	private String address;
	private String addressDetail;
	private String descs;
	private String personState;
	private String isInout;
	private Date delayToTime;
	private Date addTime;
	private String addPersonId;
	private String addPersonName;
	private String idCard;
	private String isDelete;

	private String nation;
	private String yrUnit;
	private String yrName;
	private String yrPhone;
	private String unitPhone;
	private String emergencyName;
	private String emergencyPhone;
	private String auditName;

}
