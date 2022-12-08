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
 * 流程经办
 *
 * @author booster code generator
 * @since 2021-09-23
 */
@Data
@TableName("flow_task_operator")
@EqualsAndHashCode()
@ApiModel(value = "流程经办")
public class FlowTaskOperatorEntity implements Serializable {

	/**
	 * 自然主键
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "自然主键")
	private Long id;
	/**
	 * 经办对象
	 */
	@NotEmpty(message = "经办对象必须填写")
	@ApiModelProperty(value = "经办对象")
	private String handleType;
	/**
	 * 经办主键
	 */
	@NotEmpty(message = "经办主键必须填写")
	@ApiModelProperty(value = "经办主键")
	private String handleId;
	/**
	 * 处理状态
	 */
	@NotNull(message = "处理状态必须填写")
	@ApiModelProperty(value = "处理状态")
	private Integer handleStatus;
	/**
	 * 处理时间
	 */
	@NotNull(message = "处理时间必须填写")
	@ApiModelProperty(value = "处理时间")
	private LocalDateTime handleTime;
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
	 * 节点主键
	 */
	@NotEmpty(message = "节点主键必须填写")
	@ApiModelProperty(value = "节点主键")
	private Long taskNodeId;
	/**
	 * 任务主键
	 */
	@NotEmpty(message = "任务主键必须填写")
	@ApiModelProperty(value = "任务主键")
	private Long taskId;
	/**
	 * 节点类型
	 */
	@NotEmpty(message = "节点类型必须填写")
	@ApiModelProperty(value = "节点类型")
	private String type;
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
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;
}
