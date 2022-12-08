package com.sinosoft.ie.booster.common.encrypt.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sinosoft.ie.booster.common.encrypt.config.SensitiveSerializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 段鹏宇
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@JacksonAnnotationsInside
@JsonSerialize(
		using = SensitiveSerializer.class
)
public @interface FieldSensitive {

	SensitiveType sensitiveType() default SensitiveType.ChineseName;

	/**
	 * 前置不需要打码的长度
	 */
	int prefixNoMaskLen() default 0;

	/**
	 * 后置不需要打码的长度
	 */
	int suffixNoMaskLen() default 0;

	/**
	 * 用什么打码
	 */
	String maskStr() default "*";
}
