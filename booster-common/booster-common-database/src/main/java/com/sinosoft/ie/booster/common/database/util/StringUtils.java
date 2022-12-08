package com.sinosoft.ie.booster.common.database.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/30 22:21
 */
public class StringUtils {
	private StringUtils() {
	}

	/**
	 * 安全的进行字符串 format
	 *
	 * @param target 目标字符串
	 * @param params format 参数
	 * @return format 后的
	 */
	public static String format(String target, Object... params) {
		return String.format(target, params);
	}

	public static boolean isBlank(final CharSequence cs) {
		if (cs == null) {
			return true;
		}
		int l = cs.length();
		if (l > 0) {
			for (int i = 0; i < l; i++) {
				if (!Character.isWhitespace(cs.charAt(i))) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean isNotBlank(final CharSequence cs) {
		return !isBlank(cs);
	}

	/**
	 * 去除换行
	 */
	static final Pattern REPLACE_BLANK_PATTERN = Pattern.compile("\\s*|\t|\r|\n");

	/**
	 * replaceBlank
	 *
	 * @param str {@link String}
	 * @return {@link String}
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Matcher m = REPLACE_BLANK_PATTERN.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	/**
	 * 如果为空默认
	 *
	 * @param str        {@link String}
	 * @param defaultStr {@link String}
	 * @return {@link String}
	 */
	public static String defaultString(final String str, final String defaultStr) {
		return isBlank(str) ? defaultStr : str;
	}
}
