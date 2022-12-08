package com.sinosoft.ie.booster.common.database.util;

import com.sinosoft.ie.booster.common.database.exception.ScrewException;

/**
 * 异常工具类
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/30 22:19
 */
public class ExceptionUtils {
	private ExceptionUtils() {
	}

	/**
	 * 返回一个新的异常，统一构建，方便统一处理
	 *
	 * @param msg    {@link String} 消息
	 * @param t      {@link Throwable} 异常信息
	 * @param params {@link String} 参数
	 * @return ScrewException ScrewException
	 */
	public static ScrewException mpe(String msg, Throwable t, Object... params) {
		return new ScrewException(StringUtils.format(msg, params), t);
	}

	/**
	 * 重载的方法
	 *
	 * @param msg    {@link String} 消息
	 * @param params {@link Object} 参数
	 * @return ScrewException ScrewException
	 */
	public static ScrewException mpe(String msg, Object... params) {
		return new ScrewException(StringUtils.format(msg, params));
	}

	/**
	 * 重载的方法
	 *
	 * @param t {@link Throwable} 异常
	 * @return ScrewException  ScrewException
	 */
	public static ScrewException mpe(Throwable t) {
		return new ScrewException(t);
	}
}
