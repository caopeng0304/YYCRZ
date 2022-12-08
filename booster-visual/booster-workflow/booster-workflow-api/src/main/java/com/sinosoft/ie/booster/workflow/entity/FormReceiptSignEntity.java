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
 * 收文签呈单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_receipt_sign")
@EqualsAndHashCode()
@ApiModel(value = "收文签呈单")
public class FormReceiptSignEntity implements Serializable {

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
	 * 收文标题
	 */
	@NotEmpty(message = "收文标题必须填写")
	@ApiModelProperty(value = "收文标题")
	private String receiptTitle;
	/**
	 * 收文部门
	 */
	@NotEmpty(message = "收文部门必须填写")
	@ApiModelProperty(value = "收文部门")
	private String department;
	/**
	 * 收文人
	 */
	@NotEmpty(message = "收文人必须填写")
	@ApiModelProperty(value = "收文人")
	private String collector;
	/**
	 * 相关附件
	 */
	@NotEmpty(message = "相关附件必须填写")
	@ApiModelProperty(value = "相关附件")
	private String fileJson;
	/**
	 * 收文简述
	 */
	@NotEmpty(message = "收文简述必须填写")
	@ApiModelProperty(value = "收文简述")
	private String receiptPaper;
	/**
	 * 收文日期
	 */
	@NotNull(message = "收文日期必须填写")
	@ApiModelProperty(value = "收文日期")
	private LocalDateTime receiptDate;
}
