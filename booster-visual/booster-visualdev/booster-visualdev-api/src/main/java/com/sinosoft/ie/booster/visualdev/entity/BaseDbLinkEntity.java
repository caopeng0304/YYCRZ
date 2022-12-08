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
 * 数据连接
 *
 * @author booster code generator
 * @since 2021-09-14
 */
@Data
@TableName("base_db_link")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "数据连接")
public class BaseDbLinkEntity extends BaseEntity {

	/**
	 * 自然主键
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "自然主键")
	private Long id;
	/**
	 * 连接名称
	 */
	@NotEmpty(message = "连接名称必须填写")
	@ApiModelProperty(value = "连接名称")
	private String fullName;
	/**
	 * 描述
	 */
	@NotEmpty(message = "描述必须填写")
	@ApiModelProperty(value = "描述")
	private String description;
	/**
	 * 连接驱动
	 */
	@NotEmpty(message = "连接驱动必须填写")
	@ApiModelProperty(value = "连接驱动")
	private String dbType;
	/**
	 * 主机地址
	 */
	@NotEmpty(message = "主机地址必须填写")
	@ApiModelProperty(value = "主机地址")
	private String host;
	/**
	 * 端口
	 */
	@NotNull(message = "端口必须填写")
	@ApiModelProperty(value = "端口")
	private Integer port;
	/**
	 * 用户
	 */
	@NotEmpty(message = "用户必须填写")
	@ApiModelProperty(value = "用户")
	private String userName;
	/**
	 * 密码
	 */
	@NotEmpty(message = "密码必须填写")
	@ApiModelProperty(value = "密码")
	private String password;
	/**
	 * 服务名称
	 */
	@NotEmpty(message = "服务名称必须填写")
	@ApiModelProperty(value = "服务名称")
	private String serviceName;
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
