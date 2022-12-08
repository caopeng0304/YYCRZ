package com.sinosoft.ie.booster.yypass.model.passbasicinfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;


import java.sql.Blob;
import java.util.Date;
import java.util.List;

/**
 *
 * ApsSystem模型
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-08-08 14:06:38
 */
@Data
@ApiModel
public class PassBasicInfoCrForm {

    /**
     * 业务系统id
     */
    @ApiModelProperty(value = "业务系统id")
    private String id;

	private String personType;
	private String names;
	private String sex;
	private String phone;
	private String cardType;
	private String cardNumber;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date birthday;
	private String workAddress;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	private String unit;
	private String unitId;
	private String unitName;
	private String reason;
	private String modes;
	private String address;
	private String addressDetail;
	private String descs;
	private String personState;
	private String isInout;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date delayStartTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date delayToTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date getPassTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date addTime;
	private String addPersonId;
	private String addPersonName;
	private String idCard;
	private String isDelete;

	private String delayDesc;

	private String nation;
	private String yrUnit;
	private String yrUnitArr;
	private String yrName;
	private String yrPhone;
	private String unitPhone;
	private String emergencyName;
	private String emergencyPhone;
	private String auditName;
	private String isGrantPass;
	private Integer delayNumber;
	private String isGrantFace;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date grantFaceTime;

	private String isPromise;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date promiseTime;

	private String doorPersonId;
	private String doorFaceId;

	private String isLogout; //	注销 0，未注销 1
	private Date LogoutTime;

	private String isPower;  // 是否有权限操作，有：0，无：1

	private Date oldStartTime;
	private Date oldEndTime;

	private List<String> files1;
	private List<String> files2;

}