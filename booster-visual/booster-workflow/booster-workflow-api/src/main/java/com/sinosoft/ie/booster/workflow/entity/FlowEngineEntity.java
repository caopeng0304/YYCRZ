package com.sinosoft.ie.booster.workflow.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.sinosoft.ie.booster.common.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 流程引擎
 *
 * @author booster code generator
 * @since 2021-09-23
 */
@Data
@TableName("flow_engine")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "流程引擎")
public class FlowEngineEntity extends BaseEntity {

	/**
	 * 自然主键
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "自然主键")
	private Long id;
	/**
	 * 流程编码
	 */
	@NotEmpty(message = "流程编码必须填写")
	@ApiModelProperty(value = "流程编码")
	private String encode;
	/**
	 * 流程名称
	 */
	@NotEmpty(message = "流程名称必须填写")
	@ApiModelProperty(value = "流程名称")
	private String fullName;
	/**
	 * 流程类型
	 */
	@NotNull(message = "流程类型必须填写")
	@ApiModelProperty(value = "流程类型")
	private Integer type;
	/**
	 * 流程分类
	 */
	@NotEmpty(message = "流程分类必须填写")
	@ApiModelProperty(value = "流程分类")
	private String category;
	/**
	 * 表单分类
	 */
	@NotNull(message = "表单分类必须填写")
	@ApiModelProperty(value = "表单分类")
	private Integer formType;
	/**
	 * 流程表单
	 */
	@NotEmpty(message = "流程表单必须填写")
	@ApiModelProperty(value = "流程表单")
	private String form;
	/**
	 * 关联的表
	 */
	@NotEmpty(message = "关联的表必须填写")
	@ApiModelProperty(value = "关联的表")
	@JSONField(name = "tables")
	private String refTables;
	/**
	 * 可见类型
	 */
	@NotNull(message = "可见类型必须填写")
	@ApiModelProperty(value = "可见类型")
	private Integer visibleType;
	/**
	 * 图标
	 */
	@NotEmpty(message = "图标必须填写")
	@ApiModelProperty(value = "图标")
	private String icon;
	/**
	 * 图标背景色
	 */
	@NotEmpty(message = "图标背景色必须填写")
	@ApiModelProperty(value = "图标背景色")
	private String iconBackground;
	/**
	 * 流程版本
	 */
	@NotEmpty(message = "流程版本必须填写")
	@ApiModelProperty(value = "流程版本")
	private String version;
	/**
	 * 流程模板
	 */
	@NotEmpty(message = "流程模板必须填写")
	@ApiModelProperty(value = "流程模板")
	private String flowTemplateJson;
	/**
	 * 表单模板
	 */
	@NotEmpty(message = "表单模板必须填写")
	@ApiModelProperty(value = "表单模板")
	@TableField("form_template_json")
	private String formData;
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
