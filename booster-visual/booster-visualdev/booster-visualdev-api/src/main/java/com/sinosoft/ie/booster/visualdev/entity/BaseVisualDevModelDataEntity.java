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
 * 0代码功能数据表
 *
 * @author booster code generator
 * @since 2021-09-14
 */
@Data
@TableName("base_visual_dev_model_data")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "0代码功能数据表")
public class BaseVisualDevModelDataEntity extends BaseEntity {

	/**
	 * 主键
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "主键")
	private Long id;
	/**
	 * 功能ID
	 */
	@NotEmpty(message = "功能ID必须填写")
	@ApiModelProperty(value = "功能ID")
	private Long visualDevId;
	/**
	 * 区分主子表
	 */
	@NotEmpty(message = "区分主子表必须填写")
	@ApiModelProperty(value = "区分主子表")
	private Long parentId;
	/**
	 * 数据包
	 */
	@NotEmpty(message = "数据包必须填写")
	@ApiModelProperty(value = "数据包")
	private String data;
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
