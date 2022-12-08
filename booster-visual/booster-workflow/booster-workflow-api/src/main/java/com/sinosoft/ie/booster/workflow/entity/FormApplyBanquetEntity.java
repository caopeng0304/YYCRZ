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
 * 宴请申请
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_apply_banquet")
@EqualsAndHashCode()
@ApiModel(value = "宴请申请")
public class FormApplyBanquetEntity implements Serializable {

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
	 * 申请人员
	 */
	@NotEmpty(message = "申请人员必须填写")
	@ApiModelProperty(value = "申请人员")
	private String applyUser;
	/**
	 * 所属职务
	 */
	@NotEmpty(message = "所属职务必须填写")
	@ApiModelProperty(value = "所属职务")
	private String position;
	/**
	 * 申请日期
	 */
	@NotNull(message = "申请日期必须填写")
	@ApiModelProperty(value = "申请日期")
	private LocalDateTime applyDate;
	/**
	 * 宴请人数
	 */
	@NotEmpty(message = "宴请人数必须填写")
	@ApiModelProperty(value = "宴请人数")
	private String banquetNum;
	/**
	 * 宴请人员
	 */
	@NotEmpty(message = "宴请人员必须填写")
	@ApiModelProperty(value = "宴请人员")
	private String banquetPeople;
	/**
	 * 人员总数
	 */
	@NotEmpty(message = "人员总数必须填写")
	@ApiModelProperty(value = "人员总数")
	private String total;
	/**
	 * 宴请地点
	 */
	@NotEmpty(message = "宴请地点必须填写")
	@ApiModelProperty(value = "宴请地点")
	private String place;
	/**
	 * 预计费用
	 */
	@NotNull(message = "预计费用必须填写")
	@ApiModelProperty(value = "预计费用")
	private BigDecimal expectedCost;
	/**
	 * 备注
	 */
	@NotEmpty(message = "备注必须填写")
	@ApiModelProperty(value = "备注")
	private String description;
}
