package com.sinosoft.ie.booster.workflow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sinosoft.ie.booster.common.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单信息
 *
 * @author booster code generator
 * @since 2021-09-28
 */
@Data
@TableName("form_order")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "订单信息")
public class FormOrderEntity extends BaseEntity {

	/**
	 * 自然主键
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "自然主键")
	private Long id;
	/**
	 * 客户Id
	 */
	@NotEmpty(message = "客户Id必须填写")
	@ApiModelProperty(value = "客户Id")
	private String customerId;
	/**
	 * 客户名称
	 */
	@NotEmpty(message = "客户名称必须填写")
	@ApiModelProperty(value = "客户名称")
	private String customerName;
	/**
	 * 业务员Id
	 */
	@NotEmpty(message = "业务员Id必须填写")
	@ApiModelProperty(value = "业务员Id")
	private String salesManId;
	/**
	 * 业务员
	 */
	@NotEmpty(message = "业务员必须填写")
	@ApiModelProperty(value = "业务员")
	private String salesManName;
	/**
	 * 订单日期
	 */
	@NotNull(message = "订单日期必须填写")
	@ApiModelProperty(value = "订单日期")
	private LocalDateTime orderDate;
	/**
	 * 订单编号
	 */
	@NotEmpty(message = "订单编号必须填写")
	@ApiModelProperty(value = "订单编号")
	private String orderCode;
	/**
	 * 运输方式
	 */
	@NotEmpty(message = "运输方式必须填写")
	@ApiModelProperty(value = "运输方式")
	private String transportMode;
	/**
	 * 发货日期
	 */
	@NotNull(message = "发货日期必须填写")
	@ApiModelProperty(value = "发货日期")
	private LocalDateTime deliveryDate;
	/**
	 * 发货地址
	 */
	@NotEmpty(message = "发货地址必须填写")
	@ApiModelProperty(value = "发货地址")
	private String deliveryAddress;
	/**
	 * 付款方式
	 */
	@NotEmpty(message = "付款方式必须填写")
	@ApiModelProperty(value = "付款方式")
	private String paymentMode;
	/**
	 * 应收金额
	 */
	@NotNull(message = "应收金额必须填写")
	@ApiModelProperty(value = "应收金额")
	private BigDecimal receivableMoney;
	/**
	 * 定金比率
	 */
	@NotNull(message = "定金比率必须填写")
	@ApiModelProperty(value = "定金比率")
	private BigDecimal earnestRate;
	/**
	 * 预付定金
	 */
	@NotNull(message = "预付定金必须填写")
	@ApiModelProperty(value = "预付定金")
	private BigDecimal prepayEarnest;
	/**
	 * 当前状态
	 */
	@NotNull(message = "当前状态必须填写")
	@ApiModelProperty(value = "当前状态")
	private Integer currentState;
	/**
	 * 附件信息
	 */
	@NotEmpty(message = "附件信息必须填写")
	@ApiModelProperty(value = "附件信息")
	private String fileJson;
	/**
	 * 描述
	 */
	@NotEmpty(message = "描述必须填写")
	@ApiModelProperty(value = "描述")
	private String description;
	/**
	 * 排序
	 */
	@NotNull(message = "排序必须填写")
	@ApiModelProperty(value = "排序")
	private Integer sort;
	/**
	 * 有效标志
	 */
	@NotEmpty(message = "有效标志必须填写")
	@ApiModelProperty(value = "有效标志")
	private String enabledFlag;
	/**
	 * 删除标志
	 */
	@NotEmpty(message = "删除标志必须填写")
	@ApiModelProperty(value = "删除标志")
	@TableLogic
	private String delFlag;
}
