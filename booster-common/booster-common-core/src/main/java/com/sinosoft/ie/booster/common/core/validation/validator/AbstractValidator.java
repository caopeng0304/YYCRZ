package com.sinosoft.ie.booster.common.core.validation.validator;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 封装各校验器共用的方法
 *
 * @author haocy
 * @since 2021-12-01
 */
public abstract class AbstractValidator {

	/**
	 * 获取源、目标字段值
	 *
	 * @param obj         校验对象
	 * @param sourceField 源字段
	 * @param targetField 目标字段
	 * @return 源、目标字段与值的Map
	 */
	public Map<String, String> getFieldValueMap(Object obj, String sourceField, String targetField) {
		Map<String, String> fieldValMap = new HashMap<>();
		Class<?> clz = obj.getClass();
		try {
			PropertyDescriptor sourceFieldDescriptor = new PropertyDescriptor(sourceField, clz);
			PropertyDescriptor targetFieldDescriptor = new PropertyDescriptor(targetField, clz);
			fieldValMap.put(sourceField, Optional.ofNullable(sourceFieldDescriptor.getReadMethod().invoke(obj)).map(Object::toString).orElse(null));
			fieldValMap.put(targetField, Optional.ofNullable(targetFieldDescriptor.getReadMethod().invoke(obj)).map(Object::toString).orElse(null));
		} catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
			fieldValMap.put(sourceField, null);
			fieldValMap.put(targetField, null);
		}
		return fieldValMap;
	}
}
