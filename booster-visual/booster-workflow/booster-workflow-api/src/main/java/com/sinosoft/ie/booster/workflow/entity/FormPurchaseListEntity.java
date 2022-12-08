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
 * 日常物品采购清单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_purchase_list")
@EqualsAndHashCode()
@ApiModel(value = "日常物品采购清单")
public class FormPurchaseListEntity implements Serializable {

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
	 * 所在部门
	 */
	@NotEmpty(message = "所在部门必须填写")
	@ApiModelProperty(value = "所在部门")
	private String departmental;
	/**
	 * 供应商名称
	 */
	@NotEmpty(message = "供应商名称必须填写")
	@ApiModelProperty(value = "供应商名称")
	private String vendorName;
	/**
	 * 采购人员
	 */
	@NotEmpty(message = "采购人员必须填写")
	@ApiModelProperty(value = "采购人员")
	private String buyer;
	/**
	 * 采购日期
	 */
	@NotNull(message = "采购日期必须填写")
	@ApiModelProperty(value = "采购日期")
	private LocalDateTime purchaseDate;
	/**
	 * 仓库
	 */
	@NotEmpty(message = "仓库必须填写")
	@ApiModelProperty(value = "仓库")
	private String warehouse;
	/**
	 * 联系方式
	 */
	@NotEmpty(message = "联系方式必须填写")
	@ApiModelProperty(value = "联系方式")
	private String telephone;
	/**
	 * 支付方式
	 */
	@NotEmpty(message = "支付方式必须填写")
	@ApiModelProperty(value = "支付方式")
	private String paymentMethod;
	/**
	 * 支付总额
	 */
	@NotNull(message = "支付总额必须填写")
	@ApiModelProperty(value = "支付总额")
	private BigDecimal paymentMoney;
	/**
	 * 相关附件
	 */
	@NotEmpty(message = "相关附件必须填写")
	@ApiModelProperty(value = "相关附件")
	private String fileJson;
	/**
	 * 用途原因
	 */
	@NotEmpty(message = "用途原因必须填写")
	@ApiModelProperty(value = "用途原因")
	private String reason;
}
