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

/**
 * 订单明细
 *
 * @author booster code generator
 * @since 2021-09-28
 */
@Data
@TableName("form_order_entry")
@EqualsAndHashCode()
@ApiModel(value = "订单明细")
public class FormOrderEntryEntity implements Serializable {

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
	 * 商品Id
	 */
	@NotEmpty(message = "商品Id必须填写")
	@ApiModelProperty(value = "商品Id")
	private String goodsId;
	/**
	 * 商品编码
	 */
	@NotEmpty(message = "商品编码必须填写")
	@ApiModelProperty(value = "商品编码")
	private String goodsCode;
	/**
	 * 商品名称
	 */
	@NotEmpty(message = "商品名称必须填写")
	@ApiModelProperty(value = "商品名称")
	private String goodsName;
	/**
	 * 规格型号
	 */
	@NotEmpty(message = "规格型号必须填写")
	@ApiModelProperty(value = "规格型号")
	private String specifications;
	/**
	 * 单位
	 */
	@NotEmpty(message = "单位必须填写")
	@ApiModelProperty(value = "单位")
	private String unit;
	/**
	 * 数量
	 */
	@NotNull(message = "数量必须填写")
	@ApiModelProperty(value = "数量")
	private BigDecimal qty;
	/**
	 * 单价
	 */
	@NotNull(message = "单价必须填写")
	@ApiModelProperty(value = "单价")
	private BigDecimal price;
	/**
	 * 金额
	 */
	@NotNull(message = "金额必须填写")
	@ApiModelProperty(value = "金额")
	private BigDecimal amount;
	/**
	 * 折扣%
	 */
	@NotNull(message = "折扣%必须填写")
	@ApiModelProperty(value = "折扣%")
	private BigDecimal discount;
	/**
	 * 税率%
	 */
	@NotNull(message = "税率%必须填写")
	@ApiModelProperty(value = "税率%")
	private BigDecimal cess;
	/**
	 * 实际单价
	 */
	@NotNull(message = "实际单价必须填写")
	@ApiModelProperty(value = "实际单价")
	private BigDecimal actualPrice;
	/**
	 * 实际金额
	 */
	@NotNull(message = "实际金额必须填写")
	@ApiModelProperty(value = "实际金额")
	private BigDecimal actualAmount;
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
