package com.sinosoft.ie.booster.common.core.validation;

import com.sinosoft.ie.booster.common.core.validation.validator.NotPureDigitsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 校验不能是X位以下纯数字
 *
 * @author haocy
 * @since 2021-11-29
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {NotPureDigitsValidator.class})
public @interface NotPureDigits {

	/**
	 * 要校验的最大位数
	 */
	int maxInteger();

	/**
	 * 校验出错时默认返回的消息
	 */
	String message() default "不能是X位以下纯数字";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * 同一个元素上指定多个该注解时使用
	 */
	@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
	@Retention(RUNTIME)
	@Documented
	public @interface List {
		NotPureDigits[] value();
	}
}
