package com.sinosoft.ie.booster.visualdev.entity;

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
 * 可视化开发功能表
 *
 * @author booster code generator
 * @since 2021-09-14
 */
@Data
@TableName("base_visual_dev")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "可视化开发功能表")
public class BaseVisualDevEntity extends BaseEntity {

	/**
	 * 主键
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "主键")
	private Long id;
	/**
	 * 名称
	 */
	@NotEmpty(message = "名称必须填写")
	@ApiModelProperty(value = "名称")
	private String fullName;
	/**
	 * 编码
	 */
	@NotEmpty(message = "编码必须填写")
	@ApiModelProperty(value = "编码")
	private String encode;
	/**
	 * 描述
	 */
	@NotEmpty(message = "描述必须填写")
	@ApiModelProperty(value = "描述")
	private String description;
	/**
	 * 分类（数据字典）
	 */
	@NotEmpty(message = "分类（数据字典）必须填写")
	@ApiModelProperty(value = "分类（数据字典）")
	private String category;
	/**
	 * 状态(0-暂存（默认），1-发布)
	 */
	@NotNull(message = "状态(0-暂存（默认），1-发布)必须填写")
	@ApiModelProperty(value = "状态(0-暂存（默认），1-发布)")
	private Integer state;
	/**
	 * 类型(1-Web在线开发,2-移动在线开发,3-流程表单,4-Web表单,5-移动表单)
	 */
	@NotNull(message = "类型(1-Web在线开发,2-移动在线开发,3-流程表单,4-Web表单,5-移动表单)必须填写")
	@ApiModelProperty(value = "类型(1-Web在线开发,2-移动在线开发,3-流程表单,4-Web表单,5-移动表单)")
	private Integer type;
	/**
	 * db_link外键
	 */
	@NotEmpty(message = "db_link外键必须填写")
	@ApiModelProperty(value = "db_link外键")
	private Long dbLinkId;
	/**
	 * 关联的表
	 */
	@NotEmpty(message = "关联的表必须填写")
	@ApiModelProperty(value = "关联的表")
	@JSONField(name = "tables")
	private String refTables;
	/**
	 * 表单配置JSON
	 */
	@NotEmpty(message = "表单配置JSON必须填写")
	@ApiModelProperty(value = "表单配置JSON")
	private String formData;
	/**
	 * 列表配置JSON
	 */
	@NotEmpty(message = "列表配置JSON必须填写")
	@ApiModelProperty(value = "列表配置JSON")
	private String columnData;
	/**
	 * 前端模板JSON
	 */
	@NotEmpty(message = "前端模板JSON必须填写")
	@ApiModelProperty(value = "前端模板JSON")
	private String templateData;
	/**
	 * 排序码
	 */
	@NotNull(message = "排序码必须填写")
	@ApiModelProperty(value = "排序码")
	private Integer sort;
	/**
	 * 启用标志(0-默认，禁用，1-启用)
	 */
	@NotEmpty(message = "启用标志(0-默认，禁用，1-启用)必须填写")
	@ApiModelProperty(value = "启用标志(0-默认，禁用，1-启用)")
	private String enabledFlag;
	/**
	 * 删除标志
	 */
	@NotEmpty(message = "删除标志必须填写")
	@ApiModelProperty(value = "删除标志")
	@TableLogic
	private String delFlag;
}
