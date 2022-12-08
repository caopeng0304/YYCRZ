package com.sinosoft.ie.booster.common.database.mapping;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * FieldMethod
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on  2020/3/25
 */
public class FieldMethod {

	/**
	 * 属性
	 */
	private Field field;

	/**
	 * 方法
	 */
	private Method method;

	/**
	 * Getter method for property <tt>field</tt>.
	 *
	 * @return property value of field
	 */
	public Field getField() {
		return field;
	}

	/**
	 * Setter method for property <tt>field</tt>.
	 *
	 * @param field value to be assigned to property field
	 */
	public void setField(Field field) {
		this.field = field;
	}

	/**
	 * Getter method for property <tt>method</tt>.
	 *
	 * @return property value of method
	 */
	public Method getMethod() {
		return method;
	}

	/**
	 * Setter method for property <tt>method</tt>.
	 *
	 * @param method value to be assigned to property method
	 */
	public void setMethod(Method method) {
		this.method = method;
	}

}
