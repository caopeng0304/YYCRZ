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
 * 邮件发送
 *
 * @author booster code generator
 * @since 2021-10-09
 */
@Data
@TableName("ext_email_send")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "邮件发送")
public class ExtEmailSendEntity extends BaseEntity {

	/**
	 * 自然主键
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "自然主键")
	private Long id;
	/**
	 * 类型
	 */
	@NotNull(message = "类型必须填写")
	@ApiModelProperty(value = "类型")
	private Integer type;
	/**
	 * 发件人
	 */
	@NotEmpty(message = "发件人必须填写")
	@ApiModelProperty(value = "发件人")
	private String sender;
	/**
	 * 收件人
	 */
	@NotEmpty(message = "收件人必须填写")
	@ApiModelProperty(value = "收件人")
	private String recipient;
	/**
	 * 抄送人
	 */
	@NotEmpty(message = "抄送人必须填写")
	@ApiModelProperty(value = "抄送人")
	private String cc;
	/**
	 * 密送人
	 */
	@NotEmpty(message = "密送人必须填写")
	@ApiModelProperty(value = "密送人")
	private String bcc;
	/**
	 * 颜色
	 */
	@NotEmpty(message = "颜色必须填写")
	@ApiModelProperty(value = "颜色")
	private String colour;
	/**
	 * 主题
	 */
	@NotEmpty(message = "主题必须填写")
	@ApiModelProperty(value = "主题")
	private String subject;
	/**
	 * 正文
	 */
	@NotEmpty(message = "正文必须填写")
	@ApiModelProperty(value = "正文")
	private String bodyText;
	/**
	 * 附件
	 */
	@NotEmpty(message = "附件必须填写")
	@ApiModelProperty(value = "附件")
	private String attachment;
	/**
	 * 状态
	 */
	@NotNull(message = "状态必须填写")
	@ApiModelProperty(value = "状态")
	private Integer state;
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
