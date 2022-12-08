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
 * 行文呈批表
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_batch_table")
@EqualsAndHashCode()
@ApiModel(value = "行文呈批表")
public class FormBatchTableEntity implements Serializable {

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
	 * 主办单位
	 */
	@NotEmpty(message = "主办单位必须填写")
	@ApiModelProperty(value = "主办单位")
	private String draftedPerson;
	/**
	 * 文件编号
	 */
	@NotEmpty(message = "文件编号必须填写")
	@ApiModelProperty(value = "文件编号")
	private String fileNum;
	/**
	 * 发往单位
	 */
	@NotEmpty(message = "发往单位必须填写")
	@ApiModelProperty(value = "发往单位")
	private String sendUnit;
	/**
	 * 打字
	 */
	@NotEmpty(message = "打字必须填写")
	@ApiModelProperty(value = "打字")
	private String typing;
	/**
	 * 发文日期
	 */
	@NotNull(message = "发文日期必须填写")
	@ApiModelProperty(value = "发文日期")
	private LocalDateTime writingDate;
	/**
	 * 份数
	 */
	@NotEmpty(message = "份数必须填写")
	@ApiModelProperty(value = "份数")
	private String shareNum;
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
}
