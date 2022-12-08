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
 * 档案借阅申请
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_archival_borrow")
@EqualsAndHashCode()
@ApiModel(value = "档案借阅申请")
public class FormArchivalBorrowEntity implements Serializable {

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
	 * 借阅部门
	 */
	@NotEmpty(message = "借阅部门必须填写")
	@ApiModelProperty(value = "借阅部门")
	private String borrowingDepartment;
	/**
	 * 档案名称
	 */
	@NotEmpty(message = "档案名称必须填写")
	@ApiModelProperty(value = "档案名称")
	private String archivesName;
	/**
	 * 档案属性
	 */
	@NotEmpty(message = "档案属性必须填写")
	@ApiModelProperty(value = "档案属性")
	private String archivalAttributes;
	/**
	 * 借阅方式
	 */
	@NotEmpty(message = "借阅方式必须填写")
	@ApiModelProperty(value = "借阅方式")
	private String borrowMode;
	/**
	 * 申请原因
	 */
	@NotEmpty(message = "申请原因必须填写")
	@ApiModelProperty(value = "申请原因")
	private String applyReason;
	/**
	 * 档案编号
	 */
	@NotEmpty(message = "档案编号必须填写")
	@ApiModelProperty(value = "档案编号")
	private String archivesId;
	/**
	 * 借阅时间
	 */
	@NotNull(message = "借阅时间必须填写")
	@ApiModelProperty(value = "借阅时间")
	private LocalDateTime borrowingDate;
	/**
	 * 归还时间
	 */
	@NotNull(message = "归还时间必须填写")
	@ApiModelProperty(value = "归还时间")
	private LocalDateTime returnDate;
}
