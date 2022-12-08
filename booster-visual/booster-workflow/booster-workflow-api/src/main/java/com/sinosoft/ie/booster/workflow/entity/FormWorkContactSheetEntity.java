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
 * 工作联系单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_work_contact_sheet")
@EqualsAndHashCode()
@ApiModel(value = "工作联系单")
public class FormWorkContactSheetEntity implements Serializable {

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
	 * 发件人
	 */
	@NotEmpty(message = "发件人必须填写")
	@ApiModelProperty(value = "发件人")
	private String drawPeople;
	/**
	 * 发件部门
	 */
	@NotEmpty(message = "发件部门必须填写")
	@ApiModelProperty(value = "发件部门")
	private String issuingDepartment;
	/**
	 * 收件部门
	 */
	@NotEmpty(message = "收件部门必须填写")
	@ApiModelProperty(value = "收件部门")
	private String serviceDepartment;
	/**
	 * 收件人
	 */
	@NotEmpty(message = "收件人必须填写")
	@ApiModelProperty(value = "收件人")
	private String recipients;
	/**
	 * 协调事项
	 */
	@NotEmpty(message = "协调事项必须填写")
	@ApiModelProperty(value = "协调事项")
	private String coordination;
	/**
	 * 相关附件
	 */
	@NotEmpty(message = "相关附件必须填写")
	@ApiModelProperty(value = "相关附件")
	private String fileJson;
	/**
	 * 发件日期
	 */
	@NotNull(message = "发件日期必须填写")
	@ApiModelProperty(value = "发件日期")
	private LocalDateTime toDate;
	/**
	 * 收件日期
	 */
	@NotNull(message = "收件日期必须填写")
	@ApiModelProperty(value = "收件日期")
	private LocalDateTime collectionDate;
}
