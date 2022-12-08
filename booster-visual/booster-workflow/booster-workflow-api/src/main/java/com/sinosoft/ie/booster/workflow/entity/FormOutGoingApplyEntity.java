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
 * 外出申请单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_out_going_apply")
@EqualsAndHashCode()
@ApiModel(value = "外出申请单")
public class FormOutGoingApplyEntity implements Serializable {

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
	 * 申请人
	 */
	@NotEmpty(message = "申请人必须填写")
	@ApiModelProperty(value = "申请人")
	private String applyUser;
	/**
	 * 所在部门
	 */
	@NotEmpty(message = "所在部门必须填写")
	@ApiModelProperty(value = "所在部门")
	private String department;
	/**
	 * 申请日期
	 */
	@NotNull(message = "申请日期必须填写")
	@ApiModelProperty(value = "申请日期")
	private LocalDateTime applyDate;
	/**
	 * 外出总计
	 */
	@NotEmpty(message = "外出总计必须填写")
	@ApiModelProperty(value = "外出总计")
	private String outGoingTotal;
	/**
	 * 开始时间
	 */
	@NotNull(message = "开始时间必须填写")
	@ApiModelProperty(value = "开始时间")
	private LocalDateTime startTime;
	/**
	 * 结束时间
	 */
	@NotNull(message = "结束时间必须填写")
	@ApiModelProperty(value = "结束时间")
	private LocalDateTime endTime;
	/**
	 * 目的地
	 */
	@NotEmpty(message = "目的地必须填写")
	@ApiModelProperty(value = "目的地")
	private String destination;
	/**
	 * 相关附件
	 */
	@NotEmpty(message = "相关附件必须填写")
	@ApiModelProperty(value = "相关附件")
	private String fileJson;
	/**
	 * 外出事由
	 */
	@NotEmpty(message = "外出事由必须填写")
	@ApiModelProperty(value = "外出事由")
	private String outGoingCause;
}
