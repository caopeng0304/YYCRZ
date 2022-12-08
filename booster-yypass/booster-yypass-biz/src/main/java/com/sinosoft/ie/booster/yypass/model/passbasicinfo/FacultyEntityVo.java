package com.sinosoft.ie.booster.yypass.model.passbasicinfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class FacultyEntityVo {

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键")
	private Long id;
	/**
	 * 校园卡号
	 */
	@ApiModelProperty(value = "校园卡号")
	private String facultyId;

	/**
	 * 姓名
	 */
	@ApiModelProperty(value = "姓名")
	private String facultyName;

	/**
	 * 组织机构id
	 */
	@ApiModelProperty(value = "组织机构id")
	private String facultyDept;

	/**
	 * 组织机构名称
	 */
	@ApiModelProperty(value = "组织机构名称")
	private String facultyDeptName;


	/**
	 * 园区
	 */
	@ApiModelProperty(value = "园区")
	private String campus;

	/**
	 * 状态
	 */
	@ApiModelProperty(value = "状态")
	private String checkStayus;

	/**
	 * 状态
	 */
	@ApiModelProperty(value = "车牌号")
	private String carNo;

	/**
	 * 权限
	 */
	@ApiModelProperty(value = "权限")
	private String entryTime;

	/**
	 * 学生类别
	 */
	@ApiModelProperty(value = "学生类别")
	private String xslb;

	/**
	 * 原因
	 */
	@ApiModelProperty(value = "原因")
	private String reason;

	/**
	 * 创建人名称
	 */
	@ApiModelProperty(value = "创建人单位名称")
	private String appdeptname;

	/**
	 * 创建人名称
	 */
	@ApiModelProperty(value = "创建人名称")
	private String apprealname;

	/**
	 * 创建人单位编码
	 */
	@ApiModelProperty(value = "创建人单位编码")
	private String appdeptcode;

	/**
	 * 创建人联系电话
	 */
	@ApiModelProperty(value = "创建人联系电话")
	private String apprealinfo;


	/**
	 * 加入黑名单状态
	 */
	@ApiModelProperty(value = "加入黑名单状态")
	private String blackStatas;


	/**
	 * 加入白名单状态
	 */
	@ApiModelProperty(value = "加入白名单状态")
	private String withStatas;

	/**
	 * 所在分组
	 */
	@ApiModelProperty(value = "所在分组")
	private String groupName;

	/**
	 * 人脸状态
	 */
	@ApiModelProperty(value = "人脸状态")
	private String faceType;

	/**
	 * 照片
	 */
	@ApiModelProperty(value = "人脸照片")
	private String facePhoto;

	/**
	 * 海康人脸照片
	 */
	@ApiModelProperty(value = "人脸照片")
	private String hkFacePhoto;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "白名单主键")
	private Long withId;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "黑名单主键")
	private Long blackId;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "预约校门")
	private String yyxm;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "预约时间")
	private String yysj;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "访客身份证")
	private String byyrzjh;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "预约日期")
	private String yyrq;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "访客手机号")
	private String byyrlxdh;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "访客姓名")
	private String byyrxm;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "预约事由")
	private String yysy;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "院系")
	private String xsmc;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "跟随人数")
	private String follow;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "跟随人员")
	private String followNames;

}
