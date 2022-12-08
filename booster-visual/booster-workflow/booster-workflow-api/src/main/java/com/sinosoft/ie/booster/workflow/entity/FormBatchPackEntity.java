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
 * 批包装指令
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_batch_pack")
@EqualsAndHashCode()
@ApiModel(value = "批包装指令")
public class FormBatchPackEntity implements Serializable {

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
	 * 产品名称
	 */
	@NotEmpty(message = "产品名称必须填写")
	@ApiModelProperty(value = "产品名称")
	private String productName;
	/**
	 * 生产车间
	 */
	@NotEmpty(message = "生产车间必须填写")
	@ApiModelProperty(value = "生产车间")
	private String production;
	/**
	 * 编制人员
	 */
	@NotEmpty(message = "编制人员必须填写")
	@ApiModelProperty(value = "编制人员")
	private String compactor;
	/**
	 * 编制日期
	 */
	@NotNull(message = "编制日期必须填写")
	@ApiModelProperty(value = "编制日期")
	private LocalDateTime compactorDate;
	/**
	 * 产品规格
	 */
	@NotEmpty(message = "产品规格必须填写")
	@ApiModelProperty(value = "产品规格")
	private String standard;
	/**
	 * 入库序号
	 */
	@NotEmpty(message = "入库序号必须填写")
	@ApiModelProperty(value = "入库序号")
	private String warehouseNo;
	/**
	 * 批产数量
	 */
	@NotEmpty(message = "批产数量必须填写")
	@ApiModelProperty(value = "批产数量")
	private String productionQuty;
	/**
	 * 操作日期
	 */
	@NotNull(message = "操作日期必须填写")
	@ApiModelProperty(value = "操作日期")
	private LocalDateTime operationDate;
	/**
	 * 工艺规程
	 */
	@NotEmpty(message = "工艺规程必须填写")
	@ApiModelProperty(value = "工艺规程")
	private String regulations;
	/**
	 * 包装规格
	 */
	@NotEmpty(message = "包装规格必须填写")
	@ApiModelProperty(value = "包装规格")
	private String packing;
	/**
	 * 备注
	 */
	@NotEmpty(message = "备注必须填写")
	@ApiModelProperty(value = "备注")
	private String description;
}
