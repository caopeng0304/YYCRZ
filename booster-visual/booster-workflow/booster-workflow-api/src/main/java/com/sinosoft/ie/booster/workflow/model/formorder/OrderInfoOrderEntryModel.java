package com.sinosoft.ie.booster.workflow.model.formorder;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 订单信息
 *
 * @author booster开发平台组
 * @since 2021/3/15 8:46
 */
@Data
public class OrderInfoOrderEntryModel {
	@ApiModelProperty(value = "订单日期")
	private String remove;
	@ApiModelProperty(value = "自然主键")
	private String id;
	@ApiModelProperty(value = "商品Id")
	private String goodsId;
	@ApiModelProperty(value = "商品编码")
	private String goodsCode;
	@ApiModelProperty(value = "商品名称")
	private String goodsName;
	@ApiModelProperty(value = "规格型号")
	private String specifications;
	@ApiModelProperty(value = "单位")
	private String unit;
	@ApiModelProperty(value = "数量")
	private String qty;
	@ApiModelProperty(value = "单价")
	private String price;
	@ApiModelProperty(value = "金额", example = "1")
	private int amount;
	@ApiModelProperty(value = " 折扣%", example = "1")
	private int discount;
	@ApiModelProperty(value = " 税率%")
	private String cess;
	@ApiModelProperty(value = "实际单价")
	private String actualPrice;
	@ApiModelProperty(value = "实际金额")
	private String actualAmount;
	@ApiModelProperty(value = "描述")
	private String description;
	@ApiModelProperty(value = "排序", example = "1")
	private int sort;

}
