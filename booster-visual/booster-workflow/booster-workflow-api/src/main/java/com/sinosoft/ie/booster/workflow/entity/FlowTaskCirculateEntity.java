package com.sinosoft.ie.booster.workflow.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 流程传阅
 *
 * @author booster code generator
 * @since 2021-09-23
 */
@Data
@TableName("flow_task_circulate")
@EqualsAndHashCode()
@ApiModel(value = "流程传阅")
public class FlowTaskCirculateEntity implements Serializable {

	/**
	 * 自然主键
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "自然主键")
	private Long id;
	/**
	 * 对象类型
	 */
	@NotEmpty(message = "对象类型必须填写")
	@ApiModelProperty(value = "对象类型")
	private String objectType;
	/**
	 * 对象主键
	 */
	@NotEmpty(message = "对象主键必须填写")
	@ApiModelProperty(value = "对象主键")
	private String objectId;
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
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;
}
