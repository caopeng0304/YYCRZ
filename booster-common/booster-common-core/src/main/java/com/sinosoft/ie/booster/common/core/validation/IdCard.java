package com.sinosoft.ie.booster.common.core.validation;

import com.sinosoft.ie.booster.common.core.validation.validator.IdCardValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 校验是否符合身份证号码格式
 *
 * @author haocy
 * @since 2021-08-19
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {IdCardValidator.class})
public @interface IdCard {

	/**
	 * 校验出错时默认返回的消息
	 */
	String message() default "身份证号码格式错误";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * 同一个元素上指定多个该注解时使用
	 */
	@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
	@Retention(RUNTIME)
	@Documented
	public @interface List {
		IdCard[] value();
	}
}
