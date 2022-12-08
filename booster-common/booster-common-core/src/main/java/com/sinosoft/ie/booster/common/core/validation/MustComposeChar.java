package com.sinosoft.ie.booster.common.core.validation;

import com.sinosoft.ie.booster.common.core.validation.validator.MustComposeCharValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 校验必须包含数字、字母、特殊字符中任意n种组合
 *
 * @author haocy
 * @since 2021-11-30
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {MustComposeCharValidator.class})
public @interface MustComposeChar {

	/**
	 * 要校验的最少组合项
	 */
	int minInteger();

	/**
	 * 校验出错时默认返回的消息
	 */
	String message() default "必须包含数字、字母、特殊字符中任意n种组合";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * 同一个元素上指定多个该注解时使用
	 */
	@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
	@Retention(RUNTIME)
	@Documented
	public @interface List {
		MustComposeChar[] value();
	}
}
