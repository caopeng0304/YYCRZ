package com.sinosoft.ie.booster.workflow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sinosoft.ie.booster.common.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 流程任务
 *
 * @author booster code generator
 * @since 2021-09-23
 */
@Data
@TableName("flow_task")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "流程任务")
public class FlowTaskEntity extends BaseEntity {

	/**
	 * 自然主键
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "自然主键")
	private Long id;
	/**
	 * 实例进程
	 */
	@NotEmpty(message = "实例进程必须填写")
	@ApiModelProperty(value = "实例进程")
	private Long processId;
	/**
	 * 任务编号
	 */
	@NotEmpty(message = "任务编号必须填写")
	@ApiModelProperty(value = "任务编号")
	private String encode;
	/**
	 * 任务标题
	 */
	@NotEmpty(message = "任务标题必须填写")
	@ApiModelProperty(value = "任务标题")
	private String fullName;
	/**
	 * 紧急程度
	 */
	@NotNull(message = "紧急程度必须填写")
	@ApiModelProperty(value = "紧急程度")
	private Integer flowUrgent;
	/**
	 * 流程主键
	 */
	@NotEmpty(message = "流程主键必须填写")
	@ApiModelProperty(value = "流程主键")
	private Long flowId;
	/**
	 * 流程编号
	 */
	@NotEmpty(message = "流程编号必须填写")
	@ApiModelProperty(value = "流程编号")
	private String flowCode;
	/**
	 * 流程名称
	 */
	@NotEmpty(message = "流程名称必须填写")
	@ApiModelProperty(value = "流程名称")
	private String flowName;
	/**
	 * 流程分类
	 */
	@NotNull(message = "流程分类必须填写")
	@ApiModelProperty(value = "流程分类")
	private Integer flowType;
	/**
	 * 流程类型
	 */
	@NotEmpty(message = "流程类型必须填写")
	@ApiModelProperty(value = "流程类型")
	private String flowCategory;
	/**
	 * 流程表单
	 */
	@NotEmpty(message = "流程表单必须填写")
	@ApiModelProperty(value = "流程表单")
	private String flowForm;
	/**
	 * 表单内容
	 */
	@NotEmpty(message = "表单内容必须填写")
	@ApiModelProperty(value = "表单内容")
	private String flowFormContentJson;
	/**
	 * 流程模板
	 */
	@NotEmpty(message = "流程模板必须填写")
	@ApiModelProperty(value = "流程模板")
	private String flowTemplateJson;
	/**
	 * 流程版本
	 */
	@NotEmpty(message = "流程版本必须填写")
	@ApiModelProperty(value = "流程版本")
	private String flowVersion;
	/**
	 * 开始时间
	 */
	@NotNull(message = "开始时间必须填写")
	@ApiModelProperty(value = "开始时间")
	private LocalDateTime startTime;
	/**
	 * 结束时间
	 */
	@NotNull(message = "结束时间必须填写")
	@ApiModelProperty(value = "结束时间")
	private LocalDateTime endTime;
	/**
	 * 当前步骤
	 */
	@NotEmpty(message = "当前步骤必须填写")
	@ApiModelProperty(value = "当前步骤")
	private String thisStep;
	/**
	 * 当前步骤Id
	 */
	@NotEmpty(message = "当前步骤Id必须填写")
	@ApiModelProperty(value = "当前步骤Id")
	private String thisStepId;
	/**
	 * 重要等级
	 */
	@NotEmpty(message = "重要等级必须填写")
	@ApiModelProperty(value = "重要等级")
	private String grade;
	/**
	 * 任务状态
	 */
	@NotNull(message = "任务状态必须填写")
	@ApiModelProperty(value = "任务状态")
	private Integer status;
	/**
	 * 完成情况
	 */
	@NotNull(message = "完成情况必须填写")
	@ApiModelProperty(value = "完成情况")
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
	 * 有效标志
	 */
	@NotEmpty(message = "有效标志必须填写")
	@ApiModelProperty(value = "有效标志")
	private String enabledFlag;
	/**
	 * 删除标志
	 */
	@NotEmpty(message = "删除标志必须填写")
	@ApiModelProperty(value = "删除标志")
	@TableLogic
	private String delFlag;
}
