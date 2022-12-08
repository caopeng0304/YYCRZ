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
public class OrderListVO {
	@ApiModelProperty(value = "订单日期")
	private Long orderDate;
	@ApiModelProperty(value = "订单编号")
	private String orderCode;
	@ApiModelProperty(value = "客户名称")
	private String customerName;
	@ApiModelProperty(value = "业务员")
	private String salesmanName;
	@ApiModelProperty(value = "描述")
	private String description;
	@ApiModelProperty(value = "付款金额")
	private String receivableMoney;
	@ApiModelProperty(value = "制单人员")
	private String createBy;
	@ApiModelProperty(value = "主键id")
	private String id;
	@ApiModelProperty(value = "当前状态")
	private Integer currentState;
}
