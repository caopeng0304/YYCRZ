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
 * 付款申请单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_payment_apply")
@EqualsAndHashCode()
@ApiModel(value = "付款申请单")
public class FormPaymentApplyEntity implements Serializable {

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
	 * 申请人
	 */
	@NotEmpty(message = "申请人必须填写")
	@ApiModelProperty(value = "申请人")
	private String applyUser;
	/**
	 * 申请部门
	 */
	@NotEmpty(message = "申请部门必须填写")
	@ApiModelProperty(value = "申请部门")
	private String departmental;
	/**
	 * 用途名称
	 */
	@NotEmpty(message = "用途名称必须填写")
	@ApiModelProperty(value = "用途名称")
	private String purposeName;
	/**
	 * 项目类别
	 */
	@NotEmpty(message = "项目类别必须填写")
	@ApiModelProperty(value = "项目类别")
	private String projectCategory;
	/**
	 * 项目负责人
	 */
	@NotEmpty(message = "项目负责人必须填写")
	@ApiModelProperty(value = "项目负责人")
	private String projectLeader;
	/**
	 * 开户银行
	 */
	@NotEmpty(message = "开户银行必须填写")
	@ApiModelProperty(value = "开户银行")
	private String openingBank;
	/**
	 * 收款账号
	 */
	@NotEmpty(message = "收款账号必须填写")
	@ApiModelProperty(value = "收款账号")
	private String beneficiaryAccount;
	/**
	 * 联系方式
	 */
	@NotEmpty(message = "联系方式必须填写")
	@ApiModelProperty(value = "联系方式")
	private String receivableContact;
	/**
	 * 付款单位
	 */
	@NotEmpty(message = "付款单位必须填写")
	@ApiModelProperty(value = "付款单位")
	private String paymentUnit;
	/**
	 * 申请金额
	 */
	@NotNull(message = "申请金额必须填写")
	@ApiModelProperty(value = "申请金额")
	private BigDecimal applyAmount;
	/**
	 * 结算方式
	 */
	@NotEmpty(message = "结算方式必须填写")
	@ApiModelProperty(value = "结算方式")
	private String settlementMethod;
	/**
	 * 付款类型
	 */
	@NotEmpty(message = "付款类型必须填写")
	@ApiModelProperty(value = "付款类型")
	private String paymentType;
	/**
	 * 付款金额
	 */
	@NotNull(message = "付款金额必须填写")
	@ApiModelProperty(value = "付款金额")
	private BigDecimal amountPaid;
	/**
	 * 备注
	 */
	@NotEmpty(message = "备注必须填写")
	@ApiModelProperty(value = "备注")
	private String description;
	/**
	 * 申请日期
	 */
	@NotNull(message = "申请日期必须填写")
	@ApiModelProperty(value = "申请日期")
	private LocalDateTime applyDate;
}
