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
 * 销售订单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_sales_order")
@EqualsAndHashCode()
@ApiModel(value = "销售订单")
public class FormSalesOrderEntity implements Serializable {

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
	 * 流程等级
	 */
	@NotNull(message = "流程等级必须填写")
	@ApiModelProperty(value = "流程等级")
	private Integer flowUrgent;
	/**
	 * 流程单据
	 */
	@NotEmpty(message = "流程单据必须填写")
	@ApiModelProperty(value = "流程单据")
	private String billNo;
	/**
	 * 业务人员
	 */
	@NotEmpty(message = "业务人员必须填写")
	@ApiModelProperty(value = "业务人员")
	private String salesMan;
	/**
	 * 客户名称
	 */
	@NotEmpty(message = "客户名称必须填写")
	@ApiModelProperty(value = "客户名称")
	private String customerName;
	/**
	 * 联系人
	 */
	@NotEmpty(message = "联系人必须填写")
	@ApiModelProperty(value = "联系人")
	private String contacts;
	/**
	 * 联系电话
	 */
	@NotEmpty(message = "联系电话必须填写")
	@ApiModelProperty(value = "联系电话")
	private String contactPhone;
	/**
	 * 客户地址
	 */
	@NotEmpty(message = "客户地址必须填写")
	@ApiModelProperty(value = "客户地址")
	private String customerAddress;
	/**
	 * 发票编号
	 */
	@NotEmpty(message = "发票编号必须填写")
	@ApiModelProperty(value = "发票编号")
	private String ticketNum;
	/**
	 * 发票类型
	 */
	@NotEmpty(message = "发票类型必须填写")
	@ApiModelProperty(value = "发票类型")
	private String invoiceType;
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
	 * 销售日期
	 */
	@NotNull(message = "销售日期必须填写")
	@ApiModelProperty(value = "销售日期")
	private LocalDateTime salesDate;
	/**
	 * 相关附件
	 */
	@NotEmpty(message = "相关附件必须填写")
	@ApiModelProperty(value = "相关附件")
	private String fileJson;
	/**
	 * 描述
	 */
	@NotEmpty(message = "描述必须填写")
	@ApiModelProperty(value = "描述")
	private String description;
	/**
	 * 开票日期
	 */
	@NotNull(message = "开票日期必须填写")
	@ApiModelProperty(value = "开票日期")
	private LocalDateTime ticketDate;
}
