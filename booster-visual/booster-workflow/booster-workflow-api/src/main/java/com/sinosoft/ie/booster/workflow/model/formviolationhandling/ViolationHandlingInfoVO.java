package com.sinosoft.ie.booster.workflow.model.formviolationhandling;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 违章处理申请表
 *
 * @author booster开发平台组
 * @since 2021/3/15 8:46
 */
@Data
public class ViolationHandlingInfoVO {
	@ApiModelProperty(value = "主键id")
	private String id;
	@ApiModelProperty(value = "紧急程度")
	private Integer flowUrgent;
	@ApiModelProperty(value = "违章地点")
	private String violationSite;
	@ApiModelProperty(value = "备注")
	private String description;
	@ApiModelProperty(value = "车牌号")
	private String plateNum;
	@ApiModelProperty(value = "负责人")
	private String leadingOfficial;
	@ApiModelProperty(value = "违章日期")
	private Long peccancyDate;
	@ApiModelProperty(value = "流程标题")
	private String flowTitle;
	@ApiModelProperty(value = "驾驶人")
	private String driver;
	@ApiModelProperty(value = "违章扣分")
	private String deduction;
	@ApiModelProperty(value = "通知日期")
	private Long noticeDate;
	@ApiModelProperty(value = "限处理日期")
	private Long limitDate;
	@ApiModelProperty(value = "违章行为")
	private String violationBehavior;
	@ApiModelProperty(value = "违章罚款")
	private BigDecimal amountMoney;
	@ApiModelProperty(value = "流程主键")
	private String flowId;
	@ApiModelProperty(value = "流程单据")
	private String billNo;
}
