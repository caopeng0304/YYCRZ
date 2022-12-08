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
 * 费用支出单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_expense_expenditure")
@EqualsAndHashCode()
@ApiModel(value = "费用支出单")
public class FormExpenseExpenditureEntity implements Serializable {

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
	 * 流程等级
	 */
	@NotNull(message = "流程等级必须填写")
	@ApiModelProperty(value = "流程等级")
	private Integer flowUrgent;
	/**
	 * 流程单据
	 */
	@NotEmpty(message = "流程单据必须填写")
	@ApiModelProperty(value = "流程单据")
	private String billNo;
	/**
	 * 申请人员
	 */
	@NotEmpty(message = "申请人员必须填写")
	@ApiModelProperty(value = "申请人员")
	private String applyUser;
	/**
	 * 所在部门
	 */
	@NotEmpty(message = "所在部门必须填写")
	@ApiModelProperty(value = "所在部门")
	private String department;
	/**
	 * 申请日期
	 */
	@NotNull(message = "申请日期必须填写")
	@ApiModelProperty(value = "申请日期")
	private LocalDateTime applyDate;
	/**
	 * 合同编号
	 */
	@NotEmpty(message = "合同编号必须填写")
	@ApiModelProperty(value = "合同编号")
	private String contractNum;
	/**
	 * 非合同支出
	 */
	@NotEmpty(message = "非合同支出必须填写")
	@ApiModelProperty(value = "非合同支出")
	private String nonContract;
	/**
	 * 开户银行
	 */
	@NotEmpty(message = "开户银行必须填写")
	@ApiModelProperty(value = "开户银行")
	private String accountOpeningBank;
	/**
	 * 银行账号
	 */
	@NotEmpty(message = "银行账号必须填写")
	@ApiModelProperty(value = "银行账号")
	private String bankAccount;
	/**
	 * 开户姓名
	 */
	@NotEmpty(message = "开户姓名必须填写")
	@ApiModelProperty(value = "开户姓名")
	private String openAccount;
	/**
	 * 合计费用
	 */
	@NotNull(message = "合计费用必须填写")
	@ApiModelProperty(value = "合计费用")
	private BigDecimal total;
	/**
	 * 支付方式
	 */
	@NotEmpty(message = "支付方式必须填写")
	@ApiModelProperty(value = "支付方式")
	private String paymentMethod;
	/**
	 * 支付金额
	 */
	@NotNull(message = "支付金额必须填写")
	@ApiModelProperty(value = "支付金额")
	private BigDecimal amountPayment;
	/**
	 * 备注
	 */
	@NotEmpty(message = "备注必须填写")
	@ApiModelProperty(value = "备注")
	private String description;
}
