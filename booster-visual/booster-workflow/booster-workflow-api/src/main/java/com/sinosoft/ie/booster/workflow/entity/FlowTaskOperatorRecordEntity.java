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
 * 流程经办记录
 *
 * @author booster code generator
 * @since 2021-09-23
 */
@Data
@TableName("flow_task_operator_record")
@EqualsAndHashCode()
@ApiModel(value = "流程经办记录")
public class FlowTaskOperatorRecordEntity implements Serializable {

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
	 * 经办状态
	 */
	@NotNull(message = "经办状态必须填写")
	@ApiModelProperty(value = "经办状态")
	private Integer handleStatus;
	/**
	 * 经办人员
	 */
	@NotEmpty(message = "经办人员必须填写")
	@ApiModelProperty(value = "经办人员")
	private String handleId;
	/**
	 * 经办时间
	 */
	@NotNull(message = "经办时间必须填写")
	@ApiModelProperty(value = "经办时间")
	private LocalDateTime handleTime;
	/**
	 * 经办理由
	 */
	@NotEmpty(message = "经办理由必须填写")
	@ApiModelProperty(value = "经办理由")
	private String handleOpinion;
	/**
	 * 经办主键
	 */
	@NotEmpty(message = "经办主键必须填写")
	@ApiModelProperty(value = "经办主键")
	private Long taskOperatorId;
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
}
