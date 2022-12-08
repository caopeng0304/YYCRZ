package com.sinosoft.ie.booster.auth.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sinosoft.ie.booster.auth.component.BoosterAuth2ExceptionSerializer;

/**
 * @author lengleng
 * @since 2019/2/1
 */
@JsonSerialize(using = BoosterAuth2ExceptionSerializer.class)
public class InvalidException extends BoosterAuth2Exception {

	public InvalidException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "invalid_exception";
	}

	@Override
	public int getHttpErrorCode() {
		return 426;
	}

}
