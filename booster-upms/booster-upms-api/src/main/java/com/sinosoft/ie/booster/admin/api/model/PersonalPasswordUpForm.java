package com.sinosoft.ie.booster.admin.api.model;

import com.sinosoft.ie.booster.common.core.constant.enums.OrderTypeEnum;
import com.sinosoft.ie.booster.common.core.validation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * 个人密码更新表单
 *
 * @author haocy
 * @since 2021/11/30
 */
@Data
@EqualsAndHashCode()
@NotEqualToField.List({
		@NotEqualToField(sourceField = "username", sourceFieldLabel = "用户名", targetField = "newpassword1", targetFieldLabel = "新密码"),
		@NotEqualToField(sourceField = "password", sourceFieldLabel = "原密码", targetField = "newpassword1", targetFieldLabel = "新密码"),
})
@MustEqualToField(sourceField = "newpassword1", sourceFieldLabel = "新密码", targetField = "newpassword2", targetFieldLabel = "确认密码")
public class PersonalPasswordUpForm {

	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名")
	private String username;

	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码")
	private String password;

	/**
	 * 新密码1
	 */
	@NotEmpty(message = "密码不能为空")
	@Size(min = 8, message = "密码长度不能小于8个字符")
	@Size(max = 20, message = "密码长度不能大于20个字符")
	@NotPureDigits(maxInteger = 9, message = "密码不能是9位以下纯数字")
	@NotContainWhiteSpace(message = "密码不能包含空格")
	@NotContinuousSameChar(minInteger = 4, message = "密码不能包含连续4个以及以上相同的字母或数字")
	@NotContinuousOrderChar(minInteger = 4, orderType = OrderTypeEnum.ASC, message = "密码不能包含连续4个以及以上升序的字母或数字")
	@MustComposeChar(minInteger = 2, message = "密码必须包含数字、字母、特殊字符中任意两种组合")
	private String newpassword1;

	/**
	 * 新密码2
	 */
	private String newpassword2;
}
