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
 * 发货申请单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_apply_deliver_goods")
@EqualsAndHashCode()
@ApiModel(value = "发货申请单")
public class FormApplyDeliverGoodsEntity implements Serializable {

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
	 * 客户名称
	 */
	@NotEmpty(message = "客户名称必须填写")
	@ApiModelProperty(value = "客户名称")
	private String customerName;
	/**
	 * 联系人
	 */
	@NotEmpty(message = "联系人必须填写")
	@ApiModelProperty(value = "联系人")
	private String contacts;
	/**
	 * 联系电话
	 */
	@NotEmpty(message = "联系电话必须填写")
	@ApiModelProperty(value = "联系电话")
	private String contactPhone;
	/**
	 * 客户地址
	 */
	@NotEmpty(message = "客户地址必须填写")
	@ApiModelProperty(value = "客户地址")
	private String customerAddress;
	/**
	 * 货品所属
	 */
	@NotEmpty(message = "货品所属必须填写")
	@ApiModelProperty(value = "货品所属")
	private String goodsBelonged;
	/**
	 * 发货日期
	 */
	@NotNull(message = "发货日期必须填写")
	@ApiModelProperty(value = "发货日期")
	private LocalDateTime invoiceDate;
	/**
	 * 货运公司
	 */
	@NotEmpty(message = "货运公司必须填写")
	@ApiModelProperty(value = "货运公司")
	private String freightCompany;
	/**
	 * 发货类型
	 */
	@NotEmpty(message = "发货类型必须填写")
	@ApiModelProperty(value = "发货类型")
	private String deliveryType;
	/**
	 * 货运单号
	 */
	@NotEmpty(message = "货运单号必须填写")
	@ApiModelProperty(value = "货运单号")
	private String transportNum;
	/**
	 * 货运费
	 */
	@NotNull(message = "货运费必须填写")
	@ApiModelProperty(value = "货运费")
	private BigDecimal freightCharges;
	/**
	 * 保险金额
	 */
	@NotNull(message = "保险金额必须填写")
	@ApiModelProperty(value = "保险金额")
	private BigDecimal cargoInsurance;
	/**
	 * 备注
	 */
	@NotEmpty(message = "备注必须填写")
	@ApiModelProperty(value = "备注")
	private String description;
	/**
	 * 发货金额
	 */
	@NotNull(message = "发货金额必须填写")
	@ApiModelProperty(value = "发货金额")
	private BigDecimal invoiceValue;
}
