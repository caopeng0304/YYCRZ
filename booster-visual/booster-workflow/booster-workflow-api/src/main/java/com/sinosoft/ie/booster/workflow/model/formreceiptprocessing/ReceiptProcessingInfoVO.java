package com.sinosoft.ie.booster.workflow.model.formreceiptprocessing;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 收文处理表
 *
 * @author booster开发平台组
 * @since 2021/3/15 8:46
 */
@Data
public class ReceiptProcessingInfoVO {
	@ApiModelProperty(value = "主键id")
	private String id;
	@ApiModelProperty(value = "相关附件")
	private String fileJson;
	@ApiModelProperty(value = "来文单位")
	private String communicationUnit;
	@ApiModelProperty(value = "流程标题")
	private String flowTitle;
	@ApiModelProperty(value = "紧急程度")
	private Integer flowUrgent;
	@ApiModelProperty(value = "文件标题")
	private String fileTitle;
	@ApiModelProperty(value = "收文日期")
	private Long receiptDate;
	@ApiModelProperty(value = "流程主键")
	private String flowId;
	@ApiModelProperty(value = "流程单据")
	private String billNo;
	@ApiModelProperty(value = "来文字号")
	private String letterNum;
}
