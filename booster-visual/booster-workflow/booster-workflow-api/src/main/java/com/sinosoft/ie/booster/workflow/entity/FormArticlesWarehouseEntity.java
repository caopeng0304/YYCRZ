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
 * 用品入库申请表
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_articles_warehouse")
@EqualsAndHashCode()
@ApiModel(value = "用品入库申请表")
public class FormArticlesWarehouseEntity implements Serializable {

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
	 * 所属部门
	 */
	@NotEmpty(message = "所属部门必须填写")
	@ApiModelProperty(value = "所属部门")
	private String department;
	/**
	 * 用品库存
	 */
	@NotEmpty(message = "用品库存必须填写")
	@ApiModelProperty(value = "用品库存")
	private String articles;
	/**
	 * 用品分类
	 */
	@NotEmpty(message = "用品分类必须填写")
	@ApiModelProperty(value = "用品分类")
	private String classification;
	/**
	 * 用品编号
	 */
	@NotEmpty(message = "用品编号必须填写")
	@ApiModelProperty(value = "用品编号")
	private String articlesId;
	/**
	 * 单位
	 */
	@NotEmpty(message = "单位必须填写")
	@ApiModelProperty(value = "单位")
	private String company;
	/**
	 * 数量
	 */
	@NotEmpty(message = "数量必须填写")
	@ApiModelProperty(value = "数量")
	private String estimatePeople;
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
