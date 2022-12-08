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
 * 文件签阅表
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_document_signing")
@EqualsAndHashCode()
@ApiModel(value = "文件签阅表")
public class FormDocumentSigningEntity implements Serializable {

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
	 * 文件编号
	 */
	@NotEmpty(message = "文件编号必须填写")
	@ApiModelProperty(value = "文件编号")
	private String fileNum;
	/**
	 * 拟稿人
	 */
	@NotEmpty(message = "拟稿人必须填写")
	@ApiModelProperty(value = "拟稿人")
	private String draftedPerson;
	/**
	 * 签阅人
	 */
	@NotEmpty(message = "签阅人必须填写")
	@ApiModelProperty(value = "签阅人")
	private String reader;
	/**
	 * 文件拟办
	 */
	@NotEmpty(message = "文件拟办必须填写")
	@ApiModelProperty(value = "文件拟办")
	private String filePreparation;
	/**
	 * 签阅时间
	 */
	@NotNull(message = "签阅时间必须填写")
	@ApiModelProperty(value = "签阅时间")
	private LocalDateTime checkDate;
	/**
	 * 发稿日期
	 */
	@NotNull(message = "发稿日期必须填写")
	@ApiModelProperty(value = "发稿日期")
	private LocalDateTime publicationDate;
	/**
	 * 相关附件
	 */
	@NotEmpty(message = "相关附件必须填写")
	@ApiModelProperty(value = "相关附件")
	private String fileJson;
	/**
	 * 文件内容
	 */
	@NotEmpty(message = "文件内容必须填写")
	@ApiModelProperty(value = "文件内容")
	private String documentContent;
	/**
	 * 建议栏
	 */
	@NotEmpty(message = "建议栏必须填写")
	@ApiModelProperty(value = "建议栏")
	private String adviceColumn;
}
