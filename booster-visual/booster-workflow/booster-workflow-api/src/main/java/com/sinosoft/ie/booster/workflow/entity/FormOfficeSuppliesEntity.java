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
 * 领用办公用品申请表
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_office_supplies")
@EqualsAndHashCode()
@ApiModel(value = "领用办公用品申请表")
public class FormOfficeSuppliesEntity implements Serializable {

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
	 * 申请部门
	 */
	@NotEmpty(message = "申请部门必须填写")
	@ApiModelProperty(value = "申请部门")
	private String applyUser;
	/**
	 * 所属部门
	 */
	@NotEmpty(message = "所属部门必须填写")
	@ApiModelProperty(value = "所属部门")
	private String department;
	/**
	 * 领用仓库
	 */
	@NotEmpty(message = "领用仓库必须填写")
	@ApiModelProperty(value = "领用仓库")
	private String useStock;
	/**
	 * 用品分类
	 */
	@NotEmpty(message = "用品分类必须填写")
	@ApiModelProperty(value = "用品分类")
	private String classification;
	/**
	 * 用品名称
	 */
	@NotEmpty(message = "用品名称必须填写")
	@ApiModelProperty(value = "用品名称")
	private String articlesName;
	/**
	 * 用品数量
	 */
	@NotEmpty(message = "用品数量必须填写")
	@ApiModelProperty(value = "用品数量")
	private String articlesNum;
	/**
	 * 用品编号
	 */
	@NotEmpty(message = "用品编号必须填写")
	@ApiModelProperty(value = "用品编号")
	private String articlesId;
	/**
	 * 申请原因
	 */
	@NotEmpty(message = "申请原因必须填写")
	@ApiModelProperty(value = "申请原因")
	private String applyReasons;
	/**
	 * 申请时间
	 */
	@NotNull(message = "申请时间必须填写")
	@ApiModelProperty(value = "申请时间")
	private LocalDateTime applyDate;
}
