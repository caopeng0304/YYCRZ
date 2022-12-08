package com.sinosoft.ie.booster.common.database.exception;

/**
 * MappingException
 *
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2020/3/25 21:59
 */
public class MappingException extends ScrewException {
	public MappingException() {
	}

	public MappingException(String message) {
		super(message);
	}

	public MappingException(String message, Throwable cause) {
		super(message, cause);
	}

	public MappingException(Throwable cause) {
		super(cause);
	}

	public MappingException(String message, Throwable cause, boolean enableSuppression,
							boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
