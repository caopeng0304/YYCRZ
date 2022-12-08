package com.sinosoft.ie.booster.workflow.model.formfinishedproduct;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 成品入库单
 *
 * @author booster开发平台组
 * @since 2021/3/15 8:46
 */
@Data
public class FinishedProductEntryEntityInfoModel {
	@ApiModelProperty(value = "主键")
	private String id;
	@ApiModelProperty(value = "入库单号")
	private String warehouseId;
	@ApiModelProperty(value = "商品名称")
	private String goodsName;
	@ApiModelProperty(value = "规格型号")
	private String specifications;
	@ApiModelProperty(value = "单位")
	private String unit;
	@ApiModelProperty(value = "数量")
	private String qty;
	@ApiModelProperty(value = "单价")
	private BigDecimal price;
	@ApiModelProperty(value = "金额")
	private BigDecimal amount;
	@ApiModelProperty(value = "备注")
	private String description;
	@ApiModelProperty(value = "排序号")
	private Long sort;
}
