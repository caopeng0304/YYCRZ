package com.sinosoft.ie.booster.common.database.util;

/**
 * ArrayUtils
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/30 22:22
 */
public class ArrayUtils {
	private ArrayUtils() {
	}

	/**
	 * 判断数据是否为空
	 *
	 * @param array {@link Object} 长度
	 * @return {@link Boolean} 数组为null或者长度为0时，返回 false
	 */
	public static boolean isEmpty(Object[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * 判断数组是否不为空
	 *
	 * @param array {@link Object} 数组
	 * @return {@link Boolean} 数组对象内含有任意对象时返回 true
	 * @see ArrayUtils#isEmpty(Object[])
	 */
	public static boolean isNotEmpty(Object[] array) {
		return !isEmpty(array);
	}
}
