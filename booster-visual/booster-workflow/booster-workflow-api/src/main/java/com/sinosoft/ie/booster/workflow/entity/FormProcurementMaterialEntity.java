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
 * 采购原材料
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_procurement_material")
@EqualsAndHashCode()
@ApiModel(value = "采购原材料")
public class FormProcurementMaterialEntity implements Serializable {

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
	 * 申请日期
	 */
	@NotNull(message = "申请日期必须填写")
	@ApiModelProperty(value = "申请日期")
	private LocalDateTime applyDate;
	/**
	 * 采购单位
	 */
	@NotEmpty(message = "采购单位必须填写")
	@ApiModelProperty(value = "采购单位")
	private String purchaseUnit;
	/**
	 * 送货方式
	 */
	@NotEmpty(message = "送货方式必须填写")
	@ApiModelProperty(value = "送货方式")
	private String deliveryMode;
	/**
	 * 送货地址
	 */
	@NotEmpty(message = "送货地址必须填写")
	@ApiModelProperty(value = "送货地址")
	private String deliveryAddress;
	/**
	 * 付款方式
	 */
	@NotEmpty(message = "付款方式必须填写")
	@ApiModelProperty(value = "付款方式")
	private String paymentMethod;
	/**
	 * 付款金额
	 */
	@NotNull(message = "付款金额必须填写")
	@ApiModelProperty(value = "付款金额")
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
