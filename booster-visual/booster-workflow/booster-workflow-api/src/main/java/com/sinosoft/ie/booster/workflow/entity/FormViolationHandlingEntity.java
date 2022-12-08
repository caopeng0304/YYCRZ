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
 * 违章处理申请表
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_violation_handling")
@EqualsAndHashCode()
@ApiModel(value = "违章处理申请表")
public class FormViolationHandlingEntity implements Serializable {

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
	 * 车牌号
	 */
	@NotEmpty(message = "车牌号必须填写")
	@ApiModelProperty(value = "车牌号")
	private String plateNum;
	/**
	 * 驾驶人
	 */
	@NotEmpty(message = "驾驶人必须填写")
	@ApiModelProperty(value = "驾驶人")
	private String driver;
	/**
	 * 负责人
	 */
	@NotEmpty(message = "负责人必须填写")
	@ApiModelProperty(value = "负责人")
	private String leadingOfficial;
	/**
	 * 违章地点
	 */
	@NotEmpty(message = "违章地点必须填写")
	@ApiModelProperty(value = "违章地点")
	private String violationSite;
	/**
	 * 违章行为
	 */
	@NotEmpty(message = "违章行为必须填写")
	@ApiModelProperty(value = "违章行为")
	private String violationBehavior;
	/**
	 * 违章扣分
	 */
	@NotEmpty(message = "违章扣分必须填写")
	@ApiModelProperty(value = "违章扣分")
	private String deduction;
	/**
	 * 违章罚款
	 */
	@NotNull(message = "违章罚款必须填写")
	@ApiModelProperty(value = "违章罚款")
	private BigDecimal amountMoney;
	/**
	 * 备注
	 */
	@NotEmpty(message = "备注必须填写")
	@ApiModelProperty(value = "备注")
	private String description;
	/**
	 * 通知日期
	 */
	@NotNull(message = "通知日期必须填写")
	@ApiModelProperty(value = "通知日期")
	private LocalDateTime noticeDate;
	/**
	 * 违章日期
	 */
	@NotNull(message = "违章日期必须填写")
	@ApiModelProperty(value = "违章日期")
	private LocalDateTime peccancyDate;
	/**
	 * 限处理日期
	 */
	@NotNull(message = "限处理日期必须填写")
	@ApiModelProperty(value = "限处理日期")
	private LocalDateTime limitDate;
}
