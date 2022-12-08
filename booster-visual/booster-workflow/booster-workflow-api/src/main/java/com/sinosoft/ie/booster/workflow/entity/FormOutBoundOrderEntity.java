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
 * 出库单
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_out_bound_order")
@EqualsAndHashCode()
@ApiModel(value = "出库单")
public class FormOutBoundOrderEntity implements Serializable {

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
	 * 客户名称
	 */
	@NotEmpty(message = "客户名称必须填写")
	@ApiModelProperty(value = "客户名称")
	private String customerName;
	/**
	 * 仓库
	 */
	@NotEmpty(message = "仓库必须填写")
	@ApiModelProperty(value = "仓库")
	private String warehouse;
	/**
	 * 仓库人
	 */
	@NotEmpty(message = "仓库人必须填写")
	@ApiModelProperty(value = "仓库人")
	private String outStorage;
	/**
	 * 业务人员
	 */
	@NotEmpty(message = "业务人员必须填写")
	@ApiModelProperty(value = "业务人员")
	private String businessPeople;
	/**
	 * 业务类型
	 */
	@NotEmpty(message = "业务类型必须填写")
	@ApiModelProperty(value = "业务类型")
	private String businessType;
	/**
	 * 出库日期
	 */
	@NotNull(message = "出库日期必须填写")
	@ApiModelProperty(value = "出库日期")
	private LocalDateTime outBoundDate;
	/**
	 * 备注
	 */
	@NotEmpty(message = "备注必须填写")
	@ApiModelProperty(value = "备注")
	private String description;
}
