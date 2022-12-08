package com.sinosoft.ie.booster.common.database.util;

import java.util.Collection;
import java.util.Map;

/**
 * Collection 工具类
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/30 22:22
 */
public class CollectionUtils {
	public CollectionUtils() {
	}

	/**
	 * 校验集合是否为空
	 *
	 * @param coll {@link Collection} 入参
	 * @return {@link Boolean} boolean
	 */
	public static boolean isEmpty(Collection<?> coll) {
		return (coll == null || coll.isEmpty());
	}

	/**
	 * 校验集合是否不为空
	 *
	 * @param coll {@link Collection} 入参
	 * @return {@link Boolean} boolean
	 */
	public static boolean isNotEmpty(Collection<?> coll) {
		return !isEmpty(coll);
	}

	/**
	 * 判断Map是否为空
	 *
	 * @param map {@link Map} 入参
	 * @return {@link Boolean} boolean
	 */
	public static boolean isEmpty(Map<?, ?> map) {
		return (map == null || map.isEmpty());
	}

	/**
	 * 判断Map是否不为空
	 *
	 * @param map {@link Map} 入参
	 * @return {@link Boolean} boolean
	 */
	public static boolean isNotEmpty(Map<?, ?> map) {
		return !isEmpty(map);
	}
}
