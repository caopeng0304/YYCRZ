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
 * 补卡申请
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_supplement_card")
@EqualsAndHashCode()
@ApiModel(value = "补卡申请")
public class FormSupplementCardEntity implements Serializable {

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
	 * 员工姓名
	 */
	@NotEmpty(message = "员工姓名必须填写")
	@ApiModelProperty(value = "员工姓名")
	private String fullName;
	/**
	 * 所在部门
	 */
	@NotEmpty(message = "所在部门必须填写")
	@ApiModelProperty(value = "所在部门")
	private String department;
	/**
	 * 所在职务
	 */
	@NotEmpty(message = "所在职务必须填写")
	@ApiModelProperty(value = "所在职务")
	private String position;
	/**
	 * 证明人
	 */
	@NotEmpty(message = "证明人必须填写")
	@ApiModelProperty(value = "证明人")
	private String witness;
	/**
	 * 补卡次数
	 */
	@NotEmpty(message = "补卡次数必须填写")
	@ApiModelProperty(value = "补卡次数")
	private String supplementNum;
	/**
	 * 备注
	 */
	@NotEmpty(message = "备注必须填写")
	@ApiModelProperty(value = "备注")
	private String description;
	/**
	 * 申请日期
	 */
	@NotNull(message = "申请日期必须填写")
	@ApiModelProperty(value = "申请日期")
	private LocalDateTime applyDate;
	/**
	 * 开始时间
	 */
	@NotNull(message = "开始时间必须填写")
	@ApiModelProperty(value = "开始时间")
	private LocalDateTime startTime;
	/**
	 * 结束日期
	 */
	@NotNull(message = "结束日期必须填写")
	@ApiModelProperty(value = "结束日期")
	private LocalDateTime endTime;
}
