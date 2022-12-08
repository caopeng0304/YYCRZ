package com.sinosoft.ie.booster.common.core.validation.validator;

import cn.hutool.core.lang.Validator;
import com.sinosoft.ie.booster.common.core.validation.NotContinuousSameChar;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 校验不能包含连续X个以及以上相同的字母或数字
 *
 * @author haocy
 * @since 2021-11-29
 */
public class NotContinuousSameCharValidator implements ConstraintValidator<NotContinuousSameChar, String> {

	private int minInteger;

	@Override
	public void initialize(NotContinuousSameChar constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
		minInteger = constraintAnnotation.minInteger();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		String regex = String.format("([0-9a-zA-Z])\\1{%s}", minInteger - 1);
		return !Validator.isMatchRegex(regex, value);
	}
}
