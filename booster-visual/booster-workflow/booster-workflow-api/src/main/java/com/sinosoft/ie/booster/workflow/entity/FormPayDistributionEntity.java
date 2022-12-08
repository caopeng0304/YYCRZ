package com.sinosoft.ie.booster.workflow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 薪酬发放
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_pay_distribution")
@EqualsAndHashCode()
@ApiModel(value = "薪酬发放")
public class FormPayDistributionEntity implements Serializable {

	/**
	 * 自然主键
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "自然主键")
	private Long id;
	/**
	 * 流程主键
	 */
	@NotEmpty(message = "流程主键必须填写")
	@ApiModelProperty(value = "流程主键")
	private Long flowId;
	/**
	 * 流程标题
	 */
	@NotEmpty(message = "流程标题必须填写")
	@ApiModelProperty(value = "流程标题")
	private String flowTitle;
	/**
	 * 紧急程度
	 */
	@NotNull(message = "紧急程度必须填写")
	@ApiModelProperty(value = "紧急程度")
	private Integer flowUrgent;
	/**
	 * 流程单据
	 */
	@NotEmpty(message = "流程单据必须填写")
	@ApiModelProperty(value = "流程单据")
	private String billNo;
	/**
	 * 所属月份
	 */
	@NotEmpty(message = "所属月份必须填写")
	@ApiModelProperty(value = "所属月份")
	private String month;
	/**
	 * 发放单位
	 */
	@NotEmpty(message = "发放单位必须填写")
	@ApiModelProperty(value = "发放单位")
	private String issuingUnit;
	/**
	 * 员工部门
	 */
	@NotEmpty(message = "员工部门必须填写")
	@ApiModelProperty(value = "员工部门")
	private String department;
	/**
	 * 员工职位
	 */
	@NotEmpty(message = "员工职位必须填写")
	@ApiModelProperty(value = "员工职位")
	private String position;
	/**
	 * 基本薪资
	 */
	@NotNull(message = "基本薪资必须填写")
	@ApiModelProperty(value = "基本薪资")
	private BigDecimal baseSalary;
	/**
	 * 出勤天数
	 */
	@NotEmpty(message = "出勤天数必须填写")
	@ApiModelProperty(value = "出勤天数")
	private String actualAttendance;
	/**
	 * 员工津贴
	 */
	@NotNull(message = "员工津贴必须填写")
	@ApiModelProperty(value = "员工津贴")
	private BigDecimal allowance;
	/**
	 * 所得税
	 */
	@NotNull(message = "所得税必须填写")
	@ApiModelProperty(value = "所得税")
	private BigDecimal incomeTax;
	/**
	 * 员工保险
	 */
	@NotNull(message = "员工保险必须填写")
	@ApiModelProperty(value = "员工保险")
	private BigDecimal insurance;
	/**
	 * 员工绩效
	 */
	@NotNull(message = "员工绩效必须填写")
	@ApiModelProperty(value = "员工绩效")
	private BigDecimal performance;
	/**
	 * 加班费用
	 */
	@NotNull(message = "加班费用必须填写")
	@ApiModelProperty(value = "加班费用")
	private BigDecimal overtimePay;
	/**
	 * 应发工资
	 */
	@NotNull(message = "应发工资必须填写")
	@ApiModelProperty(value = "应发工资")
	private BigDecimal grossPay;
	/**
	 * 实发工资
	 */
	@NotNull(message = "实发工资必须填写")
	@ApiModelProperty(value = "实发工资")
	private BigDecimal payroll;
	/**
	 * 备注
	 */
	@NotEmpty(message = "备注必须填写")
	@ApiModelProperty(value = "备注")
	private String description;
}
