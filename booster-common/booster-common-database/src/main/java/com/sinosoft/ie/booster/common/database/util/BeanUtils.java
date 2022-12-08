package com.sinosoft.ie.booster.common.database.util;

import org.apache.commons.lang3.StringEscapeUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static com.sinosoft.ie.booster.common.database.util.StringUtils.replaceBlank;

/**
 * BeanUtils
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/7/5 19:58
 */
public class BeanUtils {
	/**
	 * 转义bean中所有属性为字符串的
	 *
	 * @param bean {@link Object}
	 */
	public static void beanAttributeValueEscapeXml(Object bean) {
		try {
			if (bean != null) {
				//获取所有的字段包括public,private,protected,private
				Field[] fields = bean.getClass().getDeclaredFields();
				for (Field f : fields) {
					if ("java.lang.String".equals(f.getType().getName())) {
						//获取字段名
						String key = f.getName();
						Object value = getFieldValue(bean, key);

						if (value == null) {
							continue;
						}
						setFieldValue(bean, key, StringEscapeUtils.escapeXml(value.toString()));
					}
				}
			}
		} catch (Exception e) {
			throw ExceptionUtils.mpe(e);
		}
	}

	/**
	 * bean 中所有属性为字符串的进行\n\t\s处理
	 *
	 * @param bean {@link Object}
	 */
	public static void beanAttributeValueReplaceBlank(Object bean) {
		try {
			if (bean != null) {
				//获取所有的字段包括public,private,protected,private
				Field[] fields = bean.getClass().getDeclaredFields();
				for (Field f : fields) {
					if ("java.lang.String".equals(f.getType().getName())) {
						//获取字段名
						String key = f.getName();
						Object value = getFieldValue(bean, key);
						if (value == null) {
							continue;
						}
						setFieldValue(bean, key, replaceBlank(value.toString()));
					}
				}
			}
		} catch (Exception e) {
			throw ExceptionUtils.mpe(e);
		}
	}

	/**
	 * 去掉bean中所有属性为字符串的前后空格
	 *
	 * @param bean {@link Object}
	 */
	public static void beanAttributeValueTrim(Object bean) {
		try {
			if (bean != null) {
				//获取所有的字段包括public,private,protected,private
				Field[] fields = bean.getClass().getDeclaredFields();
				for (Field f : fields) {
					if ("java.lang.String".equals(f.getType().getName())) {
						//获取字段名
						String key = f.getName();
						Object value = getFieldValue(bean, key);

						if (value == null) {
							continue;
						}
						setFieldValue(bean, key, value.toString().trim());
					}
				}
			}
		} catch (Exception e) {
			throw ExceptionUtils.mpe(e);
		}
	}

	/**
	 * 利用反射通过get方法获取bean中字段fieldName的值
	 *
	 * @param bean      {@link Object}
	 * @param fieldName {@link String}
	 * @return {@link Object}
	 * @throws Exception Exception
	 */
	private static Object getFieldValue(Object bean, String fieldName) throws Exception {
		String methodName = "get" + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);

		Object rObject;
		Method method;

		@SuppressWarnings("rawtypes")
		Class[] classArr = new Class[0];
		method = bean.getClass().getMethod(methodName, classArr);
		rObject = method.invoke(bean);

		return rObject;
	}

	/**
	 * 利用发射调用bean.set方法将value设置到字段
	 *
	 * @param bean      {@link Object}
	 * @param fieldName {@link String}
	 * @param value     {@link Object}
	 * @throws Exception Exception
	 */
	private static void setFieldValue(Object bean, String fieldName,
									  Object value) throws Exception {
		String methodName = "set" + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
		//利用发射调用bean.set方法将value设置到字段
		Class<?>[] classArr = new Class[1];
		classArr[0] = "java.lang.String".getClass();
		Method method = bean.getClass().getMethod(methodName, classArr);
		method.invoke(bean, value);
	}
}
