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
 * 报价审批表
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_quotation_approval")
@EqualsAndHashCode()
@ApiModel(value = "报价审批表")
public class FormQuotationApprovalEntity implements Serializable {

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
	 * 流程等级
	 */
	@NotNull(message = "流程等级必须填写")
	@ApiModelProperty(value = "流程等级")
	private Integer flowUrgent;
	/**
	 * 流程单据
	 */
	@NotEmpty(message = "流程单据必须填写")
	@ApiModelProperty(value = "流程单据")
	private String billNo;
	/**
	 * 填报人
	 */
	@NotEmpty(message = "填报人必须填写")
	@ApiModelProperty(value = "填报人")
	private String writer;
	/**
	 * 填表日期
	 */
	@NotNull(message = "填表日期必须填写")
	@ApiModelProperty(value = "填表日期")
	private LocalDateTime writeDate;
	/**
	 * 客户名称
	 */
	@NotEmpty(message = "客户名称必须填写")
	@ApiModelProperty(value = "客户名称")
	private String customerName;
	/**
	 * 类型
	 */
	@NotEmpty(message = "类型必须填写")
	@ApiModelProperty(value = "类型")
	private String quotationType;
	/**
	 * 合作人名
	 */
	@NotEmpty(message = "合作人名必须填写")
	@ApiModelProperty(value = "合作人名")
	private String partnerName;
	/**
	 * 模板参考
	 */
	@NotEmpty(message = "模板参考必须填写")
	@ApiModelProperty(value = "模板参考")
	private String standardFile;
	/**
	 * 情况描述
	 */
	@NotEmpty(message = "情况描述必须填写")
	@ApiModelProperty(value = "情况描述")
	private String describeSituation;
	/**
	 * 相关附件
	 */
	@NotEmpty(message = "相关附件必须填写")
	@ApiModelProperty(value = "相关附件")
	private String fileJson;
}
