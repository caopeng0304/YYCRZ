package com.sinosoft.ie.booster.visualdev.util;


import cn.hutool.core.util.StrUtil;

/**
 * 字符串工具类
 *
 * @author booster开发平台组
 */
public class StringUtil {
	/**
	 * 是否包含字符串
	 *
	 * @param str  验证字符串
	 * @param strs 字符串组
	 * @return 包含返回true
	 */
	public static boolean inStringIgnoreCase(String str, String... strs) {
		if (str != null && strs != null) {
			for (String s : strs) {
				if (str.equalsIgnoreCase(StrUtil.trim(s))) {
					return true;
				}
			}
		}
		return false;
	}
}
