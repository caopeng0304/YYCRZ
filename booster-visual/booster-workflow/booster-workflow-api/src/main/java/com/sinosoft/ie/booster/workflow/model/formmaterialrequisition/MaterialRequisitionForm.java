package com.sinosoft.ie.booster.workflow.model.formmaterialrequisition;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 领料单
 *
 * @author booster开发平台组
 * @since 2021/3/15 8:46
 */
@Data
public class MaterialRequisitionForm {
	@NotBlank(message = "必填")
	@ApiModelProperty(value = "流程主键")
	private String flowId;
	@NotBlank(message = "必填")
	@ApiModelProperty(value = "流程标题")
	private String flowTitle;
	@NotNull(message = "必填")
	@ApiModelProperty(value = "紧急程度")
	private Integer flowUrgent;
	@NotBlank(message = "必填")
	@ApiModelProperty(value = "流程单据")
	private String billNo;
	@NotBlank(message = "必填")
	@ApiModelProperty(value = "领料人")
	private String leadPeople;
	@NotBlank(message = "必填")
	@ApiModelProperty(value = "领料部门")
	private String leadDepartment;
	@NotNull(message = "必填")
	@ApiModelProperty(value = "领料日期")
	private Long leadDate;
	@ApiModelProperty(value = "仓库")
	private String warehouse;
	@ApiModelProperty(value = "备注")
	private String description;
	@ApiModelProperty(value = "提交/保存 0-1")
	private String status;
	@ApiModelProperty(value = "明细")
	List<MaterialEntryEntityInfoModel> entryList;
}
