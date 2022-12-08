package com.sinosoft.ie.booster.yypass.model.passbasicinfo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.poi.sl.image.ImageHeaderBitmap;
import org.springframework.format.annotation.DateTimeFormat;

import javax.sql.rowset.serial.SerialBlob;
import java.io.InputStream;
import java.io.Serializable;
import java.io.InputStream;
import java.time.LocalDateTime;
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
public class PassBasicInfoExcelVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	@ColumnWidth(10)
	@ExcelProperty("人员身份")
	private String personType;

	@ColumnWidth(10)
	@ExcelProperty("姓名")
	private String names;

	@ColumnWidth(5)
	@ExcelProperty("性别")
	private String sex;


	@ColumnWidth(15)
	@ExcelProperty("电话")
	private String phone;

	@ColumnWidth(5)
	@ExcelProperty("证件类型")
	private String cardType;

	@ColumnWidth(15)
	@ExcelProperty("证件号")
	private String cardNumber;

	@ColumnWidth(15)
	@ExcelProperty("出生日期")
	private Date birthday;

	@ColumnWidth(15)
	@ExcelProperty("工作地点")
	private String workAddress;

	@ColumnWidth(15)
	@ExcelProperty("开始日期")
	private Date startTime;

	@ColumnWidth(15)
	@ExcelProperty("结束日期")
	private Date endTime;

	@ColumnWidth(15)
	@ExcelProperty("承办单位")
	private String unit;

	@ColumnWidth(10)
	@ExcelProperty("承办人")
	private String unitName;

	@ColumnWidth(15)
	@ExcelProperty("事由")
	private String reason;

	@ColumnWidth(5)
	@ExcelProperty("出入校模式")
	private String modes;

	@ColumnWidth(15)
	@ExcelProperty("居住地点")
	private String address;

	@ColumnWidth(15)
	@ExcelProperty("详细地址")
	private String addressDetail;

	@ColumnWidth(15)
	@ExcelProperty("备注")
	private String descs;

	@ColumnWidth(5)
	@ExcelProperty("状态")
	private String personState;

	@ColumnWidth(5)
	@ExcelProperty("出入校权限")
	private String isInout;

	@ColumnWidth(15)
	@ExcelProperty("延期时间")
	private Date delayToTime;

	@ColumnWidth(5)
	@ExcelProperty("民族")
	private String nation;

	@ColumnWidth(15)
	@ExcelProperty("用人单位")
	private String yrUnit;

	@ColumnWidth(10)
	@ExcelProperty("用人单位负责人")
	private String yrName;

	@ColumnWidth(15)
	@ExcelProperty("用人单位负责人电话")
	private String yrPhone;

	@ColumnWidth(15)
	@ExcelProperty("承办人电话")
	private String unitPhone;

	@ColumnWidth(10)
	@ExcelProperty("紧急联系人")
	private String emergencyName;

	@ColumnWidth(15)
	@ExcelProperty("紧急联系人电话")
	private String emergencyPhone;

	@ColumnWidth(10)
	@ExcelProperty("审核人")
	private String auditName;

	@ColumnWidth(10)
	@ExcelProperty("发证日期")
	private Date getPassTime;

	@ColumnWidth(20)
	@ExcelProperty("图片")
	public String file;

	private String isGrantFace;

	private String idCard;

}
