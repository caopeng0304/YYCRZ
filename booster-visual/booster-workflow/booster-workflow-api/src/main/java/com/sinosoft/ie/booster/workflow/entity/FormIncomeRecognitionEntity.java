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
 * 收入确认分析表
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_income_recognition")
@EqualsAndHashCode()
@ApiModel(value = "收入确认分析表")
public class FormIncomeRecognitionEntity implements Serializable {

	/**
	 * 自然主键
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "自然主键")
	private Long id;
	/**
	 * 流程主题
	 */
	@NotEmpty(message = "流程主题必须填写")
	@ApiModelProperty(value = "流程主题")
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
	 * 结算月份
	 */
	@NotEmpty(message = "结算月份必须填写")
	@ApiModelProperty(value = "结算月份")
	private String settlementMonth;
	/**
	 * 客户名称
	 */
	@NotEmpty(message = "客户名称必须填写")
	@ApiModelProperty(value = "客户名称")
	private String customerName;
	/**
	 * 合同编号
	 */
	@NotEmpty(message = "合同编号必须填写")
	@ApiModelProperty(value = "合同编号")
	private String contractNum;
	/**
	 * 合同金额
	 */
	@NotNull(message = "合同金额必须填写")
	@ApiModelProperty(value = "合同金额")
	private BigDecimal totalAmount;
	/**
	 * 到款银行
	 */
	@NotEmpty(message = "到款银行必须填写")
	@ApiModelProperty(value = "到款银行")
	private String moneyBank;
	/**
	 * 到款金额
	 */
	@NotNull(message = "到款金额必须填写")
	@ApiModelProperty(value = "到款金额")
	private BigDecimal actualAmount;
	/**
	 * 联系人姓名
	 */
	@NotEmpty(message = "联系人姓名必须填写")
	@ApiModelProperty(value = "联系人姓名")
	private String contactName;
	/**
	 * 联系电话
	 */
	@NotEmpty(message = "联系电话必须填写")
	@ApiModelProperty(value = "联系电话")
	private String contactPhone;
	/**
	 * 联系QQ
	 */
	@NotEmpty(message = "联系QQ必须填写")
	@ApiModelProperty(value = "联系QQ")
	private String contactQq;
	/**
	 * 未付金额
	 */
	@NotNull(message = "未付金额必须填写")
	@ApiModelProperty(value = "未付金额")
	private BigDecimal unpaidAmount;
	/**
	 * 已付金额
	 */
	@NotNull(message = "已付金额必须填写")
	@ApiModelProperty(value = "已付金额")
	private BigDecimal amountPaid;
	/**
	 * 备注
	 */
	@NotEmpty(message = "备注必须填写")
	@ApiModelProperty(value = "备注")
	private String description;
	/**
	 * 到款日期
	 */
	@NotNull(message = "到款日期必须填写")
	@ApiModelProperty(value = "到款日期")
	private LocalDateTime paymentDate;
}
