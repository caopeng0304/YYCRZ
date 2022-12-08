package com.sinosoft.ie.booster.admin.api.entity;

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
 * 消息实例
 *
 * @author booster code generator
 * @since 2021-09-18
 */
@Data
@TableName("base_message")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "消息实例")
public class BaseMessageEntity extends BaseEntity {

	/**
	 * 自然主键
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "自然主键")
	private Long id;
	/**
	 * 类别
	 */
	@NotNull(message = "类别必须填写")
	@ApiModelProperty(value = "类别")
	private Integer type;
	/**
	 * 标题
	 */
	@NotEmpty(message = "标题必须填写")
	@ApiModelProperty(value = "标题")
	private String title;
	/**
	 * 正文
	 */
	@NotEmpty(message = "正文必须填写")
	@ApiModelProperty(value = "正文")
	private String bodyText;
	/**
	 * 优先
	 */
	@NotNull(message = "优先必须填写")
	@ApiModelProperty(value = "优先")
	private Integer priorityLevel;
	/**
	 * 收件用户
	 */
	@NotEmpty(message = "收件用户必须填写")
	@ApiModelProperty(value = "收件用户")
	private String toUserIds;
	/**
	 * 是否阅读
	 */
	@NotNull(message = "是否阅读必须填写")
	@ApiModelProperty(value = "是否阅读")
	private Integer isRead;
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
