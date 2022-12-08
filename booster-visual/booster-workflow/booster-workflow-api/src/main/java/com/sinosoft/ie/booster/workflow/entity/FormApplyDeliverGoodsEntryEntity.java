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
 * 发货明细
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_apply_deliver_goods_entry")
@EqualsAndHashCode()
@ApiModel(value = "发货明细")
public class FormApplyDeliverGoodsEntryEntity implements Serializable {

	/**
	 * 自然主键
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "自然主键")
	private Long id;
	/**
	 * 发货主键
	 */
	@NotEmpty(message = "发货主键必须填写")
	@ApiModelProperty(value = "发货主键")
	private Long invoiceId;
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
	@NotEmpty(message = "数量必须填写")
	@ApiModelProperty(value = "数量")
	private String qty;
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
	 * 描述
	 */
	@NotEmpty(message = "描述必须填写")
	@ApiModelProperty(value = "描述")
	private String description;
	/**
	 * 排序码
	 */
	@NotNull(message = "排序码必须填写")
	@ApiModelProperty(value = "排序码")
	private Integer sort;
}
