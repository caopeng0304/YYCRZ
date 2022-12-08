package com.sinosoft.ie.booster.yypass.model.passbasicinfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinosoft.ie.booster.common.core.model.Pagination;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 *
 * ApsSystem模型
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-08-08 14:06:38
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel
public class PassBasicInfoPagination extends Pagination {

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
	private String unitId;
	private String unitName;
	private String reason;
	private String modes;
	private String address;
	private String addressDetail;
	private String descs;
	private String personState;
	private String isInout;
	private String delayToTime;
	private Date addTime;
	private String addPersonId;
	private String addPersonName;
	private String idCard;
	private String isDelete;

	private String nation;
	private String yrUnit;
	private String yrName;
	private String yrPhone;
	private String yrUnitArr;
	private String unitPhone;
	private String emergencyName;
	private String emergencyPhone;
	private String auditName;

	private Date getPassTime;
	private String delayDesc;
	private String isGrantPass;
	private Integer delayNumber;
	private String isGrantFace;
	private Date grantFaceTime;
	private String isPromise;
	private Date promiseTime;

	private String isLogout;
	private Date LogoutTime;

	/*@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")*/
	private String startTime1;

	/*@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")*/
	private String endTime1;

	/*@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")*/
	private String startTime2;

	/*@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")*/
	private String endTime2;

	private String personStatus;
}