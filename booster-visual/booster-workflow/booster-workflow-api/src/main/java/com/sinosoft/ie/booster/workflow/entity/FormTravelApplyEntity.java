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
 * 出差预支申请单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_travel_apply")
@EqualsAndHashCode()
@ApiModel(value = "出差预支申请单")
public class FormTravelApplyEntity implements Serializable {

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
	 * 出差人
	 */
	@NotEmpty(message = "出差人必须填写")
	@ApiModelProperty(value = "出差人")
	private String travelMan;
	/**
	 * 申请日期
	 */
	@NotNull(message = "申请日期必须填写")
	@ApiModelProperty(value = "申请日期")
	private LocalDateTime applyDate;
	/**
	 * 所属部门
	 */
	@NotEmpty(message = "所属部门必须填写")
	@ApiModelProperty(value = "所属部门")
	private String departmental;
	/**
	 * 所属职务
	 */
	@NotEmpty(message = "所属职务必须填写")
	@ApiModelProperty(value = "所属职务")
	private String position;
	/**
	 * 开始日期
	 */
	@NotNull(message = "开始日期必须填写")
	@ApiModelProperty(value = "开始日期")
	private LocalDateTime startDate;
	/**
	 * 结束日期
	 */
	@NotNull(message = "结束日期必须填写")
	@ApiModelProperty(value = "结束日期")
	private LocalDateTime endDate;
	/**
	 * 起始地点
	 */
	@NotEmpty(message = "起始地点必须填写")
	@ApiModelProperty(value = "起始地点")
	private String startPlace;
	/**
	 * 目的地
	 */
	@NotEmpty(message = "目的地必须填写")
	@ApiModelProperty(value = "目的地")
	private String destination;
	/**
	 * 预支旅费
	 */
	@NotNull(message = "预支旅费必须填写")
	@ApiModelProperty(value = "预支旅费")
	private BigDecimal prepaidTravel;
	/**
	 * 备注
	 */
	@NotEmpty(message = "备注必须填写")
	@ApiModelProperty(value = "备注")
	private String description;
}
