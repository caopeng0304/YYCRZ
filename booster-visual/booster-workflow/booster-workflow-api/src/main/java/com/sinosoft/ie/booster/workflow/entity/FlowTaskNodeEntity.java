package com.sinosoft.ie.booster.workflow.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 流程节点
 *
 * @author booster code generator
 * @since 2021-09-23
 */
@Data
@TableName("flow_task_node")
@EqualsAndHashCode()
@ApiModel(value = "流程节点")
public class FlowTaskNodeEntity implements Serializable {

	/**
	 * 自然主键
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "自然主键")
	private Long id;
	/**
	 * 节点编号
	 */
	@NotEmpty(message = "节点编号必须填写")
	@ApiModelProperty(value = "节点编号")
	private String nodeCode;
	/**
	 * 节点名称
	 */
	@NotEmpty(message = "节点名称必须填写")
	@ApiModelProperty(value = "节点名称")
	private String nodeName;
	/**
	 * 节点类型
	 */
	@NotEmpty(message = "节点类型必须填写")
	@ApiModelProperty(value = "节点类型")
	private String nodeType;
	/**
	 * 节点属性Json
	 */
	@NotEmpty(message = "节点属性Json必须填写")
	@ApiModelProperty(value = "节点属性Json")
	private String nodePropertyJson;
	/**
	 * 上一节点
	 */
	@NotEmpty(message = "上一节点必须填写")
	@ApiModelProperty(value = "上一节点")
	private String nodeUp;
	/**
	 * 下一节点
	 */
	@NotEmpty(message = "下一节点必须填写")
	@ApiModelProperty(value = "下一节点")
	private String nodeNext;
	/**
	 * 任务主键
	 */
	@NotEmpty(message = "任务主键必须填写")
	@ApiModelProperty(value = "任务主键")
	private Long taskId;
	/**
	 * 状态
	 */
	@NotEmpty(message = "状态必须填写")
	@ApiModelProperty(value = "状态")
	private String state;
	/**
	 * 是否完成
	 */
	@NotNull(message = "是否完成必须填写")
	@ApiModelProperty(value = "是否完成")
	private Integer completion;
	/**
	 * 描述
	 */
	@NotEmpty(message = "描述必须填写")
	@ApiModelProperty(value = "描述")
	private String description;
	/**
	 * 排序码
	 */
	@NotNull(message = "排序码必须填写")
	@ApiModelProperty(value = "排序码")
	private Integer sort;
	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;
}
