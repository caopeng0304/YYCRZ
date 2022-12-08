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
 * 销售支持表
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_sales_support")
@EqualsAndHashCode()
@ApiModel(value = "销售支持表")
public class FormSalesSupportEntity implements Serializable {

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
	 * 申请部门
	 */
	@NotEmpty(message = "申请部门必须填写")
	@ApiModelProperty(value = "申请部门")
	private String applyDept;
	/**
	 * 相关客户
	 */
	@NotEmpty(message = "相关客户必须填写")
	@ApiModelProperty(value = "相关客户")
	private String customer;
	/**
	 * 相关项目
	 */
	@NotEmpty(message = "相关项目必须填写")
	@ApiModelProperty(value = "相关项目")
	private String project;
	/**
	 * 售前支持
	 */
	@NotEmpty(message = "售前支持必须填写")
	@ApiModelProperty(value = "售前支持")
	private String pSaleSupInfo;
	/**
	 * 开始时间
	 */
	@NotNull(message = "开始时间必须填写")
	@ApiModelProperty(value = "开始时间")
	private LocalDateTime startDate;
	/**
	 * 结束日期
	 */
	@NotNull(message = "结束日期必须填写")
	@ApiModelProperty(value = "结束日期")
	private LocalDateTime endDate;
	/**
	 * 支持天数
	 */
	@NotEmpty(message = "支持天数必须填写")
	@ApiModelProperty(value = "支持天数")
	private String pSaleSupDays;
	/**
	 * 准备天数
	 */
	@NotEmpty(message = "准备天数必须填写")
	@ApiModelProperty(value = "准备天数")
	private String pSalePreDays;
	/**
	 * 机构咨询
	 */
	@NotEmpty(message = "机构咨询必须填写")
	@ApiModelProperty(value = "机构咨询")
	private String consulManager;
	/**
	 * 售前顾问
	 */
	@NotEmpty(message = "售前顾问必须填写")
	@ApiModelProperty(value = "售前顾问")
	private String pSalSupConsul;
	/**
	 * fileJson
	 */
	@NotEmpty(message = "fileJson必须填写")
	@ApiModelProperty(value = "fileJson")
	private String fileJson;
	/**
	 * 销售总结
	 */
	@NotEmpty(message = "销售总结必须填写")
	@ApiModelProperty(value = "销售总结")
	private String salSupConclusion;
	/**
	 * 交付说明
	 */
	@NotEmpty(message = "交付说明必须填写")
	@ApiModelProperty(value = "交付说明")
	private String consultResult;
	/**
	 * 咨询评价
	 */
	@NotEmpty(message = "咨询评价必须填写")
	@ApiModelProperty(value = "咨询评价")
	private String iEvaluation;
	/**
	 * 发起人总结
	 */
	@NotEmpty(message = "发起人总结必须填写")
	@ApiModelProperty(value = "发起人总结")
	private String conclusion;
}
