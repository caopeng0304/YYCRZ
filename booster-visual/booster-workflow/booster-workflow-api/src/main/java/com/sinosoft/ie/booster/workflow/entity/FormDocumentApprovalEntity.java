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
 * 文件签批意见表
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_document_approval")
@EqualsAndHashCode()
@ApiModel(value = "文件签批意见表")
public class FormDocumentApprovalEntity implements Serializable {

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
	 * 文件名称
	 */
	@NotEmpty(message = "文件名称必须填写")
	@ApiModelProperty(value = "文件名称")
	private String fileName;
	/**
	 * 拟稿人
	 */
	@NotEmpty(message = "拟稿人必须填写")
	@ApiModelProperty(value = "拟稿人")
	private String draftedPerson;
	/**
	 * 发文单位
	 */
	@NotEmpty(message = "发文单位必须填写")
	@ApiModelProperty(value = "发文单位")
	private String serviceUnit;
	/**
	 * 文件拟办
	 */
	@NotEmpty(message = "文件拟办必须填写")
	@ApiModelProperty(value = "文件拟办")
	private String filePreparation;
	/**
	 * 文件编号
	 */
	@NotEmpty(message = "文件编号必须填写")
	@ApiModelProperty(value = "文件编号")
	private String fileNum;
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
	/**
	 * 修改意见
	 */
	@NotEmpty(message = "修改意见必须填写")
	@ApiModelProperty(value = "修改意见")
	private String modifyOpinion;
}
