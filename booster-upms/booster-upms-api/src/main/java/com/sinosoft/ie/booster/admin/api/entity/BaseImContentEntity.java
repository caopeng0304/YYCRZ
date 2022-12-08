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
 * 聊天内容
 *
 * @author booster code generator
 * @since 2021-09-18
 */
@Data
@TableName("base_im_content")
@EqualsAndHashCode()
@ApiModel(value = "聊天内容")
public class BaseImContentEntity {

	/**
	 * 自然主键
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "自然主键")
	private Long id;
	/**
	 * 发送者
	 */
	@NotEmpty(message = "发送者必须填写")
	@ApiModelProperty(value = "发送者")
	private String sendUserId;
	/**
	 * 发送时间
	 */
	@NotNull(message = "发送时间必须填写")
	@ApiModelProperty(value = "发送时间")
	private LocalDateTime sendTime;
	/**
	 * 接收者
	 */
	@NotEmpty(message = "接收者必须填写")
	@ApiModelProperty(value = "接收者")
	private String receiveUserId;
	/**
	 * 接收时间
	 */
	@NotNull(message = "接收时间必须填写")
	@ApiModelProperty(value = "接收时间")
	private LocalDateTime receiveTime;
	/**
	 * 内容
	 */
	@NotEmpty(message = "内容必须填写")
	@ApiModelProperty(value = "内容")
	private String content;
	/**
	 * 内容
	 */
	@NotEmpty(message = "内容必须填写")
	@ApiModelProperty(value = "内容")
	private String contentType;
	/**
	 * 状态
	 */
	@NotNull(message = "状态必须填写")
	@ApiModelProperty(value = "状态")
	private Integer state;
}
