package com.sinosoft.ie.booster.admin.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 消息接收
 *
 * @author booster code generator
 * @since 2021-09-18
 */
@Data
@TableName("base_message_receive")
@EqualsAndHashCode()
@ApiModel(value = "消息接收")
public class BaseMessageReceiveEntity {

	/**
	 * 自然主键
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "自然主键")
	private Long id;
	/**
	 * 消息主键
	 */
	@NotEmpty(message = "消息主键必须填写")
	@ApiModelProperty(value = "消息主键")
	private Long messageId;
	/**
	 * 用户主键
	 */
	@NotEmpty(message = "用户主键必须填写")
	@ApiModelProperty(value = "用户主键")
	private String userId;
	/**
	 * 是否阅读
	 */
	@NotNull(message = "是否阅读必须填写")
	@ApiModelProperty(value = "是否阅读")
	private Integer isRead;
	/**
	 * 阅读时间
	 */
	@NotNull(message = "阅读时间必须填写")
	@ApiModelProperty(value = "阅读时间")
	private LocalDateTime readTime;
	/**
	 * 阅读次数
	 */
	@NotNull(message = "阅读次数必须填写")
	@ApiModelProperty(value = "阅读次数")
	private Integer readCount;
}
