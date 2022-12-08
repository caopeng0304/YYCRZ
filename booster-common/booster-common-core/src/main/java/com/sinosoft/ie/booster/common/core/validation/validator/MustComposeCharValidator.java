package com.sinosoft.ie.booster.common.core.validation.validator;

import cn.hutool.core.lang.Validator;
import com.sinosoft.ie.booster.common.core.validation.MustComposeChar;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 校验必须包含数字、字母、特殊字符中任意n种组合
 *
 * @author haocy
 * @since 2021-11-30
 */
public class MustComposeCharValidator implements ConstraintValidator<MustComposeChar, String> {

	private int minInteger;

	/**
	 * 数字
	 */
	private static final String REG_NUMBER = ".*\\d+.*";

	/**
	 * 字母
	 */
	private static final String REG_CHAR = ".*[a-zA-Z]+.*";

	/**
	 * 特殊字符
	 */
	private static final String REG_SYMBOL = ".*[~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"]+.*";

	@Override
	public void initialize(MustComposeChar constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
		minInteger = constraintAnnotation.minInteger();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		int i = 0;
		if (Validator.isMatchRegex(REG_NUMBER, value)) {
			i++;
		}
		if (Validator.isMatchRegex(REG_CHAR, value)) {
			i++;
		}
		if (Validator.isMatchRegex(REG_SYMBOL, value)) {
			i++;
		}
		return i >= minInteger;
	}
}
