package com.sinosoft.ie.booster.common.core.validation;

import com.sinosoft.ie.booster.common.core.validation.validator.NotEqualToFieldValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 校验目标字段值不能与源字段值相同
 *
 * @author haocy
 * @since 2021-11-29
 */
@Target({ElementType.TYPE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {NotEqualToFieldValidator.class})
public @interface NotEqualToField {

	/**
	 * 对应源字段
	 */
	String sourceField();

	/**
	 * 对应源字段标签
	 */
	String sourceFieldLabel();

	/**
	 * 对应目标字段
	 */
	String targetField();

	/**
	 * 对应目标字段标签
	 */
	String targetFieldLabel();

	/**
	 * 校验出错时默认返回的消息
	 */
	String message() default "不能与指定的字段内容相同";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * 同一个元素上指定多个该注解时使用
	 */
	@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
	@Retention(RUNTIME)
	@Documented
	public @interface List {
		NotEqualToField[] value();
	}
}
