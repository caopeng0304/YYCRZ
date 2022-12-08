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
 * 合同开票流程
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_con_billing")
@EqualsAndHashCode()
@ApiModel(value = "合同开票流程")
public class FormConBillingEntity implements Serializable {

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
	 * 开票人
	 */
	@NotEmpty(message = "开票人必须填写")
	@ApiModelProperty(value = "开票人")
	private String drawer;
	/**
	 * 开票日期
	 */
	@NotNull(message = "开票日期必须填写")
	@ApiModelProperty(value = "开票日期")
	private LocalDateTime billDate;
	/**
	 * 公司名称
	 */
	@NotEmpty(message = "公司名称必须填写")
	@ApiModelProperty(value = "公司名称")
	private String companyName;
	/**
	 * 关联名称
	 */
	@NotEmpty(message = "关联名称必须填写")
	@ApiModelProperty(value = "关联名称")
	private String conName;
	/**
	 * 开户银行
	 */
	@NotEmpty(message = "开户银行必须填写")
	@ApiModelProperty(value = "开户银行")
	private String bank;
	/**
	 * 开户账号
	 */
	@NotEmpty(message = "开户账号必须填写")
	@ApiModelProperty(value = "开户账号")
	private String amount;
	/**
	 * 开票金额
	 */
	@NotNull(message = "开票金额必须填写")
	@ApiModelProperty(value = "开票金额")
	private BigDecimal billAmount;
	/**
	 * 税号
	 */
	@NotEmpty(message = "税号必须填写")
	@ApiModelProperty(value = "税号")
	private String taxId;
	/**
	 * 发票号
	 */
	@NotEmpty(message = "发票号必须填写")
	@ApiModelProperty(value = "发票号")
	private String invoiceId;
	/**
	 * 发票地址
	 */
	@NotEmpty(message = "发票地址必须填写")
	@ApiModelProperty(value = "发票地址")
	private String invoAddress;
	/**
	 * 付款金额
	 */
	@NotNull(message = "付款金额必须填写")
	@ApiModelProperty(value = "付款金额")
	private BigDecimal payAmount;
	/**
	 * 相关附件
	 */
	@NotEmpty(message = "相关附件必须填写")
	@ApiModelProperty(value = "相关附件")
	private String fileJson;
	/**
	 * 备注
	 */
	@NotEmpty(message = "备注必须填写")
	@ApiModelProperty(value = "备注")
	private String description;
}
