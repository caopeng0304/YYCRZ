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
 * 收文处理表
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_receipt_processing")
@EqualsAndHashCode()
@ApiModel(value = "收文处理表")
public class FormReceiptProcessingEntity implements Serializable {

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
	 * 文件标题
	 */
	@NotEmpty(message = "文件标题必须填写")
	@ApiModelProperty(value = "文件标题")
	private String fileTitle;
	/**
	 * 来文单位
	 */
	@NotEmpty(message = "来文单位必须填写")
	@ApiModelProperty(value = "来文单位")
	private String communicationUnit;
	/**
	 * 来文字号
	 */
	@NotEmpty(message = "来文字号必须填写")
	@ApiModelProperty(value = "来文字号")
	private String letterNum;
	/**
	 * 收文日期
	 */
	@NotNull(message = "收文日期必须填写")
	@ApiModelProperty(value = "收文日期")
	private LocalDateTime receiptDate;
	/**
	 * 相关附件
	 */
	@NotEmpty(message = "相关附件必须填写")
	@ApiModelProperty(value = "相关附件")
	private String fileJson;
}
