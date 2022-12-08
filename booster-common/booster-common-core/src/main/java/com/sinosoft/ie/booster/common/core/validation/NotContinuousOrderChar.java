package com.sinosoft.ie.booster.common.core.validation;

import com.sinosoft.ie.booster.common.core.constant.enums.OrderTypeEnum;
import com.sinosoft.ie.booster.common.core.validation.validator.NotContinuousOrderCharValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 校验不能包含连续X个以及以上有顺序的字母或数字
 *
 * @author haocy
 * @since 2021-11-30
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {NotContinuousOrderCharValidator.class})
public @interface NotContinuousOrderChar {

	/**
	 * 要校验的最小位数
	 */
	int minInteger();

	/**
	 * 排序类型
	 */
	OrderTypeEnum orderType();

	/**
	 * 校验出错时默认返回的消息
	 */
	String message() default "不能包含连续X个以及以上有顺序的字母或数字";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * 同一个元素上指定多个该注解时使用
	 */
	@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
	@Retention(RUNTIME)
	@Documented
	public @interface List {
		NotContinuousOrderChar[] value();
	}
}
