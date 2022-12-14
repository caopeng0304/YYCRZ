package com.sinosoft.ie.booster.workflow.model.formorder;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单信息
 *
 * @author booster开发平台组
 * @since 2021/3/15 8:46
 */
@Data
public class OrderExportModel {
	private String customerId;
	private String customerName;
	private String salesmanId;
	private String salesmanName;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date orderDate;
	private String orderCode;
	private String transportMode;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date deliveryDate;
	private String deliveryAddress;
	private String paymentMode;
	private BigDecimal receivableMoney;
	private BigDecimal earnestRate;
	private BigDecimal prepayEarnest;
	private String description;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	private String createBy;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	private String updateBy;

}
