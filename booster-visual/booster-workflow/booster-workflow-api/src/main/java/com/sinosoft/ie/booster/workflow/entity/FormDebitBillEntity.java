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
import java.time.LocalDateTime;

/**
 * 借支单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_debit_bill")
@EqualsAndHashCode()
@ApiModel(value = "借支单")
public class FormDebitBillEntity implements Serializable {

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
	 * 工作部门
	 */
	@NotEmpty(message = "工作部门必须填写")
	@ApiModelProperty(value = "工作部门")
	private String departmental;
	/**
	 * 申请日期
	 */
	@NotNull(message = "申请日期必须填写")
	@ApiModelProperty(value = "申请日期")
	private LocalDateTime applyDate;
	/**
	 * 员工姓名
	 */
	@NotEmpty(message = "员工姓名必须填写")
	@ApiModelProperty(value = "员工姓名")
	private String staffName;
	/**
	 * 员工职务
	 */
	@NotEmpty(message = "员工职务必须填写")
	@ApiModelProperty(value = "员工职务")
	private String staffPost;
	/**
	 * 员工编号
	 */
	@NotEmpty(message = "员工编号必须填写")
	@ApiModelProperty(value = "员工编号")
	private String staffId;
	/**
	 * 借款方式
	 */
	@NotEmpty(message = "借款方式必须填写")
	@ApiModelProperty(value = "借款方式")
	private String loanMode;
	/**
	 * 借支金额
	 */
	@NotNull(message = "借支金额必须填写")
	@ApiModelProperty(value = "借支金额")
	private BigDecimal amountDebit;
	/**
	 * 转账账户
	 */
	@NotEmpty(message = "转账账户必须填写")
	@ApiModelProperty(value = "转账账户")
	private String transferAccount;
	/**
	 * 还款票据
	 */
	@NotEmpty(message = "还款票据必须填写")
	@ApiModelProperty(value = "还款票据")
	private String repaymentBill;
	/**
	 * 还款日期
	 */
	@NotNull(message = "还款日期必须填写")
	@ApiModelProperty(value = "还款日期")
	private LocalDateTime teachingDate;
	/**
	 * 支付方式
	 */
	@NotEmpty(message = "支付方式必须填写")
	@ApiModelProperty(value = "支付方式")
	private String paymentMethod;
	/**
	 * 借款原因
	 */
	@NotEmpty(message = "借款原因必须填写")
	@ApiModelProperty(value = "借款原因")
	private String reason;
}
