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
 * 订单收款
 *
 * @author booster code generator
 * @since 2021-09-28
 */
@Data
@TableName("form_order_receivable")
@EqualsAndHashCode()
@ApiModel(value = "订单收款")
public class FormOrderReceivableEntity implements Serializable {

	/**
	 * 自然主键
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "自然主键")
	private Long id;
	/**
	 * 订单主键
	 */
	@NotEmpty(message = "订单主键必须填写")
	@ApiModelProperty(value = "订单主键")
	private Long orderId;
	/**
	 * 收款摘要
	 */
	@NotEmpty(message = "收款摘要必须填写")
	@ApiModelProperty(value = "收款摘要")
	private String summary;
	/**
	 * 收款日期
	 */
	@NotNull(message = "收款日期必须填写")
	@ApiModelProperty(value = "收款日期")
	private LocalDateTime receivableDate;
	/**
	 * 收款比率
	 */
	@NotNull(message = "收款比率必须填写")
	@ApiModelProperty(value = "收款比率")
	private BigDecimal receivableRate;
	/**
	 * 收款金额
	 */
	@NotNull(message = "收款金额必须填写")
	@ApiModelProperty(value = "收款金额")
	private BigDecimal receivableMoney;
	/**
	 * 收款方式
	 */
	@NotEmpty(message = "收款方式必须填写")
	@ApiModelProperty(value = "收款方式")
	private String receivableMode;
	/**
	 * 收款状态
	 */
	@NotNull(message = "收款状态必须填写")
	@ApiModelProperty(value = "收款状态")
	private Integer receivableState;
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
}
