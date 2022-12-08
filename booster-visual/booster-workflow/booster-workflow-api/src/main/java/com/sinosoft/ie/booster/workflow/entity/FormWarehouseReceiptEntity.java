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
import java.time.LocalDateTime;

/**
 * 入库申请单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_warehouse_receipt")
@EqualsAndHashCode()
@ApiModel(value = "入库申请单")
public class FormWarehouseReceiptEntity implements Serializable {

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
	 * 供应商名称
	 */
	@NotEmpty(message = "供应商名称必须填写")
	@ApiModelProperty(value = "供应商名称")
	private String supplierName;
	/**
	 * 联系电话
	 */
	@NotEmpty(message = "联系电话必须填写")
	@ApiModelProperty(value = "联系电话")
	private String contactPhone;
	/**
	 * 入库类别
	 */
	@NotEmpty(message = "入库类别必须填写")
	@ApiModelProperty(value = "入库类别")
	private String warehouseCategory;
	/**
	 * 仓库
	 */
	@NotEmpty(message = "仓库必须填写")
	@ApiModelProperty(value = "仓库")
	private String warehouse;
	/**
	 * 入库人
	 */
	@NotEmpty(message = "入库人必须填写")
	@ApiModelProperty(value = "入库人")
	private String warehousePeople;
	/**
	 * 送货单号
	 */
	@NotEmpty(message = "送货单号必须填写")
	@ApiModelProperty(value = "送货单号")
	private String deliveryNo;
	/**
	 * 入库单号
	 */
	@NotEmpty(message = "入库单号必须填写")
	@ApiModelProperty(value = "入库单号")
	private String warehouseNo;
	/**
	 * 入库日期
	 */
	@NotNull(message = "入库日期必须填写")
	@ApiModelProperty(value = "入库日期")
	private LocalDateTime warehouseDate;
}
