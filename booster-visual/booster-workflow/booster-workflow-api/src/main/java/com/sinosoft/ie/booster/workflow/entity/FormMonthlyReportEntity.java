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
 * 月工作总结
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_monthly_report")
@EqualsAndHashCode()
@ApiModel(value = "月工作总结")
public class FormMonthlyReportEntity implements Serializable {

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
	 * 创建人
	 */
	@NotEmpty(message = "创建人必须填写")
	@ApiModelProperty(value = "创建人")
	private String applyUser;
	/**
	 * 创建日期
	 */
	@NotNull(message = "创建日期必须填写")
	@ApiModelProperty(value = "创建日期")
	private LocalDateTime applyDate;
	/**
	 * 所属部门
	 */
	@NotEmpty(message = "所属部门必须填写")
	@ApiModelProperty(value = "所属部门")
	private String applyDept;
	/**
	 * 所属职务
	 */
	@NotEmpty(message = "所属职务必须填写")
	@ApiModelProperty(value = "所属职务")
	private String applyPost;
	/**
	 * 完成时间
	 */
	@NotNull(message = "完成时间必须填写")
	@ApiModelProperty(value = "完成时间")
	private LocalDateTime planEndTime;
	/**
	 * 总体评价
	 */
	@NotEmpty(message = "总体评价必须填写")
	@ApiModelProperty(value = "总体评价")
	private String overallEvaluate;
	/**
	 * 工作事项
	 */
	@NotEmpty(message = "工作事项必须填写")
	@ApiModelProperty(value = "工作事项")
	private String npWorkMatter;
	/**
	 * 次月日期
	 */
	@NotNull(message = "次月日期必须填写")
	@ApiModelProperty(value = "次月日期")
	private LocalDateTime npFinishTime;
	/**
	 * 次月目标
	 */
	@NotEmpty(message = "次月目标必须填写")
	@ApiModelProperty(value = "次月目标")
	private String npFinishMethod;
	/**
	 * 相关附件
	 */
	@NotEmpty(message = "相关附件必须填写")
	@ApiModelProperty(value = "相关附件")
	private String fileJson;
}
