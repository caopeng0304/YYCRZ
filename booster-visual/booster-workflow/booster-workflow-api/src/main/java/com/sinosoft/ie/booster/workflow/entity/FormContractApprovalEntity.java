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
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 合同审批
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_contract_approval")
@EqualsAndHashCode()
@ApiModel(value = "合同审批")
public class FormContractApprovalEntity implements Serializable {

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
	 * 甲方单位
	 */
	@NotEmpty(message = "甲方单位必须填写")
	@ApiModelProperty(value = "甲方单位")
	private String firstPartyUnit;
	/**
	 * 乙方单位
	 */
	@NotEmpty(message = "乙方单位必须填写")
	@ApiModelProperty(value = "乙方单位")
	private String secondPartyUnit;
	/**
	 * 甲方负责人
	 */
	@NotEmpty(message = "甲方负责人必须填写")
	@ApiModelProperty(value = "甲方负责人")
	private String firstPartyPerson;
	/**
	 * 乙方负责人
	 */
	@NotEmpty(message = "乙方负责人必须填写")
	@ApiModelProperty(value = "乙方负责人")
	private String secondPartyPerson;
	/**
	 * 甲方联系方式
	 */
	@NotEmpty(message = "甲方联系方式必须填写")
	@ApiModelProperty(value = "甲方联系方式")
	private String firstPartyContact;
	/**
	 * 乙方联系方式
	 */
	@NotEmpty(message = "乙方联系方式必须填写")
	@ApiModelProperty(value = "乙方联系方式")
	private String secondPartyContact;
	/**
	 * 合同名称
	 */
	@NotEmpty(message = "合同名称必须填写")
	@ApiModelProperty(value = "合同名称")
	private String contractName;
	/**
	 * 合同分类
	 */
	@NotEmpty(message = "合同分类必须填写")
	@ApiModelProperty(value = "合同分类")
	private String contractClass;
	/**
	 * 合同类型
	 */
	@NotEmpty(message = "合同类型必须填写")
	@ApiModelProperty(value = "合同类型")
	private String contractType;
	/**
	 * 合同编号
	 */
	@NotEmpty(message = "合同编号必须填写")
	@ApiModelProperty(value = "合同编号")
	private String contractId;
	/**
	 * 业务人员
	 */
	@NotEmpty(message = "业务人员必须填写")
	@ApiModelProperty(value = "业务人员")
	private String businessPerson;
	/**
	 * 收入金额
	 */
	@NotNull(message = "收入金额必须填写")
	@ApiModelProperty(value = "收入金额")
	private BigDecimal incomeAmount;
	/**
	 * 填写人员
	 */
	@NotEmpty(message = "填写人员必须填写")
	@ApiModelProperty(value = "填写人员")
	private String inputPerson;
	/**
	 * 相关附件
	 */
	@NotEmpty(message = "相关附件必须填写")
	@ApiModelProperty(value = "相关附件")
	private String fileJson;
	/**
	 * 主要内容
	 */
	@NotEmpty(message = "主要内容必须填写")
	@ApiModelProperty(value = "主要内容")
	private String primaryCoverage;
	/**
	 * 备注
	 */
	@NotEmpty(message = "备注必须填写")
	@ApiModelProperty(value = "备注")
	private String description;
	/**
	 * 签约时间
	 */
	@NotNull(message = "签约时间必须填写")
	@ApiModelProperty(value = "签约时间")
	private LocalDateTime signingDate;
	/**
	 * 开始时间
	 */
	@NotNull(message = "开始时间必须填写")
	@ApiModelProperty(value = "开始时间")
	private LocalDateTime startDate;
	/**
	 * 结束时间
	 */
	@NotNull(message = "结束时间必须填写")
	@ApiModelProperty(value = "结束时间")
	private LocalDateTime endDate;
}
