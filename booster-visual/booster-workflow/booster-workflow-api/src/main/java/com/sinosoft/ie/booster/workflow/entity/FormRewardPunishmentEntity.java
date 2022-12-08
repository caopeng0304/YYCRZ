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
 * 行政赏罚单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_reward_punishment")
@EqualsAndHashCode()
@ApiModel(value = "行政赏罚单")
public class FormRewardPunishmentEntity implements Serializable {

	/**
	 * 自然主键
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "自然主键")
	private Long id;
	/**
	 * 主键
	 */
	@NotEmpty(message = "主键必须填写")
	@ApiModelProperty(value = "主键")
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
	 * 员工姓名
	 */
	@NotEmpty(message = "员工姓名必须填写")
	@ApiModelProperty(value = "员工姓名")
	private String fullName;
	/**
	 * 填表日期
	 */
	@NotNull(message = "填表日期必须填写")
	@ApiModelProperty(value = "填表日期")
	private LocalDateTime fillFromDate;
	/**
	 * 员工部门
	 */
	@NotEmpty(message = "员工部门必须填写")
	@ApiModelProperty(value = "员工部门")
	private String department;
	/**
	 * 员工职位
	 */
	@NotEmpty(message = "员工职位必须填写")
	@ApiModelProperty(value = "员工职位")
	private String position;
	/**
	 * 赏罚金额
	 */
	@NotNull(message = "赏罚金额必须填写")
	@ApiModelProperty(value = "赏罚金额")
	private BigDecimal rewardPun;
	/**
	 * 赏罚原因
	 */
	@NotEmpty(message = "赏罚原因必须填写")
	@ApiModelProperty(value = "赏罚原因")
	private String reason;
}
