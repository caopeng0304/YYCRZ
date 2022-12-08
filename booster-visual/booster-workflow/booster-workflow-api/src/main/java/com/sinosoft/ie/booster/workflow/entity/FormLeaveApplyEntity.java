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
 * 流程表单【请假申请】
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_leave_apply")
@EqualsAndHashCode()
@ApiModel(value = "流程表单【请假申请】")
public class FormLeaveApplyEntity implements Serializable {

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
	 * 单据编号
	 */
	@NotEmpty(message = "单据编号必须填写")
	@ApiModelProperty(value = "单据编号")
	private String billNo;
	/**
	 * 申请人员
	 */
	@NotEmpty(message = "申请人员必须填写")
	@ApiModelProperty(value = "申请人员")
	private String applyUser;
	/**
	 * 申请部门
	 */
	@NotEmpty(message = "申请部门必须填写")
	@ApiModelProperty(value = "申请部门")
	private String applyDept;
	/**
	 * 申请职位
	 */
	@NotEmpty(message = "申请职位必须填写")
	@ApiModelProperty(value = "申请职位")
	private String applyPost;
	/**
	 * 请假类别
	 */
	@NotEmpty(message = "请假类别必须填写")
	@ApiModelProperty(value = "请假类别")
	private String leaveType;
	/**
	 * 请假原因
	 */
	@NotEmpty(message = "请假原因必须填写")
	@ApiModelProperty(value = "请假原因")
	private String leaveReason;
	/**
	 * 请假天数
	 */
	@NotEmpty(message = "请假天数必须填写")
	@ApiModelProperty(value = "请假天数")
	private String leaveDayCount;
	/**
	 * 请假小时
	 */
	@NotEmpty(message = "请假小时必须填写")
	@ApiModelProperty(value = "请假小时")
	private String leaveHour;
	/**
	 * 相关附件
	 */
	@NotEmpty(message = "相关附件必须填写")
	@ApiModelProperty(value = "相关附件")
	private String fileJson;
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
	 * 请假时间
	 */
	@NotNull(message = "请假时间必须填写")
	@ApiModelProperty(value = "请假时间")
	private LocalDateTime leaveStartTime;
	/**
	 * 结束时间
	 */
	@NotNull(message = "结束时间必须填写")
	@ApiModelProperty(value = "结束时间")
	private LocalDateTime leaveEndTime;
}
