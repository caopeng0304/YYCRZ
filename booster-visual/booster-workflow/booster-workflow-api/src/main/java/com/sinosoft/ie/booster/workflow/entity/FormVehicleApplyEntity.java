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
 * 车辆申请
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_vehicle_apply")
@EqualsAndHashCode()
@ApiModel(value = "车辆申请")
public class FormVehicleApplyEntity implements Serializable {

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
	 * 用车人
	 */
	@NotEmpty(message = "用车人必须填写")
	@ApiModelProperty(value = "用车人")
	private String carMan;
	/**
	 * 所在部门
	 */
	@NotEmpty(message = "所在部门必须填写")
	@ApiModelProperty(value = "所在部门")
	private String department;
	/**
	 * 车牌号
	 */
	@NotEmpty(message = "车牌号必须填写")
	@ApiModelProperty(value = "车牌号")
	private String plateNum;
	/**
	 * 目的地
	 */
	@NotEmpty(message = "目的地必须填写")
	@ApiModelProperty(value = "目的地")
	private String destination;
	/**
	 * 路费金额
	 */
	@NotNull(message = "路费金额必须填写")
	@ApiModelProperty(value = "路费金额")
	private BigDecimal roadFee;
	/**
	 * 公里数
	 */
	@NotEmpty(message = "公里数必须填写")
	@ApiModelProperty(value = "公里数")
	private String kilometreNum;
	/**
	 * 随行人数
	 */
	@NotEmpty(message = "随行人数必须填写")
	@ApiModelProperty(value = "随行人数")
	private String entourage;
	/**
	 * 备注
	 */
	@NotEmpty(message = "备注必须填写")
	@ApiModelProperty(value = "备注")
	private String description;
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
}
