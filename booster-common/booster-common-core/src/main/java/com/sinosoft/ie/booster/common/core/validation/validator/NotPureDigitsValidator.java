package com.sinosoft.ie.booster.common.core.validation.validator;

import cn.hutool.core.lang.Validator;
import com.sinosoft.ie.booster.common.core.validation.NotPureDigits;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 校验不能是X位以下纯数字
 *
 * @author haocy
 * @since 2021-11-29
 */
public class NotPureDigitsValidator implements ConstraintValidator<NotPureDigits, String> {

	private int maxInteger;

	@Override
	public void initialize(NotPureDigits constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
		maxInteger = constraintAnnotation.maxInteger();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		String regex = String.format("\\d{1,%s}", maxInteger);
		return !Validator.isMatchRegex(regex, value);
	}
}
