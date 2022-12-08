package com.sinosoft.ie.booster.common.database.mapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据结构属性注解
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2020/3/25
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
public @interface MappingField {

	/**
	 * 属性映射名称
	 *
	 * @return {@link String}
	 **/
	String value() default "";

}
