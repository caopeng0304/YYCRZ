package com.sinosoft.ie.booster.yypass.model.passbasicinfo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户excel 对应的实体
 *
 * @author lengleng
 * @since 2021/8/4
 */
@Data
@ColumnWidth(30)
@HeadRowHeight(20)
@ContentRowHeight(18)
public class DataTransferExcelVO implements Serializable {

	private static final long serialVersionUID = 1L;


	@ColumnWidth(10)
	@ExcelProperty("PERSON_TYPE")
	private String personType;

	@ColumnWidth(10)
	@ExcelProperty("APPLY_NAME")
	private String names;

	@ColumnWidth(10)
	@ExcelProperty("APPLY_SEX")
	private String sex;

	@ColumnWidth(10)
	@ExcelProperty("APPLY_PHONE")
	private String phone;

	@ColumnWidth(10)
	@ExcelProperty("APPLY_CERT_TYPE")
	private String cardType;

	@ColumnWidth(10)
	@ExcelProperty("APPLY_CERT_NO")
	private String cardNumber;

	//private Date birthday;
	//private String workAddress;

	@ColumnWidth(10)
	@ExcelProperty("START_DATE")
	private Date startTime;

	@ColumnWidth(10)
	@ExcelProperty("END_DATE")
	private Date endTime;

	//private String unit;
	//private String unitName;

	@ColumnWidth(10)
	@ExcelProperty("APPLY_REASON")
	private String reason;

	//private String modes;
	//private String address;
	//private String addressDetail;
	//private String descs;
	//private String personState;
	//private String isInout;

	@ColumnWidth(10)
	@ExcelProperty("CHECK_DATE")
	private Date delayStartTime;

	@ColumnWidth(10)
	@ExcelProperty("EXPIRATION_DATE")
	private Date delayToTime;

	@ColumnWidth(10)
	@ExcelProperty("APPLY_DATE")
	private Date addTime;

	//private String addPersonId;

	@ColumnWidth(10)
	@ExcelProperty("CHECK_NAME")
	private String addPersonName;

	@ColumnWidth(10)
	@ExcelProperty("APPLY_ID")
	private String idCard;    //

	@ColumnWidth(10)
	@ExcelProperty("APPLY_DEPT")
	private String unit;

	@ColumnWidth(10)
	@ExcelProperty("FACULTY_NAME")
	private String unitName;

	@ColumnWidth(10)
	@ExcelProperty("FACULTY_MOBILE")
	private String unitPhone;

	@ColumnWidth(10)
	@ExcelProperty("CHECK_NAME")
	private String auditName;



	@ColumnWidth(10)
	@ExcelProperty("APPLY_BATCH")
	private String applyBatch;

	@ColumnWidth(10)
	@ExcelProperty("WORK_PLACE")
	private String workAddress;

	@ColumnWidth(10)
	@ExcelProperty("RESIDENCE_ADDRESS")
	private String address;

	@ColumnWidth(10)
	@ExcelProperty("NATIONALITY")
	private String nation;

	@ColumnWidth(10)
	@ExcelProperty("LINKMAN_MOBILE")
	private String emergencyPhone;

	@ColumnWidth(10)
	@ExcelProperty("LINKMAN")
	private String emergencyName;

	@ColumnWidth(10)
	@ExcelProperty("REPLACE_REMARK")
	private String descs;

}
