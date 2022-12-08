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
 * 邮件配置
 *
 * @author booster code generator
 * @since 2021-10-09
 */
@Data
@TableName("ext_email_config")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "邮件配置")
public class ExtEmailConfigEntity extends BaseEntity {

	/**
	 * 自然主键
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "自然主键")
	private Long id;
	/**
	 * POP3服务
	 */
	@NotEmpty(message = "POP3服务必须填写")
	@ApiModelProperty(value = "POP3服务")
	private String pop3Host;
	/**
	 * POP3端口
	 */
	@NotNull(message = "POP3端口必须填写")
	@ApiModelProperty(value = "POP3端口")
	private Integer pop3Port;
	/**
	 * SMTP服务
	 */
	@NotEmpty(message = "SMTP服务必须填写")
	@ApiModelProperty(value = "SMTP服务")
	private String smtpHost;
	/**
	 * SMTP端口
	 */
	@NotNull(message = "SMTP端口必须填写")
	@ApiModelProperty(value = "SMTP端口")
	private Integer smtpPort;
	/**
	 * 账户
	 */
	@NotEmpty(message = "账户必须填写")
	@ApiModelProperty(value = "账户")
	private String account;
	/**
	 * 密码
	 */
	@NotEmpty(message = "密码必须填写")
	@ApiModelProperty(value = "密码")
	private String password;
	/**
	 * SSL登录
	 */
	@NotNull(message = "SSL登录必须填写")
	@ApiModelProperty(value = "SSL登录")
	private Integer emailSsl;
	/**
	 * 发件人名称
	 */
	@NotEmpty(message = "发件人名称必须填写")
	@ApiModelProperty(value = "发件人名称")
	private String senderName;
	/**
	 * 我的文件夹
	 */
	@NotEmpty(message = "我的文件夹必须填写")
	@ApiModelProperty(value = "我的文件夹")
	private String folderJson;
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
