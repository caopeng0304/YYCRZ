package com.sinosoft.ie.booster.common.core.validation.validator;

import cn.hutool.core.lang.Validator;
import com.sinosoft.ie.booster.common.core.validation.NotContainWhiteSpace;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 校验不能包含空格
 *
 * @author haocy
 * @since 2021-11-29
 */
public class NotContainWhiteSpaceValidator implements ConstraintValidator<NotContainWhiteSpace, String> {

	@Override
	public void initialize(NotContainWhiteSpace constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		String regex = "\\s+";
		return !Validator.isMatchRegex(regex, value);
	}
}
