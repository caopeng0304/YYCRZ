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
 * 领料单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_material_requisition")
@EqualsAndHashCode()
@ApiModel(value = "领料单")
public class FormMaterialRequisitionEntity implements Serializable {

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
	 * 领料人
	 */
	@NotEmpty(message = "领料人必须填写")
	@ApiModelProperty(value = "领料人")
	private String leadPeople;
	/**
	 * 领料部门
	 */
	@NotEmpty(message = "领料部门必须填写")
	@ApiModelProperty(value = "领料部门")
	private String leadDepartment;
	/**
	 * 领料日期
	 */
	@NotNull(message = "领料日期必须填写")
	@ApiModelProperty(value = "领料日期")
	private LocalDateTime leadDate;
	/**
	 * 仓库
	 */
	@NotEmpty(message = "仓库必须填写")
	@ApiModelProperty(value = "仓库")
	private String warehouse;
	/**
	 * 备注
	 */
	@NotEmpty(message = "备注必须填写")
	@ApiModelProperty(value = "备注")
	private String description;
}
