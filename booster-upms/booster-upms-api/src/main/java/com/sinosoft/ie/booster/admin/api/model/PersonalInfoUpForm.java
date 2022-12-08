package com.sinosoft.ie.booster.admin.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 个人信息更新表单
 *
 * @author haocy
 * @since 2021/11/30
 */
@Data
@EqualsAndHashCode()
public class PersonalInfoUpForm {

	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名")
	private String username;

	/**
	 * 手机号
	 */
	@ApiModelProperty(value = "手机号")
	private String phone;

	/**
	 * 头像
	 */
	@ApiModelProperty(value = "头像地址")
	private String avatar;
}
