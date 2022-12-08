package com.sinosoft.ie.booster.auth.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sinosoft.ie.booster.auth.component.BoosterAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * @author lengleng
 * @since 2019/2/1
 */
@JsonSerialize(using = BoosterAuth2ExceptionSerializer.class)
public class UnauthorizedException extends BoosterAuth2Exception {

	public UnauthorizedException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "unauthorized";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.UNAUTHORIZED.value();
	}

}
