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
 * 数据接口
 *
 * @author booster code generator
 * @since 2021-09-14
 */
@Data
@TableName("base_data_interface")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "数据接口")
public class BaseDataInterfaceEntity extends BaseEntity {

	/**
	 * 主键ID
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "主键ID")
	private Long id;
	/**
	 * 接口名称
	 */
	@NotEmpty(message = "接口名称必须填写")
	@ApiModelProperty(value = "接口名称")
	private String fullName;
	/**
	 * 接口编码
	 */
	@NotEmpty(message = "接口编码必须填写")
	@ApiModelProperty(value = "接口编码")
	private String encode;
	/**
	 * 描述或说明
	 */
	@NotEmpty(message = "描述或说明必须填写")
	@ApiModelProperty(value = "描述或说明")
	private String description;
	/**
	 * 分组ID
	 */
	@NotEmpty(message = "分组ID必须填写")
	@ApiModelProperty(value = "分组ID")
	private String categoryId;
	/**
	 * 接口路径
	 */
	@NotEmpty(message = "接口路径必须填写")
	@ApiModelProperty(value = "接口路径")
	private String path;
	/**
	 * 请求方式
	 */
	@NotEmpty(message = "请求方式必须填写")
	@ApiModelProperty(value = "请求方式")
	private String requestMethod;
	/**
	 * 返回类型
	 */
	@NotEmpty(message = "返回类型必须填写")
	@ApiModelProperty(value = "返回类型")
	private String responseType;
	/**
	 * 查询语句
	 */
	@NotEmpty(message = "查询语句必须填写")
	@ApiModelProperty(value = "查询语句")
	private String query;
	/**
	 * 请求参数JSON
	 */
	@NotEmpty(message = "请求参数JSON必须填写")
	@ApiModelProperty(value = "请求参数JSON")
	private String requestParameters;
	/**
	 * 返回参数JSON
	 */
	@NotEmpty(message = "返回参数JSON必须填写")
	@ApiModelProperty(value = "返回参数JSON")
	private String responseParameters;
	/**
	 * 数据源id
	 */
	@NotEmpty(message = "数据源id必须填写")
	@ApiModelProperty(value = "数据源id")
	private Long dbLinkId;
	/**
	 * 数据类型(1-动态数据SQL查询，2-静态数据)
	 */
	@NotNull(message = "数据类型(1-动态数据SQL查询，2-静态数据)必须填写")
	@ApiModelProperty(value = "数据类型(1-动态数据SQL查询，2-静态数据)")
	private Integer dataType;
	/**
	 * 排序码(默认0)
	 */
	@NotNull(message = "排序码(默认0)必须填写")
	@ApiModelProperty(value = "排序码(默认0)")
	private Integer sort;
	/**
	 * 启用标志(0-默认，禁用，1-启用)
	 */
	@NotEmpty(message = "启用标志(0-默认，禁用，1-启用)必须填写")
	@ApiModelProperty(value = "启用标志(0-默认，禁用，1-启用)")
	private String enabledFlag;
	/**
	 * 删除标志(默认0)
	 */
	@NotEmpty(message = "删除标志(默认0)必须填写")
	@ApiModelProperty(value = "删除标志(默认0)")
	@TableLogic
	private String delFlag;
}
