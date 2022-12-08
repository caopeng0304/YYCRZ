package com.sinosoft.ie.booster.common.database.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Map 工具类
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/21 23:13
 */
public class MapUtils {
	/**
	 * 对象转Map
	 *
	 * @param obj {@link Object}
	 * @return {@link Map}
	 * @throws IllegalAccessException IllegalAccessException
	 */
	public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
		Map<String, Object> map = new HashMap<>(16);
		Class<?> clazz = obj.getClass();
		for (Field field : clazz.getDeclaredFields()) {
			field.setAccessible(true);
			String fieldName = field.getName();
			Object value = field.get(obj);
			map.put(fieldName, value);
		}
		return map;
	}
}
