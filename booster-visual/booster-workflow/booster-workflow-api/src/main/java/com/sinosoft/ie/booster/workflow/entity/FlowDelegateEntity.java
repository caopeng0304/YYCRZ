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
 * 流程委托
 *
 * @author booster code generator
 * @since 2021-09-23
 */
@Data
@TableName("flow_delegate")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "流程委托")
public class FlowDelegateEntity extends BaseEntity {

	/**
	 * 自然主键
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "自然主键")
	private Long id;
	/**
	 * 被委托人
	 */
	@NotEmpty(message = "被委托人必须填写")
	@ApiModelProperty(value = "被委托人")
	private String toUserId;
	/**
	 * 被委托人
	 */
	@NotEmpty(message = "被委托人必须填写")
	@ApiModelProperty(value = "被委托人")
	private String toUserName;
	/**
	 * 委托流程Id
	 */
	@NotEmpty(message = "委托流程Id必须填写")
	@ApiModelProperty(value = "委托流程Id")
	private Long flowId;
	/**
	 * 委托流程名称
	 */
	@NotEmpty(message = "委托流程名称必须填写")
	@ApiModelProperty(value = "委托流程名称")
	private String flowName;
	/**
	 * 流程分类
	 */
	@NotEmpty(message = "流程分类必须填写")
	@ApiModelProperty(value = "流程分类")
	private String flowCategory;
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
	 * 描述
	 */
	@NotEmpty(message = "描述必须填写")
	@ApiModelProperty(value = "描述")
	private String description;
	/**
	 * 排序
	 */
	@NotNull(message = "排序必须填写")
	@ApiModelProperty(value = "排序")
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
