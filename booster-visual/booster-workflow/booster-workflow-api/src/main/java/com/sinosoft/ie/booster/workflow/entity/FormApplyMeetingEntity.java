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
 * 会议申请
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_apply_meeting")
@EqualsAndHashCode()
@ApiModel(value = "会议申请")
public class FormApplyMeetingEntity implements Serializable {

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
	 * 所属职务
	 */
	@NotEmpty(message = "所属职务必须填写")
	@ApiModelProperty(value = "所属职务")
	private String position;
	/**
	 * 会议名称
	 */
	@NotEmpty(message = "会议名称必须填写")
	@ApiModelProperty(value = "会议名称")
	private String conferenceName;
	/**
	 * 会议主题
	 */
	@NotEmpty(message = "会议主题必须填写")
	@ApiModelProperty(value = "会议主题")
	private String conferenceTheme;
	/**
	 * 会议类型
	 */
	@NotEmpty(message = "会议类型必须填写")
	@ApiModelProperty(value = "会议类型")
	private String conferenceType;
	/**
	 * 预计人数
	 */
	@NotEmpty(message = "预计人数必须填写")
	@ApiModelProperty(value = "预计人数")
	private String estimatePeople;
	/**
	 * 会议室
	 */
	@NotEmpty(message = "会议室必须填写")
	@ApiModelProperty(value = "会议室")
	private String conferenceRoom;
	/**
	 * 管理人
	 */
	@NotEmpty(message = "管理人必须填写")
	@ApiModelProperty(value = "管理人")
	private String administrator;
	/**
	 * 查看人
	 */
	@NotEmpty(message = "查看人必须填写")
	@ApiModelProperty(value = "查看人")
	private String lookPeople;
	/**
	 * 纪要员
	 */
	@NotEmpty(message = "纪要员必须填写")
	@ApiModelProperty(value = "纪要员")
	private String memo;
	/**
	 * 出席人
	 */
	@NotEmpty(message = "出席人必须填写")
	@ApiModelProperty(value = "出席人")
	private String attendees;
	/**
	 * 申请材料
	 */
	@NotEmpty(message = "申请材料必须填写")
	@ApiModelProperty(value = "申请材料")
	private String applyMaterial;
	/**
	 * 预计金额
	 */
	@NotNull(message = "预计金额必须填写")
	@ApiModelProperty(value = "预计金额")
	private BigDecimal estimatedAmount;
	/**
	 * 其他出席人
	 */
	@NotEmpty(message = "其他出席人必须填写")
	@ApiModelProperty(value = "其他出席人")
	private String otherAttendee;
	/**
	 * 开始时间
	 */
	@NotNull(message = "开始时间必须填写")
	@ApiModelProperty(value = "开始时间")
	private LocalDateTime startDate;
	/**
	 * 结束时间
	 */
	@NotNull(message = "结束时间必须填写")
	@ApiModelProperty(value = "结束时间")
	private LocalDateTime endDate;
	/**
	 * 相关附件
	 */
	@NotEmpty(message = "相关附件必须填写")
	@ApiModelProperty(value = "相关附件")
	private String fileJson;
	/**
	 * 会议描述
	 */
	@NotEmpty(message = "会议描述必须填写")
	@ApiModelProperty(value = "会议描述")
	private String describe;
}
