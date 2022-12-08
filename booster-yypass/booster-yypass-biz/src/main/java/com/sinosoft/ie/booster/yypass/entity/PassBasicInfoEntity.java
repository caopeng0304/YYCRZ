package com.sinosoft.ie.booster.yypass.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Blob;
import java.sql.Clob;
import java.util.Date;

/**
 *
 * 
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-08-08 14:06:39
 */
@Data
@TableName("pass_basic_info")
@ApiModel(value = "")
public class PassBasicInfoEntity {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

	@ApiModelProperty(value = "")
    private Long sNo;

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
	private String isInout;  //	开传 0，关传 1
	private Date delayStartTime;
	private Date delayToTime;
	private Date addTime;
	private String addPersonId;
	private String addPersonName;
	private String idCard;
	private String isDelete;

	private String nation;
	private String yrUnit;
	private String yrUnitArr;
	private String yrName;
	private String yrPhone;
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

	private String doorPersonId;
	private String doorFaceId;

	private String applyBatch;

	private String isLogout; //	注销 0，未注销 1
	private Date LogoutTime;

	private String isPower;  // 是否有权限操作，有：0，无：1

	private Date oldStartTime;
	private Date oldEndTime;



	@Override
	public String toString() {
		return "PassBasicInfoEntity{" +
				"id='" + id + '\'' +
				", sNo=" + sNo +
				", personType='" + personType + '\'' +
				", names='" + names + '\'' +
				", sex='" + sex + '\'' +
				", phone='" + phone + '\'' +
				", cardType='" + cardType + '\'' +
				", cardNumber='" + cardNumber + '\'' +
				", birthday=" + birthday +
				", workAddress='" + workAddress + '\'' +
				", startTime=" + startTime +
				", endTime=" + endTime +
				", unit='" + unit + '\'' +
				", unitName='" + unitName + '\'' +
				", reason='" + reason + '\'' +
				", modes='" + modes + '\'' +
				", address='" + address + '\'' +
				", addressDetail='" + addressDetail + '\'' +
				", descs='" + descs + '\'' +
				", personState='" + personState + '\'' +
				", isInout='" + isInout + '\'' +
				", delayStartTime=" + delayStartTime +
				", delayToTime=" + delayToTime +
				", addTime=" + addTime +
				", addPersonId='" + addPersonId + '\'' +
				", addPersonName='" + addPersonName + '\'' +
				", idCard='" + idCard + '\'' +
				", isDelete='" + isDelete + '\'' +
				", nation='" + nation + '\'' +
				", yrUnit='" + yrUnit + '\'' +
				", yrName='" + yrName + '\'' +
				", yrPhone='" + yrPhone + '\'' +
				", unitPhone='" + unitPhone + '\'' +
				", emergencyName='" + emergencyName + '\'' +
				", emergencyPhone='" + emergencyPhone + '\'' +
				", auditName='" + auditName + '\'' +
				", getPassTime=" + getPassTime +
				", delayDesc='" + delayDesc + '\'' +
				", isGrantPass='" + isGrantPass + '\'' +
				", delayNumber=" + delayNumber +
				", isGrantFace='" + isGrantFace + '\'' +
				", grantFaceTime=" + grantFaceTime +
				", isPromise='" + isPromise + '\'' +
				", promiseTime=" + promiseTime +
				", doorPersonId='" + doorPersonId + '\'' +
				", doorFaceId='" + doorFaceId + '\'' +
				'}';
	}
}
