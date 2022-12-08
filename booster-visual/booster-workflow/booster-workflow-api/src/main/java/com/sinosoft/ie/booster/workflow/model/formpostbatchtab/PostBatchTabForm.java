package com.sinosoft.ie.booster.workflow.model.formpostbatchtab;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 发文呈批表
 *
 * @author booster开发平台组
 * @since 2021/3/15 8:46
 */
@Data
public class PostBatchTabForm {

	private String draftedPerson;
	@ApiModelProperty(value = "相关附件")
	private String fileJson;
	@NotBlank(message = "必填")
	@ApiModelProperty(value = "流程标题")
	private String flowTitle;
	@NotNull(message = "必填")
	@ApiModelProperty(value = "紧急程度")
	private Integer flowUrgent;
	@NotNull(message = "必填")
	@ApiModelProperty(value = "发文日期")
	private Long writingDate;
	@ApiModelProperty(value = "文件标题")
	private String fileTitle;
	@ApiModelProperty(value = "发往单位")
	private String sendUnit;
	@ApiModelProperty(value = "备注")
	private String description;
	@ApiModelProperty(value = "发文编码")
	private String writingNum;
	@NotBlank(message = "必填")
	@ApiModelProperty(value = "流程主键")
	private String flowId;
	@NotBlank(message = "必填")
	@ApiModelProperty(value = "流程单据")
	private String billNo;
	@ApiModelProperty(value = "份数")
	private String shareNum;
	@ApiModelProperty(value = "提交/保存 0-1")
	private String status;
}
