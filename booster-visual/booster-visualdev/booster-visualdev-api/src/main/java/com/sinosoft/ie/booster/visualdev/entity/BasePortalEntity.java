package com.sinosoft.ie.booster.visualdev.entity;

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

/**
 * 门户表
 *
 * @author booster code generator
 * @since 2021-09-14
 */
@Data
@TableName("base_portal")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "门户表")
public class BasePortalEntity extends BaseEntity {

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
	 * 表单配置JSON
	 */
	@NotEmpty(message = "表单配置JSON必须填写")
	@ApiModelProperty(value = "表单配置JSON")
	private String formData;
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
