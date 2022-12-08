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

import javax.validation.constraints.NotBlank;

/**
 * 系统社交登录账号表
 *
 * @author lengleng
 * @since 2018-08-16 21:30:41
 */
@Data
@TableName("sys_social_details")
@ApiModel(value = "第三方账号信息")
@EqualsAndHashCode(callSuper = true)
public class SysSocialDetailsEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 主鍵
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "主键")
	private Long id;
	/**
	 * 类型
	 */
	@NotBlank(message = "类型不能为空")
	@ApiModelProperty(value = "账号类型")
	private String type;
	/**
	 * 描述
	 */
	@ApiModelProperty(value = "描述")
	private String remark;
	/**
	 * appid
	 */
	@NotBlank(message = "账号不能为空")
	@ApiModelProperty(value = "appId")
	private String appId;
	/**
	 * app_secret
	 */
	@NotBlank(message = "密钥不能为空")
	@ApiModelProperty(value = "app secret")
	private String appSecret;
	/**
	 * 回调地址
	 */
	@ApiModelProperty(value = "回调地址")
	private String redirectUrl;
	/**
	 * 删除标记
	 */
	@TableLogic
	@ApiModelProperty(value = "删除标记,1:已删除,0:正常")
	private String delFlag;

}
