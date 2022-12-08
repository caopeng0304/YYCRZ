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
 * 合同申请单表
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_contract_approval_sheet")
@EqualsAndHashCode()
@ApiModel(value = "合同申请单表")
public class FormContractApprovalSheetEntity implements Serializable {

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
	 * 申请人
	 */
	@NotEmpty(message = "申请人必须填写")
	@ApiModelProperty(value = "申请人")
	private String applyUser;
	/**
	 * 申请日期
	 */
	@NotNull(message = "申请日期必须填写")
	@ApiModelProperty(value = "申请日期")
	private LocalDateTime applyDate;
	/**
	 * 编号支出
	 */
	@NotEmpty(message = "编号支出必须填写")
	@ApiModelProperty(value = "编号支出")
	private String contractId;
	/**
	 * 合同号
	 */
	@NotEmpty(message = "合同号必须填写")
	@ApiModelProperty(value = "合同号")
	private String contractNum;
	/**
	 * 签署方(甲方)
	 */
	@NotEmpty(message = "签署方(甲方)必须填写")
	@ApiModelProperty(value = "签署方(甲方)")
	private String firstParty;
	/**
	 * 乙方
	 */
	@NotEmpty(message = "乙方必须填写")
	@ApiModelProperty(value = "乙方")
	private String secondParty;
	/**
	 * 合同名称
	 */
	@NotEmpty(message = "合同名称必须填写")
	@ApiModelProperty(value = "合同名称")
	private String contractName;
	/**
	 * 合同类型
	 */
	@NotEmpty(message = "合同类型必须填写")
	@ApiModelProperty(value = "合同类型")
	private String contractType;
	/**
	 * 合作负责人
	 */
	@NotEmpty(message = "合作负责人必须填写")
	@ApiModelProperty(value = "合作负责人")
	private String personCharge;
	/**
	 * 所属部门
	 */
	@NotEmpty(message = "所属部门必须填写")
	@ApiModelProperty(value = "所属部门")
	private String leadDepartment;
	/**
	 * 签订地区
	 */
	@NotEmpty(message = "签订地区必须填写")
	@ApiModelProperty(value = "签订地区")
	private String signArea;
	/**
	 * 收入金额
	 */
	@NotNull(message = "收入金额必须填写")
	@ApiModelProperty(value = "收入金额")
	private BigDecimal incomeAmount;
	/**
	 * 支出总额
	 */
	@NotNull(message = "支出总额必须填写")
	@ApiModelProperty(value = "支出总额")
	private BigDecimal totalExpenditure;
	/**
	 * 合同期限
	 */
	@NotEmpty(message = "合同期限必须填写")
	@ApiModelProperty(value = "合同期限")
	private String contractPeriod;
	/**
	 * 付款方式
	 */
	@NotEmpty(message = "付款方式必须填写")
	@ApiModelProperty(value = "付款方式")
	private String paymentMethod;
	/**
	 * 预算批付
	 */
	@NotEmpty(message = "预算批付必须填写")
	@ApiModelProperty(value = "预算批付")
	private String budgetaryApproval;
	/**
	 * 开始时间
	 */
	@NotNull(message = "开始时间必须填写")
	@ApiModelProperty(value = "开始时间")
	private LocalDateTime startContractDate;
	/**
	 * endContractDate
	 */
	@NotNull(message = "endContractDate必须填写")
	@ApiModelProperty(value = "endContractDate")
	private LocalDateTime endContractDate;
	/**
	 * 相关附件
	 */
	@NotEmpty(message = "相关附件必须填写")
	@ApiModelProperty(value = "相关附件")
	private String fileJson;
	/**
	 * 内容简要
	 */
	@NotEmpty(message = "内容简要必须填写")
	@ApiModelProperty(value = "内容简要")
	private String contractContent;
}
